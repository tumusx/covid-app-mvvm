package com.github.tumusx.covidapp.country.data.repository

import com.github.tumusx.covidapp.country.data.service.IService
import com.github.tumusx.covidapp.country.domain.model.CountryVO
import com.github.tumusx.covidapp.country.domain.repository.ISearchCountryRepository

class SearchCountryRepositoryImpl(
    private val service: IService,
) : ISearchCountryRepository {
    override suspend fun searchCountryByName(nameCountry: String): Result<CountryVO?> {
        return runCatching {
            val request = service.searchByCountryName(nameCountry)
            if (request.isSuccessful) {
                request.body()?.data?.countryVO()
            } else {
                null
            }
        }
    }

}