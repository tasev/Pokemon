package com.gsixacademy.android.pokemon.api

import com.gsixacademy.android.pokemon.models.PokemonDetails
import com.gsixacademy.android.pokemon.models.PokemonListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("api/v2/pokemon")
    fun getPokemonList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<PokemonListResponse>

    @GET("api/v2/pokemon/{pokemonName}")
    fun getPokemonDetails(@Path("pokemonName") pokemonName: String): Call<PokemonDetails>

}