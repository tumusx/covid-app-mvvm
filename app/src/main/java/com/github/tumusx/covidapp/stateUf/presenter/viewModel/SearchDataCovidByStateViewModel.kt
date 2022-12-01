package com.github.tumusx.covidapp.stateUf.presenter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.tumusx.covidapp.state.DataStateUI
import com.github.tumusx.covidapp.stateUf.data.repository.SearchDataCovidByStateRepositoryImpl
import com.github.tumusx.covidapp.stateUf.domain.model.StateVO
import com.github.tumusx.covidapp.stateUf.domain.repository.ISearchDataCovidByStateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SearchDataCovidByStateViewModel(
    private val repository: ISearchDataCovidByStateRepository,
    private val coroutineContext: CoroutineContext
) : ViewModel() {
    private val _state: MutableStateFlow<DataStateUI> =
        MutableStateFlow(DataStateUI.Loading)
    val stateUI: StateFlow<DataStateUI> = _state

    fun searchDataByState(ufState: String) {
        viewModelScope.launch(coroutineContext) {
            repository.getDataByState("brazil", ufState).onSuccess { stateVoResult ->
                _state.value = DataStateUI.SuccessDataUI(stateVoResult)
            }.onFailure {
                _state.value = DataStateUI.ErrorDataUI(it.message ?: "unknown error")
                it.printStackTrace()
            }
        }
    }
}