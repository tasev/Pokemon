package com.gsixacademy.android.pokemon.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gsixacademy.android.pokemon.R
import com.gsixacademy.android.pokemon.api.PokemonApi
import com.gsixacademy.android.pokemon.api.ServiceBuilder
import com.gsixacademy.android.pokemon.models.PokemonDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pokemon_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonDetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)

        var pokemonName = intent.getStringExtra("pokemonName")
        var pokemonUrl = intent.getStringExtra("pokemonUrl")

        text_view_pokemon_name.text = pokemonName

        // from this "https://pokeapi.co/api/v2/pokemon/2/" to this "https://pokeres.bastionbot.org/images/pokemon/2.png"
        var pokemonId = pokemonUrl?.trimEnd('/')?.substringAfterLast('/')
        Picasso.get().load("https://pokeres.bastionbot.org/images/pokemon/${pokemonId}.png").fit().centerInside().into(image_view_pokemon_details)

        image_view_back.setOnClickListener { onBackPressed() }

        val request = ServiceBuilder.buildService(PokemonApi::class.java)

        val cal = request.getPokemonDetails(pokemonName)

        cal.enqueue(object : Callback<PokemonDetails>{
            override fun onFailure(call: Call<PokemonDetails>, t: Throwable) {
               Toast.makeText(applicationContext,"api error",Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<PokemonDetails>,
                response: Response<PokemonDetails>
            ) {
                if(response.isSuccessful){
                      fillPokemonData(response.body())
                }
            }

        })

    }

 fun fillPokemonData(pokemonDetails: PokemonDetails?){
     text_view_info_name.text = pokemonDetails?.name
     text_view_info_height.text = "Height: ${pokemonDetails?.height}m"
     text_view_info_weight.text = "Weight: ${pokemonDetails?.weight}kg"

     if(pokemonDetails?.types!= null && pokemonDetails.types.size>0)
     text_view_info_type_slot1.text = pokemonDetails.types[0].type?.name ?: ""

     if(pokemonDetails?.types!= null && pokemonDetails.types.size>1) {
         text_view_info_type_slot2.text = pokemonDetails.types[1].type?.name ?: ""
     }else {
         text_view_info_type_slot2.visibility = View.INVISIBLE
     }




 }

}