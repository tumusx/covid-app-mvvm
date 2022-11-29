package com.github.tumusx.covidapp.country.domain.repository

import com.github.tumusx.covidapp.country.data.model.CountryFilterDateDTO
import com.github.tumusx.covidapp.country.domain.model.CountryVO

interface ISearchCountryRepository {
    suspend fun searchCountryByName(nameCountry: String) : Result<CountryVO?>
    suspend fun filterCountryByDate(nameCountry: String, date: String) : Result<List<CountryFilterDateDTO>?>
}