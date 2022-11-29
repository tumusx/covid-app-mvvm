package com.github.tumusx.covidapp.state

sealed class DataStateUI {
    data class SuccessDataUI<T>(val data: T?) : DataStateUI()
    data class ErrorDataUI(val error: String) : DataStateUI()
    object Loading : DataStateUI()
}