package com.github.tumusx.covidapp.country.data.model

import com.github.tumusx.covidapp.country.domain.model.CountryVO

data class CountryDTO(
    var cases: Int? = null,

    var confirmed: Int? = null,

    var country: String? = null,

    var deaths: Int? = null,

    var recovered: Int? = null,

    var updated_at: String? = null
) {
    fun countryVO(): CountryVO {
        return CountryVO(
            nameCountry = country, updatedAt = updated_at,
            qtdDeaths = deaths, confirmed = confirmed, cases = cases
        )
    }
}