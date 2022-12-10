package com.github.tumusx.covidapp.country.presenter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.github.tumusx.covidapp.country.domain.model.CountryVO
import com.github.tumusx.covidapp.country.presenter.viewModel.SearchCountryByNameViewModel
import com.github.tumusx.covidapp.databinding.CountryFragmentBinding
import com.github.tumusx.covidapp.state.DataStateUI
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CountryFragment : Fragment() {
    private val viewModel by viewModel<SearchCountryByNameViewModel>()
    private lateinit var binding: CountryFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CountryFragmentBinding.inflate(layoutInflater)
        onConfigureSearchCountry()
        onResultState()
        onConfigureListeners()
        onConfigureResultList()
        return binding.root
    }

    private fun onConfigureUI(countryVO: CountryVO) {

        binding.nameCountry.text = "nome do país: " + countryVO.nameCountry ?: "sem dados"
        binding.confirmed.text = "confirmado: " + countryVO.confirmed.toString() ?: "sem dados"
        binding.qtdDeaths.text =
            "quantidade de mortes: " + countryVO.qtdDeaths.toString() ?: "sem dados"
        binding.updatedAt.text =
            "ultima atualizacao: " + countryVO.updatedAt.toString() ?: "sem dados"
    }


    private fun onResultState() {
        lifecycleScope.launch {
            viewModel.stateUI.collect { state ->
                when (state) {
                    is DataStateUI.SuccessDataUI<*> -> {
                        val county = (state.data as CountryVO)
                        if (county.nameCountry != null) {
                            onConfigureUI(county)
                            viewModel.insetItem(county)
                        }
                    }

                    is DataStateUI.ErrorDataUI -> {
                        println(state.error)
                    }

                    is DataStateUI.Loading -> {
                        println("loading")
                    }
                }
            }
        }
    }

    private fun onConfigureResultList() {
        lifecycleScope.launch{
            viewModel.stateDataBase.collect { state ->
                when (state) {
                    is DataStateUI.SuccessDataUI<*> -> {
                        val county = state.data as List<CountryVO>
                        if (county.first().nameCountry != null && county.isNotEmpty()) onConfigureUI(county.first())
                    }

                    is DataStateUI.ErrorDataUI -> {
                        println(state.error)
                    }

                    is DataStateUI.Loading -> {
                        println("loading")
                    }
                }
            }
        }
    }

    private fun onConfigureSubmitNameCountry(query: String?) {
        query?.let { text ->
            viewModel.searchCountryByName(text)
        }.also {
            Toast.makeText(requireContext(), "Pesquisando o pais $query", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun onConfigureListeners(){
        binding.button.setOnClickListener {
            viewModel.listAllItems()
        }
    }

    private fun onConfigureSearchCountry() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                onConfigureSubmitNameCountry(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.count()?.let { size -> size >= 4 } == true)
                    onConfigureTimeSearch(newText)
                return false
            }
        })
    }

    private fun onConfigureTimeSearch(newText: String?) {
        newText?.let { text ->
            viewModel.searchCountryByName(text)
        }
    }
}