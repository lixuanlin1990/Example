package com.lixlop.example.touch

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class TestView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.i("lixiaoyan","child ${event?.action}")
        if (event?.action == MotionEvent.ACTION_DOWN ){
            return false
        }
        return false
    }
}