package com.github.tumusx.covidapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.tumusx.covidapp.country.data.local.SearchDataCovidDataBase
import com.github.tumusx.covidapp.country.data.local.SearchDataDAO
import com.github.tumusx.covidapp.country.data.local.SearchDataEntity
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SimpleEntityReadWriteTest {
    private lateinit var searchDataDAO: SearchDataDAO
    private lateinit var db: SearchDataCovidDataBase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.databaseBuilder(
            context, SearchDataCovidDataBase::class.java,
            SearchDataCovidDataBase::class.java.name
        ).build()
        searchDataDAO = db.searchDataDAO()
    }


    @Test
    fun writeDataAndReadInList() {
        val entity = SearchDataEntity("brazil", 21, 21, "2010201")
        searchDataDAO.insertData(entity)
        val resultList = searchDataDAO.getAllDataEntity()
        assertThat(resultList[0], equalTo(entity))
    }
}