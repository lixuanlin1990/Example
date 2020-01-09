package com.lixlop.example.touch

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.lixlop.example.R

class TouchActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.i("lixiaoyan","activity ${event?.action}")
        return super.onTouchEvent(event)
    }
}