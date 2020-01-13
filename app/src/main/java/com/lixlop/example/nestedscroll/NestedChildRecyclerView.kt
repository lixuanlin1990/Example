package com.lixlop.example.nestedscroll

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.view.NestedScrollingParent2
import androidx.recyclerview.widget.RecyclerView
import android.view.GestureDetector
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.icu.lang.UCharacter.GraphemeClusterBreak.T







class NestedChildRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {
    var mDetector: GestureDetector? = null
    protected val FLIP_DISTANCE = 50f
    protected var up = false
    init {
        overScrollMode = OVER_SCROLL_NEVER
        mDetector = GestureDetector(context,object :GestureDetector.OnGestureListener{
            override fun onShowPress(e: MotionEvent?) {
            }

            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                return false
            }

            override fun onDown(e: MotionEvent?): Boolean {
                return false
            }

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent?,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                if (e1!!.x - e2!!.x > FLIP_DISTANCE) {
                    Log.i("MYTAG", "向左滑...")
                    return true
                }
                if (e2.x - e1.x > FLIP_DISTANCE) {
                    Log.i("MYTAG", "向右滑...")
                    return true
                }
                if (e1.y - e2.y > FLIP_DISTANCE) {
                    Log.i("MYTAG", "向上滑...")
                    up = true
                    return true
                }
                if (e2.y - e1.y > FLIP_DISTANCE) {
                    Log.i("MYTAG", "向下滑...")
                    up = false
                    return true
                }
                return false
            }

            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent?,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                return false
            }

            override fun onLongPress(e: MotionEvent?) {

            }

        })
    }
    override fun hasNestedScrollingParent(type: Int): Boolean {
        val result =super.hasNestedScrollingParent(type)
            Log.i("lixiaoyan", "child hasNestedScrollingParent $result")
        return result
    }

    override fun startNestedScroll(axes: Int, type: Int): Boolean {
        val result = super.startNestedScroll(axes, type)
        Log.i("lixiaoyan", "child startNestedScroll $result")
        return result
    }

    override fun stopNestedScroll() {
        Log.i("lixiaoyan", "child stopNestedScroll")
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
        Log.i("lixiaoyan", "child dispatchNestedPreScroll dx $dx dy $dy consumed ${consumed?.get(0)} ${consumed?.get(1)} $result")
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
        Log.i("lixiaoyan", "child dispatchNestedScroll dxConsumed $dxConsumed dyConsumed $dyConsumed dxUnconsumed $dxUnconsumed dyUnconsumed $dyUnconsumed $result")
        return result
    }

    override fun dispatchNestedPreFling(velocityX: Float, velocityY: Float): Boolean {
        val result = super.dispatchNestedPreFling(velocityX, velocityY)
        Log.i("lixiaoyan", "child dispatchNestedPreFling $result")
        return result
    }

    override fun dispatchNestedFling(
        velocityX: Float,
        velocityY: Float,
        consumed: Boolean
    ): Boolean {
        val result = super.dispatchNestedFling(velocityX, velocityY, consumed)
        Log.i("lixiaoyan", "child dispatchNestedFling $result")
        return result
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.i("lixuanlin", "child dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        val result = super.onInterceptTouchEvent(e)
        Log.i("lixuanlin", "child onInterceptTouchEvent $result ${e?.action}")
        return result
    }
    override fun onTouchEvent(e: MotionEvent?): Boolean {
        val result = super.onTouchEvent(e)
        Log.i("lixuanlin", "child onTouchEvent $result ${e?.action}")
        return result
    }
}