package com.github.tumusx.covidapp.stateUf.data.repository

import com.github.tumusx.covidapp.country.domain.model.CountryVO
import com.github.tumusx.covidapp.country.domain.repository.ISearchCountryRepository

class SearchDataCovidByStateRepositoryImplTest : ISearchCountryRepository {
    override suspend fun searchCountryByName(nameCountry: String): Result<CountryVO?> {
        return Result.success(
            CountryVO(
                nameCountry = "Brazil",
                updatedAt = "2020/10/02",
                123,
                145,
                145
            )
        )
    }
}