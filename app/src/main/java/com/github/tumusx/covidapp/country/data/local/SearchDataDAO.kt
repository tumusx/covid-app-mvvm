package com.github.tumusx.covidapp.country.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SearchDataDAO {
    @Insert
     fun insertData(searchDataEntity: SearchDataEntity)

    @Query("SELECT * FROM searchDataEntity")
     fun getAllDataEntity(): List<SearchDataEntity>
}
