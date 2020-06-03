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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pokemon_details.view.*
import kotlinx.android.synthetic.main.activity_pokemon_details.view.image_view_back
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonDetailsGeneralFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.activity_pokemon_details,container,false)

        val pokemonName =  arguments?.getString("pokemonName")
        val pokemonUrl =  arguments?.getString("pokemonUrl")

        view.text_view_pokemon_name.text = pokemonName

        // from this "https://pokeapi.co/api/v2/pokemon/2/" to this "https://pokeres.bastionbot.org/images/pokemon/2.png"
        var pokemonId = pokemonUrl?.trimEnd('/')?.substringAfterLast('/')
        Picasso.get().load("https://pokeres.bastionbot.org/images/pokemon/${pokemonId}.png").fit().centerInside().into(view.image_view_pokemon_details)

        view.image_view_back.setOnClickListener { activity?.onBackPressed() }

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
                    fillPokemonData(view, response.body())
                }
            }

        })


        return view
    }


    fun fillPokemonData(view: View, pokemonDetails: PokemonDetails?){
        view.text_view_info_name.text = pokemonDetails?.name
        view.text_view_info_height.text = "Height: ${pokemonDetails?.height}m"
        view.text_view_info_weight.text = "Weight: ${pokemonDetails?.weight}kg"

        if(pokemonDetails?.types!= null && pokemonDetails.types.size>0)
            view.text_view_info_type_slot1.text = pokemonDetails.types[0].type?.name ?: ""

        if(pokemonDetails?.types!= null && pokemonDetails.types.size>1) {
            view.text_view_info_type_slot2.text = pokemonDetails.types[1].type?.name ?: ""
        }else {
            view.text_view_info_type_slot2.visibility = View.INVISIBLE
        }

        if(pokemonDetails?.stats!=null){
            var totalSum = 0
            var baseStatPom = 1
            if(pokemonDetails.stats.size>0) {
                // "what percent of 600 is  500"
                // 500/600 * 100 = 83
                baseStatPom = pokemonDetails.stats[0].base_stat?: 1
                totalSum += baseStatPom
                view.text_view_name_stat1.text = pokemonDetails.stats[0].stat?.name
                view.text_view_value_stat1.text = "$baseStatPom"
                view.progress_bar_stat1.progress = (baseStatPom/600f*100).toInt()
            }
            if(pokemonDetails.stats.size>1) {
                baseStatPom = pokemonDetails.stats[1].base_stat?: 1
                totalSum+=baseStatPom

                view.text_view_name_stat2.text = pokemonDetails.stats[1].stat?.name
                view.text_view_value_stat2.text = "$baseStatPom"
                view.progress_bar_stat2.progress = (baseStatPom/600f*100).toInt()

            }
            if(pokemonDetails.stats.size>2) {
                baseStatPom = pokemonDetails.stats[2].base_stat?: 1
                totalSum+=baseStatPom

                view.text_view_name_stat3.text = pokemonDetails.stats[2].stat?.name
                view.text_view_value_stat3.text = "$baseStatPom"
                view.progress_bar_stat3.progress = (baseStatPom/600f*100).toInt()

            }

            if(pokemonDetails.stats.size>3) {
                baseStatPom = pokemonDetails.stats[3].base_stat?: 1
                totalSum+=baseStatPom

                view.text_view_name_stat4.text = pokemonDetails.stats[3].stat?.name
                view.text_view_value_stat4.text = "$baseStatPom"
                view.progress_bar_stat4.progress = (baseStatPom/600f*100).toInt()

            }

            if(pokemonDetails.stats.size>4) {
                baseStatPom = pokemonDetails.stats[4].base_stat?: 1
                totalSum+=baseStatPom

                view.text_view_name_stat5.text = pokemonDetails.stats[4].stat?.name
                view.text_view_value_stat5.text = "$baseStatPom"
                view.progress_bar_stat5.progress = (baseStatPom/600f*100).toInt()

            }

            if(pokemonDetails.stats.size>5) {
                baseStatPom = pokemonDetails.stats[5].base_stat?: 1
                totalSum+=baseStatPom

                view.text_view_name_stat6.text = pokemonDetails.stats[5].stat?.name
                view.text_view_value_stat6.text = "$baseStatPom"
                view.progress_bar_stat6.progress = (baseStatPom/600f*100).toInt()

            }
            view.text_view_value_total.text = "$totalSum"
            view.progress_bar_total.progress = totalSum


        }

    }

}