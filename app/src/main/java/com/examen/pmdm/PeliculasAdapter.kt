package com.examen.pmdm

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.examen.pmdm.databinding.ItemPeliculasBinding

data class PeliculasAdapter (var listaPeliculas : List<Film>/*,var listaPelisPersonaje:Array<String>*/) : RecyclerView.Adapter<PeliculasAdapter.TextoViewHolder>(){

    class TextoViewHolder(var itemBinding : ItemPeliculasBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun getItemCount(): Int {
        return listaPeliculas.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):TextoViewHolder {
        val binding= ItemPeliculasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TextoViewHolder(binding)

    }

    override fun onBindViewHolder(holder: TextoViewHolder, position: Int) {

        holder.itemBinding.tvNombre.text= listaPeliculas[position].title


        holder.itemBinding.itemPeliculas.setOnClickListener{
            val personaje=listaPeliculas[position].characters
            val intent = Intent(holder.itemBinding.root.context, PersonajesActivity::class.java)
            intent.putExtra("PERSONAJE", personaje.toTypedArray())
            holder.itemBinding.root.context.startActivity(intent)
        }

    }


}