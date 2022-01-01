package com.kwekboss.mrjokes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.kwekboss.mrjokes.api.RetrofitInstance
import kotlinx.coroutines.*
import retrofit2.awaitResponse


class MainActivity : AppCompatActivity() {

    private lateinit var jokeText: TextView
    private lateinit var answerText: TextView
    private lateinit var checkAnswer: Button
    private  lateinit var newJoke: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //finding views by their id
        jokeText = findViewById(R.id.txtQuestion)
        answerText = findViewById(R.id.txtAnswer)
        checkAnswer = findViewById(R.id.btnCheckAnswer)
        newJoke = findViewById(R.id.btnNewJoke)
        progressBar = findViewById(R.id.progressBar)

        requestResponse()

        checkAnswer.setOnClickListener {
            answerText.visibility = View.VISIBLE
        }

        newJoke.setOnClickListener {
            requestResponse()
        }

    }


    private fun requestResponse() {
        jokeText.visibility = View.INVISIBLE
        answerText.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE


            GlobalScope.launch(Dispatchers.IO) {

                try {
                val response = RetrofitInstance.retrofit.getAllJokes().awaitResponse()
                if (response.isSuccessful) {
                    val accessGranted = response.body()!!
                    withContext(Dispatchers.Main) {
                        jokeText.visibility = View.VISIBLE
                        progressBar.visibility = View.INVISIBLE

                        jokeText.text = accessGranted.setup
                        answerText.text = accessGranted.delivery
                    }
                }
            }
                catch (e: Exception) {
                    Toast.makeText(applicationContext, "An error occurred.Might be your internet",
                        Toast.LENGTH_SHORT).show()
        }
        }
    }

}