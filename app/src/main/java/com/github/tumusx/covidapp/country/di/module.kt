package com.github.tumusx.covidapp.country.di

import androidx.room.Room
import com.github.tumusx.covidapp.country.data.local.SearchDataCovidDataBase
import com.github.tumusx.covidapp.country.data.repository.SearchCountryRepositoryImpl
import com.github.tumusx.covidapp.country.data.service.IService
import com.github.tumusx.covidapp.country.domain.repository.ISearchCountryRepository
import com.github.tumusx.covidapp.country.presenter.viewModel.SearchCountryByNameViewModel
import com.github.tumusx.covidapp.retrofitInstance.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val countryModuleKoin = module {
    val retrofitBuilder = RetrofitInstance.onCreateRetrofit.create(IService::class.java)

    single {
        Room.databaseBuilder(androidApplication(), SearchDataCovidDataBase::class.java, "room.db")
            .allowMainThreadQueries().build()
    }

    fun onSearchDataCovidByCountryRepository(): ISearchCountryRepository {
        return SearchCountryRepositoryImpl(retrofitBuilder)
    }


    viewModel {
        SearchCountryByNameViewModel(
            onSearchDataCovidByCountryRepository(),
            Dispatchers.IO
        )
    }
}