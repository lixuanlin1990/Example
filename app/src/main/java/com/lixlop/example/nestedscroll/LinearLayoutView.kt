package com.lixlop.example.nestedscroll

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.NestedScrollingParent

class LinearLayoutView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), NestedScrollingParent {

    override fun onStartNestedScroll(child: View, target: View, axes: Int): Boolean {
        Log.i("lixiaoyan","onStartNestedScroll")
        return true
    }

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int) {
        Log.i("lixiaoyan","onNestedScrollAccepted")
    }

    override fun onStopNestedScroll(target: View) {
        Log.i("lixiaoyan","onStopNestedScroll")
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray) {
        Log.i("lixiaoyan","onNestedPreScroll")
    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int
    ) {
        Log.i("lixiaoyan","onNestedScroll")
    }

    override fun onNestedPreFling(target: View, velocityX: Float, velocityY: Float): Boolean {
        Log.i("lixiaoyan","onNestedPreFling")
        return false
    }

    override fun onNestedFling(
        target: View,
        velocityX: Float,
        velocityY: Float,
        consumed: Boolean
    ): Boolean {
        Log.i("lixiaoyan","onNestedFling")
        return false
    }
}