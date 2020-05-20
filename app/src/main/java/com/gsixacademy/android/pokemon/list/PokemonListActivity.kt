package com.gsixacademy.android.pokemon.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gsixacademy.android.pokemon.R
import com.gsixacademy.android.pokemon.api.PokemonApi
import com.gsixacademy.android.pokemon.api.ServiceBuilder
import com.gsixacademy.android.pokemon.models.PokemonListResponse
import kotlinx.android.synthetic.main.activity_pokemon_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_list)

        val request = ServiceBuilder.buildService(PokemonApi::class.java)

        val call = request.getPokemonList(0, 50)

        call.enqueue(object : Callback<PokemonListResponse> {
            override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Api error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<PokemonListResponse>,
                response: Response<PokemonListResponse>
            ) {
                if (response.isSuccessful) {
                    var pokemonListResponse = response.body()

                    var pokemons = pokemonListResponse?.results

                    if (pokemons != null) {
                        //initiate adapter
                        var pokemonListAdapter = PokemonListAdapter(pokemons){

                        }
                        recycler_view_pokemons.adapter = pokemonListAdapter
                    }

                }
            }

        })
    }
}
