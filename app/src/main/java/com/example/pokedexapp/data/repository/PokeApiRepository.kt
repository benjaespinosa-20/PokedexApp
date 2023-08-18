package com.example.pokedexapp.data.repository

import com.example.pokedexapp.data.response.PokeApiResponse
import com.example.pokedexapp.data.response.PokeResult
import retrofit2.Call

interface PokeApiRepository {
    suspend fun getPokeList(): PokeApiResponse
}