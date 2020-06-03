package com.gsixacademy.android.pokemon.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.gsixacademy.android.pokemon.R
import com.gsixacademy.android.pokemon.api.PokemonApi
import com.gsixacademy.android.pokemon.api.ServiceBuilder
import com.gsixacademy.android.pokemon.models.PokemonDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonDetailsMovesFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details_moves,container,false)

        val pokemonName =  arguments?.getString("pokemonName")
        val pokemonUrl =  arguments?.getString("pokemonUrl")

        val request = ServiceBuilder.buildService(PokemonApi::class.java)

        val cal = request.getPokemonDetails(pokemonName?:"")

        cal.enqueue(object : Callback<PokemonDetails> {
            override fun onFailure(call: Call<PokemonDetails>, t: Throwable) {
                Toast.makeText(activity,"api error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<PokemonDetails>,
                response: Response<PokemonDetails>
            ) {
                if(response.isSuccessful){
                    fillPokemonMovesData(view, response.body())
                }
            }

        })

        return view
    }

    private fun fillPokemonMovesData(view: View?, pokemonDetails: PokemonDetails?) {

        // fill xml data

    }

}