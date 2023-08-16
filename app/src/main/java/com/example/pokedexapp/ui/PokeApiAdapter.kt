package com.example.pokedexapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexapp.data.response.PokeApiResponse
import com.example.pokedexapp.data.response.PokeResult
import com.example.pokedexapp.databinding.PokemonItemBinding

class PokeApiAdapter(
    private val pokeList: List<PokeResult>,
    private val itemClickListener: OnPokeClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnPokeClickListener{
        fun onPokeClick(pokemon: PokeResult)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = PokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = PokeViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener {
            val position = holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.onPokeClick(pokeList[position])
        }
        return holder
    }

    override fun getItemCount(): Int = pokeList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is PokeViewHolder -> holder.bind(pokeList[position])
        }
    }

    private inner class PokeViewHolder(val binding: PokemonItemBinding, val context: Context): BaseViewHolder<PokeResult>(binding.root) {
        override fun bind(item: PokeResult) {
            binding.namePokemon.text = item.name
        }

    }

}

abstract class BaseViewHolder<T>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}