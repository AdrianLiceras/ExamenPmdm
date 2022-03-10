package com.examen.pmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.examen.pmdm.databinding.ActivityPersonajesBinding

class PersonajesActivity : AppCompatActivity() {
    lateinit var listaCharac:Array<String>
    private lateinit var binding: ActivityPersonajesBinding
    private lateinit var adapter:PersonajesAdapter
    private val viewModel: PersonajeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonajesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        initObserver()
        viewModel.getPersonajes()
        val personaje = intent.getStringArrayExtra("PERSONAJE")

    }

    private fun initObserver() {
        viewModel.isVisible.observe(this) { isVisible ->
            if (isVisible) setVisible() else setGone()
        }

        viewModel.listaPersonaje.observe(this) {
            if (viewModel.listaPersonaje.value != null)
                mostrarPersonaje(viewModel.listaPersonaje.value!!)
        }

    }

    fun mostrarPersonaje(personajes: List<People>) {
        adapter = PersonajesAdapter(personajes)
        binding.recyclerview.adapter = adapter
    }
    fun getPersonaje(personajes:Array<String>){

        this.listaCharac=personajes

    }
    private fun setVisible() {
        binding.pbDownloading.visibility = View.VISIBLE
    }

    private fun setGone() {
        binding.pbDownloading.visibility = View.GONE
    }
}

