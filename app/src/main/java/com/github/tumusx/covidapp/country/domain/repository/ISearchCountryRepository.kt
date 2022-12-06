package com.github.tumusx.covidapp.country.domain.repository

import com.github.tumusx.covidapp.country.domain.model.CountryVO

interface ISearchCountryRepository {
    suspend fun searchCountryByName(nameCountry: String) : Result<CountryVO?>
}