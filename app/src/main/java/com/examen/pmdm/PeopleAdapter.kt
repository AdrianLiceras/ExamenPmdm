package com.examen.pmdm

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.examen.pmdm.databinding.ItemPeopleBinding

class PeopleAdapter (var listaPeople : List<People>) : RecyclerView.Adapter<PeopleAdapter.TextoViewHolder>(){
    lateinit var people:Array<String>
    class TextoViewHolder(var itemBinding : ItemPeopleBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun getItemCount(): Int {
        return listaPeople.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):TextoViewHolder {
        val binding=ItemPeopleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TextoViewHolder(binding)

    }

    override fun onBindViewHolder(holder: TextoViewHolder, position: Int) {

        holder.itemBinding.tvNombre.text=listaPeople[position].name
        holder.itemBinding.itemPeople.setOnClickListener{
            people= listaPeople[position].films.toTypedArray()
            val intent = Intent(holder.itemBinding.root.context, PeliculasActivity::class.java)
            intent.putExtra("PERSONA", people)
            holder.itemBinding.root.context.startActivity(intent)
        }

    }

}