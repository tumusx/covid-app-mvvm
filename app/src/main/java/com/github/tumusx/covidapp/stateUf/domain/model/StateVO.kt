package com.github.tumusx.covidapp.stateUf.domain.model

data class StateVO(
    var cases: Int? = 0,
    var deaths: Int? = 0,
    var suspects: Int? = 0,
    var uf: String? = ""
)