package com.examen.pmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.examen.pmdm.databinding.ActivityMainBinding

class MainActivity  : AppCompatActivity(){

    private lateinit var binding : ActivityMainBinding
    private lateinit var  adapter:PeopleAdapter
    private val viewModel : MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
        initObserver()
        viewModel.getPeople()

    }
    private fun initObserver() {
        viewModel.isVisible.observe(this) { isVisible ->
            if (isVisible) setVisible() else setGone()
        }

        viewModel.listaPeople.observe(this){
           if( viewModel.listaPeople.value!=null)
            mostrarPersonas(viewModel.listaPeople.value!!)
        }

    }
    fun mostrarPersonas(personas:List<People>){
        adapter= PeopleAdapter(personas)
        binding.recyclerview.adapter=adapter
    }
    private fun setVisible(){
        binding.pbDownloading.visibility = View.VISIBLE
    }
    private fun setGone(){
        binding.pbDownloading.visibility = View.GONE
    }


}