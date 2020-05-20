package com.gsixacademy.android.pokemon.models

class PokemonListResponse(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: ArrayList<PokemonResult>
)
