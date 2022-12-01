package com.github.tumusx.covidapp.country.data.repository

import com.github.tumusx.covidapp.country.data.model.CountryFilterDateDTO
import com.github.tumusx.covidapp.country.data.service.IService
import com.github.tumusx.covidapp.country.domain.model.CountryVO
import com.github.tumusx.covidapp.country.domain.repository.ISearchCountryRepository
import com.github.tumusx.covidapp.retrofitInstance.RetrofitInstance

class SearchCountryRepositoryImpl(
    private val service: IService
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

    override suspend fun filterCountryByDate(
        nameCountry: String,
        date: String
    ): Result<List<CountryFilterDateDTO>?> {
        return runCatching {
            val request = service.searchByCountryDateName(nameCountry, date)
            if (request.isSuccessful) {
                request.body()
            } else {
                null
            }
        }
    }
}