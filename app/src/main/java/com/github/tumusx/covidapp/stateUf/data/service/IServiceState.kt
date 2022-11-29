package com.github.tumusx.covidapp.stateUf.data.service

import com.github.tumusx.covidapp.stateUf.data.model.StateDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IServiceState {
    @GET("{country}/uf/{uf}")
    suspend fun getDataByState(
        @Path("country") country: String,
        @Path("uf") uf: String
    ): Response<StateDTO>
}