package com.lixlop.example.nestedscroll

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lixlop.example.R
import kotlinx.android.synthetic.main.activity_nested_scroll.*
import kotlinx.android.synthetic.main.rv_item.view.*
import kotlinx.android.synthetic.main.rv_last_item.view.*

class NestedScrollActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nested_scroll)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = TestAdapter()
    }

    class TestAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return if (viewType == 0)
                TestViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.rv_item,
                        parent,
                        false
                    )
                )
            else
                TestViewHolder2(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.rv_last_item,
                        parent,
                        false
                    )
                )
        }

        override fun getItemViewType(position: Int): Int {
            if (position == 49) {
                return 1
            }
            return 0
        }

        override fun getItemCount(): Int {
            return 50
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (holder is TestViewHolder) {
                holder.bind(position)
            } else if (holder is TestViewHolder2) {

            }
        }
    }

    class TestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            itemView.view_flipper.text = "$position"
        }
    }

    class TestViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.text_view.setOnClickListener {
                Log.i("lixiaoyan","setOnClickListener")
            }
            itemView.child_recycler_view.layoutManager = LinearLayoutManager(itemView.context)
            itemView.child_recycler_view.adapter = ChildTestAdapter()
        }

        fun bind(position: Int) {

        }
    }

    class ChildTestAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return TestViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.rv_item,
                    parent,
                    false
                )
            )
        }

        override fun getItemCount(): Int {
            return 50
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (holder is TestViewHolder) {
                holder.bind(position)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val result = super.onTouchEvent(event)
        Log.i("lixuanlin", "activity onTouchEvent $result")
        return result
    }
}