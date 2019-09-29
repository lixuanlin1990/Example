package com.lixlop.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch {
            val image = async {

            }
            val name = async {

            }
            withContext(Dispatchers.IO){

            }
            image.await()
        }
    }
}
