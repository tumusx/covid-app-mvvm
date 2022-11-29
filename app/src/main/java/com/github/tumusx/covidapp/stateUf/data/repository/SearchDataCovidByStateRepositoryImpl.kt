package com.github.tumusx.covidapp.stateUf.data.repository

import com.github.tumusx.covidapp.retrofitInstance.RetrofitInstance
import com.github.tumusx.covidapp.stateUf.data.service.IServiceState
import com.github.tumusx.covidapp.stateUf.domain.model.StateVO
import com.github.tumusx.covidapp.stateUf.domain.repository.ISearchDataCovidByStateRepository

class SearchDataCovidByStateRepositoryImpl(
    private val service: IServiceState = RetrofitInstance
        .onCreateRetrofit
        .create(IServiceState::class.java)
) : ISearchDataCovidByStateRepository {
    override suspend fun getDataByState(countryName: String, nameState: String): Result<StateVO?> {
        val response = service.getDataByState(countryName, nameState)
        return runCatching {
            if (response.isSuccessful) {
                with(response) {
                    println("body " + this.body())
                    println("raw " + this.raw())
                    this.body()?.let { body ->
                        StateVO(cases = body.cases, deaths = body.deaths, suspects = body.suspects, uf = body.uf)
                    }
                }
            } else {
                null
            }
        }
    }
}