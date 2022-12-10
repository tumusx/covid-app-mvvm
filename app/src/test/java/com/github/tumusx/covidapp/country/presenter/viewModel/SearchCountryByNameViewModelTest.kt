package com.github.tumusx.covidapp.country.presenter.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.tumusx.covidapp.country.domain.model.CountryVO
import com.github.tumusx.covidapp.state.DataStateUI
import com.github.tumusx.covidapp.stateUf.data.repository.MainCoroutineRule
import com.github.tumusx.covidapp.stateUf.data.repository.SearchDataCovidByStateRepositoryImplTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchCountryByNameViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val coroutineContextRule = MainCoroutineRule()
    private lateinit var viewModel: SearchCountryByNameViewModel
    @Before
    fun setup() {
        viewModel = SearchCountryByNameViewModel(
            SearchDataCovidByStateRepositoryImplTest(),
            coroutineContextRule.testScheduler,
        )
    }
    @Test
    fun searchCountryResult() {
        viewModel.searchCountryByName("brazil")
        assertEquals(
            viewModel.stateUI.value,
            DataStateUI.SuccessDataUI(
                CountryVO(nameCountry = "Brazil", updatedAt = "2020/10/02", 123, 145, 145))) }
    @Test
    fun searchCountryReturnError(){
        viewModel.searchCountryByName("")
        assertEquals(viewModel.stateUI.value, DataStateUI.ErrorDataUI("BORA BILL"))
    }
}