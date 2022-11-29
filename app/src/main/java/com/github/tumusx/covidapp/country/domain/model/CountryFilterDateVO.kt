package com.github.tumusx.covidapp.country.domain.model

data class CountryFilterDateVO(
    var state: String? = null,
    var uf: String? = null,
    var suspects: Int? = null,
    var deaths: Int? = null,
    var cases: Int? = null,
    )