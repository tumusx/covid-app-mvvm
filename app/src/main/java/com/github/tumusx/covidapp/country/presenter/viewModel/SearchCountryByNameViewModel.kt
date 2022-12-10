package com.github.tumusx.covidapp.country.presenter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.tumusx.covidapp.country.data.local.SearchDataCovidDataBase
import com.github.tumusx.covidapp.country.data.local.SearchDataEntity
import com.github.tumusx.covidapp.country.domain.model.CountryVO
import com.github.tumusx.covidapp.country.domain.repository.ISearchCountryRepository
import com.github.tumusx.covidapp.state.DataStateUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SearchCountryByNameViewModel(
    private val searchCountryByRepository: ISearchCountryRepository,
    private val coroutineContext: CoroutineContext,
    private val searchDataCovidDataBase: SearchDataCovidDataBase? = null
) :
    ViewModel() {
    private val _state: MutableStateFlow<DataStateUI> =
        MutableStateFlow(DataStateUI.Loading)
    val stateUI: StateFlow<DataStateUI> = _state

    private val _stateDataBase: MutableStateFlow<DataStateUI> =
        MutableStateFlow(DataStateUI.Loading)
    val stateDataBase: StateFlow<DataStateUI> = _stateDataBase

    fun searchCountryByName(nameCountry: String) {
        viewModelScope.launch(coroutineContext) {
            val result = searchCountryByRepository.searchCountryByName(nameCountry)
            result.onSuccess {
                _state.value = DataStateUI.SuccessDataUI(it)
                println(it)
            }.onFailure { error ->
                _state.value = DataStateUI.ErrorDataUI(error.message ?: "unknown error")
                println(error.message)
            }
        }
    }


    private fun insertItemDatabase(countryVO: CountryVO) {
        viewModelScope.launch {
            val countryEntity = SearchDataEntity(
                countryVO.nameCountry,
                countryVO.qtdDeaths,
                countryVO.confirmed,
                countryVO.updatedAt
            )
            searchDataCovidDataBase?.searchDataDAO()?.insertData(countryEntity)
            println(searchDataCovidDataBase?.searchDataDAO()?.getAllDataEntity())
        }
    }

    private fun listItemsAlls(): List<CountryVO> {
        val countryVoList = mutableListOf<CountryVO>()
        viewModelScope.launch {
            val result = searchDataCovidDataBase?.searchDataDAO()?.getAllDataEntity()
            try {
                result?.let { resultList ->
                    for (itemResultList in resultList) {
                        countryVoList.add(
                            CountryVO(
                                itemResultList.nameCountry,
                                itemResultList.updateAt,
                                itemResultList.deathCountry,
                                itemResultList.casesConfirmed,
                                null
                            )
                        )
                    }
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
        return countryVoList
    }

    fun insetItem(countryVO: CountryVO) {
        viewModelScope.launch(coroutineContext) {
            try {
                insertItemDatabase(countryVO)
                println(searchDataCovidDataBase?.searchDataDAO()?.getAllDataEntity())
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    fun listAllItems() {
        viewModelScope.launch(coroutineContext) {
            val resultItems = listItemsAlls()
            try {
                _stateDataBase.value = DataStateUI.SuccessDataUI(resultItems)
            } catch (exception: Exception) {
                _stateDataBase.value = DataStateUI.ErrorDataUI("Erro ao salvar pesquisa.")
                exception.printStackTrace()
            }
        }
    }
}