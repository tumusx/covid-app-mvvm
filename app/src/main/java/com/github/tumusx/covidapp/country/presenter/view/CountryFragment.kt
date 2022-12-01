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
        return binding.root
    }

    private fun onShowMessageNotFoundCountry(nameCountry: String?) {
        if (nameCountry == null) Toast.makeText(
            requireContext(),
            "país não encontrado. tente novamente",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun onResultState() {
        lifecycleScope.launch {
            viewModel.stateUI.collect { state ->
                when (state) {
                    is DataStateUI.SuccessDataUI<*> -> {
                        val county = (state.data as CountryVO)
                        onShowMessageNotFoundCountry(county.nameCountry)
                        println(county)
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

    private fun onConfigureSubmitCountry(query: String?) {
        query?.let { text ->
            viewModel.searchCountryByName(text)
        }.also {
            Toast.makeText(requireContext(), "Pesquisando o pais $query", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun onConfigureSearchCountry() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                onConfigureSubmitCountry(query)
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