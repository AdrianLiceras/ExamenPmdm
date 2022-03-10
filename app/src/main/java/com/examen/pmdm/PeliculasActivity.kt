package com.examen.pmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.examen.pmdm.databinding.ActivityPeliculasBinding

class PeliculasActivity : AppCompatActivity() {
    private lateinit var binding:ActivityPeliculasBinding
    private lateinit var  adapter:PeliculasAdapter
    val viewModel :PeliculasViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPeliculasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val personaURL = intent.getStringArrayExtra("PERSONA")
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        initObserver()
        viewModel.getFilms()
        if (personaURL != null) {
            viewModel.getUrl(personaURL)
        }

    }

    fun mostrarPeliculas(personas:List<Film>/*,urlFilms:Array<String>*/){
        adapter= PeliculasAdapter(personas)
        binding.recyclerview.adapter=adapter
    }
    private fun initObserver() {
        viewModel.isVisible.observe(this) { isVisible ->
            if (isVisible) setVisible() else setGone()
        }
        viewModel.listaPelis.observe(this){listaPeli->
            if (viewModel.listaPelis.value!=null)
               mostrarPeliculas(viewModel.listaPelis.value!!)

        }
    }
    private fun setVisible(){
        binding.pbDownloading.visibility = View.VISIBLE
    }
    private fun setGone(){
        binding.pbDownloading.visibility = View.GONE
    }
}