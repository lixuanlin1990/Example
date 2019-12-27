package com.lixlop.example.nestedscroll

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lixlop.example.R
import kotlinx.android.synthetic.main.activity_nested_scroll.*
import kotlinx.android.synthetic.main.rv_item.view.*

class NestedScrollActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nested_scroll)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = TestAdapter()
    }

    class TestAdapter: RecyclerView.Adapter<TestViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
            return TestViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item,parent,false))
        }

        override fun getItemCount(): Int {
            return 100
        }

        override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
            holder.bind(position)
        }

    }
    class TestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int){
            itemView.view_flipper.text = "$position"
        }
    }
}