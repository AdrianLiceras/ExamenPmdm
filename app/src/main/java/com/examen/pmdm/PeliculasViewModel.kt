package com.examen.pmdm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.*
import okhttp3.*
import java.io.IOException

class PeliculasViewModel : ViewModel() {

    private lateinit var url:Array<String>
    private val _isVisible by lazy { MediatorLiveData<Boolean>() }
    val isVisible : LiveData<Boolean>
        get() = _isVisible

    private val _responseText by lazy { MediatorLiveData<String>() }
    val responseText : LiveData<String>
        get() = _responseText

    private val _listaPelis by lazy { MediatorLiveData<List<Film>>() }
    val listaPelis:LiveData<List<Film>>
        get()= _listaPelis

    private val _listaPeliPersonaje by lazy { MediatorLiveData<Array<String>> ()}
    val listaPeliPersonaje:LiveData<Array<String>>
        get()=_listaPeliPersonaje
    suspend fun setIsVisibleInMainThread(value : Boolean) = withContext(Dispatchers.Main){
        _isVisible.value = value
    }

    suspend fun setResponseTextInMainThread(value : String) = withContext(Dispatchers.Main){
        _responseText.value = value
    }


    fun getFilms(){

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                setIsVisibleInMainThread(true)


                val client = OkHttpClient()

                val request = Request.Builder()
                request.url("https://swapi.dev/api/films/")


                val call = client.newCall(request.build())
                call.enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        println(e.toString())
                        CoroutineScope(Dispatchers.Main).launch {
                            delay(2000)
                            setResponseTextInMainThread("Algo ha ido mal")
                            setIsVisibleInMainThread(false)
                        }

                    }

                    override fun onResponse(call: Call, response: Response) {
                        println(response.toString())
                        response.body?.let { responseBody ->
                            val body = responseBody.string()
                            println(body)
                            val gson = Gson()

                            val films = gson.fromJson(body, ResulFilms::class.java)

                            println(films)

                            CoroutineScope(Dispatchers.Main).launch {
                                delay(2000)
                                setIsVisibleInMainThread(false)
                                _listaPelis.value=films.results
                                _listaPeliPersonaje.value=url

                            }
                        }
                    }
                })
            }
        }


    }
    fun getUrl(url: Array<String>?){

        if (url != null) {
            this.url= url
        }

    }
}