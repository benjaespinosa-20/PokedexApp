package com.example.pokedexapp.data.datasourse

import com.example.pokedexapp.data.response.PokeApiResponse
import com.example.pokedexapp.data.response.PokeResult
import com.example.pokedexapp.data.service.Service


class PokeApiDataSource(private val webService: Service) {
    suspend fun getPokeList(): PokeApiResponse = webService.getPokemonList(151,0)
}