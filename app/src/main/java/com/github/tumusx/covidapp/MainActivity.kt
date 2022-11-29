package com.github.tumusx.covidapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.github.tumusx.covidapp.country.presenter.viewModel.SearchCountryByNameViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: SearchCountryByNameViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[SearchCountryByNameViewModel::class.java]
        viewModel.searchCountryByName("brazil")
        setContentView(R.layout.activity_main)
    }
}