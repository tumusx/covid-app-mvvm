package com.github.tumusx.covidapp.country.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "searchDataEntity")
data class SearchDataEntity(
    @ColumnInfo(name = "name_country")
    val nameCountry: String?,

    @ColumnInfo(name = "death_country")
    val deathCountry: Int?,

    @ColumnInfo(name = "cases_confirmed")
    val casesConfirmed: Int?,

    @ColumnInfo(name = "update_at")
    val updateAt: String?
)