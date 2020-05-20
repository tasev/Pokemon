package com.gsixacademy.android.pokemon.list

import com.gsixacademy.android.pokemon.models.PokemonResult

sealed class PokemonListAdapterClickEvent {
    data class PokemonListAdapterItemClicked(val pokemonResult: PokemonResult): PokemonListAdapterClickEvent()
}