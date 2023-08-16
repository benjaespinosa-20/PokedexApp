package com.example.pokedexapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.pokedexapp.R
import com.example.pokedexapp.data.datasourse.PokeApiDataSource
import com.example.pokedexapp.data.repository.PokeApiRepositoryImpl
import com.example.pokedexapp.data.response.PokeResult
import com.example.pokedexapp.data.service.RetrofitClient
import com.example.pokedexapp.databinding.FragmentHomeBinding
import com.example.pokedexapp.presentation.PokeApiViewModel
import com.example.pokedexapp.presentation.PokeApiViewModelFactory
import com.example.pokedexapp.utils.Resource

class HomeFragment : Fragment(R.layout.fragment_home), PokeApiAdapter.OnPokeClickListener {
    private lateinit var binding: FragmentHomeBinding

    //Patron Factory inyeccion de dependencias
    private val viewModel by viewModels<PokeApiViewModel> {
        PokeApiViewModelFactory(PokeApiRepositoryImpl(PokeApiDataSource(RetrofitClient.webservice)))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        viewModel.getPokeList().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvHome.adapter = PokeApiAdapter(result.data.results, this@HomeFragment)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

    }

    override fun onPokeClick(product: PokeResult) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment())
    }

}