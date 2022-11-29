package com.github.tumusx.covidapp.country.domain.model

data class CountryVO(
    var nameCountry: String? = "",
    var updatedAt: String? = null,
    var qtdDeaths: Int? = null,
    var confirmed: Int? = null,
    var cases: Int? = null,
)