package com.github.tumusx.covidapp.country.presenter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.tumusx.covidapp.country.data.repository.SearchCountryRepositoryImpl
import com.github.tumusx.covidapp.country.domain.repository.ISearchCountryRepository
import com.github.tumusx.covidapp.state.DataStateUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

class SearchCountryByNameViewModel(
    private val searchCountryByRepository: ISearchCountryRepository,
    private val coroutineContext: CoroutineContext
) :
    ViewModel() {
    private val _state: MutableStateFlow<DataStateUI> =
        MutableStateFlow(DataStateUI.Loading)
    val stateUI: StateFlow<DataStateUI> = _state
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

    fun filterDataByDate(nameCountry: String, date: String) {
        viewModelScope.launch(coroutineContext) {
            searchCountryByRepository.filterCountryByDate(nameCountry, date)
                .onSuccess { countryList ->
                    println(countryList)
                }.onFailure { throwable ->
                    throwable.printStackTrace()
                }
        }
    }
}