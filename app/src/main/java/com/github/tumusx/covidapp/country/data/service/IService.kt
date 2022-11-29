package com.github.tumusx.covidapp.country.data.service

import com.github.tumusx.covidapp.country.data.model.CountryFilterDateDTO
import com.github.tumusx.covidapp.country.data.model.JConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IService {
    @GET("{country}")
    suspend fun searchByCountryName(@Path("country") country: String): Response<JConfig>

    @GET("{country}/{date}")
    suspend fun searchByCountryDateName(
        @Path("country") country: String,
        @Path("date") date: String,
    ): Response<List<CountryFilterDateDTO>>
}