package com.github.tumusx.covidapp.stateUf.domain.repository

import com.github.tumusx.covidapp.stateUf.domain.model.StateVO

interface ISearchDataCovidByStateRepository {
    suspend fun getDataByState(countryName: String, nameState: String) : Result<StateVO?>
}