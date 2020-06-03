package com.gsixacademy.android.pokemon.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PokemonDetailsPagerAdapter(
    fragmentManager: FragmentManager,
    val pokemonName: String?,
    val pokemonUrl: String?
): FragmentStatePagerAdapter(fragmentManager){

    override fun getItem(position: Int): Fragment {

        var args = Bundle()
        args.putString("pokemonName",pokemonName)
        args.putString("pokemonUrl",pokemonUrl)

        var fragment: Fragment

        if(position==0){
            fragment = PokemonDetailsGeneralFragment()
        }else{
            fragment =  PokemonDetailsMovesFragment()
        }

        fragment.arguments = args

        return fragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        if(position==0){
            return "General"
        }else{
            return "Moves"
        }
    }
}