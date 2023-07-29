package com.example.quotesapp


import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlin.random.Random

class MainViewModel(val context: Context) : ViewModel() {

    private var quoteList : Array<Quotes>
    private var index = 0

    init{
       quoteList = loadQuoteFromAssests()

    }

    private fun loadQuoteFromAssests(): Array<Quotes> {
        val inputStream = context.assets.open("Quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Quotes>::class.java)
    }

    fun getQuote() = quoteList[Random.nextInt(0, quoteList.size)]

    fun nextQuotes() = quoteList[Random.nextInt(0, quoteList.size)]

    fun previousQuotes() = quoteList[Random.nextInt(0, quoteList.size)]

}

class MainViewModelFactory(private val context : Context): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(context) as T
    }
}
