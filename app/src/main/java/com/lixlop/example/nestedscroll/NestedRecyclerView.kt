package com.lixlop.example.nestedscroll

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.view.NestedScrollingParent2
import androidx.recyclerview.widget.RecyclerView

class NestedRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr),NestedScrollingParent2 {
    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        Log.i("lixiaoyan", "onStartNestedScroll")
        return false
    }

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int, type: Int) {
        Log.i("lixiaoyan", "onNestedScrollAccepted")
    }

    override fun onStopNestedScroll(target: View, type: Int) {
        Log.i("lixiaoyan", "onStopNestedScroll")
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        Log.i("lixiaoyan", "onNestedPreScroll")
    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
        Log.i("lixiaoyan", "onNestedScroll")
    }

    override fun hasNestedScrollingParent(type: Int): Boolean {
        val result =super.hasNestedScrollingParent(type)
            Log.i("lixiaoyan", "hasNestedScrollingParent $result")
        return result
    }

    override fun startNestedScroll(axes: Int, type: Int): Boolean {
        val result = super.startNestedScroll(axes, type)
        Log.i("lixiaoyan", "startNestedScroll $result")
        return result
    }

    override fun stopNestedScroll() {
        Log.i("lixiaoyan", "stopNestedScroll")
        super.stopNestedScroll()
    }

    override fun dispatchNestedPreScroll(
        dx: Int,
        dy: Int,
        consumed: IntArray?,
        offsetInWindow: IntArray?,
        type: Int
    ): Boolean {
        val result = super.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type)
        Log.i("lixiaoyan", "dispatchNestedPreScroll dx $dx dy $dy consumed ${consumed?.get(0)} ${consumed?.get(1)} $result")
        return result
    }

    override fun dispatchNestedScroll(
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        offsetInWindow: IntArray?,
        type: Int
    ): Boolean {
        val result = super.dispatchNestedScroll(
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            offsetInWindow,
            type
        )
        Log.i("lixiaoyan", "dispatchNestedScroll dxConsumed $dxConsumed dyConsumed $dyConsumed dxUnconsumed $dxUnconsumed dyUnconsumed $dyUnconsumed $result")
        return result
    }

    override fun dispatchNestedPreFling(velocityX: Float, velocityY: Float): Boolean {
        val result = super.dispatchNestedPreFling(velocityX, velocityY)
        Log.i("lixiaoyan", "dispatchNestedPreFling $result")
        return result
    }

    override fun dispatchNestedFling(
        velocityX: Float,
        velocityY: Float,
        consumed: Boolean
    ): Boolean {
        val result = super.dispatchNestedFling(velocityX, velocityY, consumed)
        Log.i("lixiaoyan", "dispatchNestedFling $result")
        return result
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.i("lixuanlin", "parent dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        val result = super.onInterceptTouchEvent(e)
        Log.i("lixuanlin", "parent onInterceptTouchEvent $result ${e?.action}")
        return result
    }
    override fun onTouchEvent(e: MotionEvent?): Boolean {
        val result = super.onTouchEvent(e)
        Log.i("lixuanlin", "parent onTouchEvent $result ${e?.action}")
        return result
    }
}