package com.example.quotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.quotesapp.databinding.ActivityMainBinding
import org.json.JSONArray
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel : MainViewModel
    private lateinit var binding : ActivityMainBinding
    private lateinit var list : ArrayList<Quotes>

    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(application))[MainViewModel::class.java]
        setQuote(mainViewModel.getQuote())
    }

    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text)
        startActivity(intent)
    }

    fun setQuote(quote : Quotes){
           binding.quoteText.text = quote.text
           binding.quoteAuthor.text = quote.author
       }

    fun onNext(view: View) {
        setQuote(mainViewModel.nextQuotes())
    }

    fun onPrevious(view: View) {
        setQuote(mainViewModel.previousQuotes())
    }





}