package com.example.pokedexapp.data.response

data class PokeApiResponse (
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<PokeResult> = listOf()
)

data class PokeResult (
    val name: String,
    val url: String?
)
