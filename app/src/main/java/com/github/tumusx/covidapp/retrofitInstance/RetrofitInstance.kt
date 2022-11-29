package com.github.tumusx.covidapp.retrofitInstance

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    private val baseUrl: String = "https://covid19-brazil-api.vercel.app/api/report/v1/"

    val onCreateRetrofit: Retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl).build()

}