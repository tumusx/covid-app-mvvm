package com.github.tumusx.covidapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.github.tumusx.covidapp.country.presenter.view.CountryFragment
import com.github.tumusx.covidapp.country.presenter.viewModel.SearchCountryByNameViewModel
import com.github.tumusx.covidapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        onConfigureTransactionFragmentInit()
        setContentView(R.layout.activity_main)
    }

    private fun onConfigureTransactionFragmentInit() {
        supportFragmentManager.beginTransaction()
            .replace(binding.fcFragment.id, CountryFragment(), CountryFragment::class.java.name)
            .commit()
    }
}