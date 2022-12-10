package com.github.tumusx.covidapp.country.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SearchDataEntity::class], version = 0, exportSchema = false)
abstract class SearchDataCovidDataBase : RoomDatabase() {
    abstract fun searchDataDAO(): SearchDataDAO
}