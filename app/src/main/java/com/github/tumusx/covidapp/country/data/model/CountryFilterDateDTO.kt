package com.github.tumusx.covidapp.country.data.model

import com.github.tumusx.covidapp.country.domain.model.CountryFilterDateVO

data class CountryFilterDateDTO(
    var broadcast: Boolean? = null,

    var cases: Int? = null,

    var comments: String? = null,

    var dateTime: String? = null,

    var deaths: Int? = null,

    var refuses: Int,

    var state: String? = null,

    var suspects: Int? = null,

    var uf: String? = null,

    var uid: Int? = null
) {

    fun CountryFilterDateDTO.countryVO(): CountryFilterDateVO {
        return CountryFilterDateVO(
            state = state,
            uf = uf,
            suspects = suspects,
            deaths = deaths,
            cases = cases
        )
    }
}