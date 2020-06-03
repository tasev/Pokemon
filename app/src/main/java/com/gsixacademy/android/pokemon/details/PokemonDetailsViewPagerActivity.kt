package com.gsixacademy.android.pokemon.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gsixacademy.android.pokemon.R
import kotlinx.android.synthetic.main.activity_pokemon_details_view_pager.*

class PokemonDetailsViewPagerActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details_view_pager)

        var pokemonName = intent.getStringExtra("pokemonName")
        var pokemonUrl = intent.getStringExtra("pokemonUrl")

        val adapter = PokemonDetailsPagerAdapter(supportFragmentManager, pokemonName, pokemonUrl)

        view_pager_details.adapter = adapter
        tab_layout_details.setupWithViewPager(view_pager_details)

    }
}