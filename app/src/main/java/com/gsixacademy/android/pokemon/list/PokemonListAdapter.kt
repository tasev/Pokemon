package com.gsixacademy.android.pokemon.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gsixacademy.android.pokemon.R
import com.gsixacademy.android.pokemon.models.PokemonResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pokemon_list_item.view.*

class PokemonListAdapter(val itemList: ArrayList<PokemonResult>, val pokemonListAdapterClickEvent: (PokemonListAdapterClickEvent)-> Unit):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.pokemon_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var myViewHolder = holder as MyViewHolder
        myViewHolder.bindData(itemList[position], position)
    }

    inner class MyViewHolder (view: View) : RecyclerView.ViewHolder(view){
        fun bindData(itemModel: PokemonResult, position: Int) {
            // from this "https://pokeapi.co/api/v2/pokemon/2/" to this "https://pokeres.bastionbot.org/images/pokemon/2.png"
            var pokemonId = itemModel.url?.trimEnd('/')?.substringAfterLast('/')

            Picasso.get().load("https://pokeres.bastionbot.org/images/pokemon/${pokemonId}.png").fit().centerInside().into(itemView.image_view_pokemon)
            itemView.text_view_name.text = itemModel.name
            itemView.text_view_position.text = position.toString()
        }
    }

}