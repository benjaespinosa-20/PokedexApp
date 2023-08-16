package com.example.pokedexapp.data.service

import com.example.pokedexapp.data.response.PokeApiResponse
import com.example.pokedexapp.data.response.PokeResult
import com.example.pokedexapp.utils.Constants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): PokeApiResponse
}

object RetrofitClient{
    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(Service::class.java)
    }
}