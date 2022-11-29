package com.github.tumusx.covidapp.stateUf.data.model

data class StateDTO(
    var cases: Int? = null,

    var datetime: String? = null,

    var deaths: Int? = null,

    var refuses: Int? = null,

    var state: String? = null,

    var suspects: Int? = null,

    var uf: String? = null,

    var uid: Int? = null
)