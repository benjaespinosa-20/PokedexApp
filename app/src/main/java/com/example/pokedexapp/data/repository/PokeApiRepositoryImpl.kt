package com.example.pokedexapp.data.repository

import com.example.pokedexapp.data.datasourse.PokeApiDataSource
import com.example.pokedexapp.data.response.PokeApiResponse
import com.example.pokedexapp.data.response.PokeResult

class PokeApiRepositoryImpl(private val dataSource: PokeApiDataSource): PokeApiRepository {
    override suspend fun getPokeList(): PokeApiResponse = dataSource.getPokeList()
}