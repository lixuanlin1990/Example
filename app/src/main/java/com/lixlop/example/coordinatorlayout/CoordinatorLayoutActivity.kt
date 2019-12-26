package com.lixlop.example.coordinatorlayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.LayoutHelper
import com.alibaba.android.vlayout.VirtualLayoutAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
import com.lixlop.example.R
import kotlinx.android.synthetic.main.activity_coordinatorlayout.*


class CoordinatorLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coordinatorlayout)
//        rvToDoList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
//        val layoutManager = VirtualLayoutManager(this)
//        val delegateAdapter = DelegateAdapter(layoutManager, false)
//        rvToDoList.layoutManager = layoutManager
//        rvToDoList.adapter = delegateAdapter
//        delegateAdapter.addAdapter(TestAdapter(50))
        floating_action_btn.setOnClickListener {
                Snackbar.make(main_content, "123456", LENGTH_SHORT)
                    .setAction("action") {
                        Snackbar.make(main_content, "Action 被点击", Snackbar.LENGTH_SHORT).show();
                    }
                    .show()
        }
        window.decorView
    }

    class TestAdapter(var count: Int) :
        DelegateAdapter.Adapter<TestViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
            return TestViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.rv_item,
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
            holder.bind(position)
        }

        override fun getItemCount(): Int {
            return count
        }

        override fun onCreateLayoutHelper(): LayoutHelper {
            return LinearLayoutHelper()
        }
    }

    class TestViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.view_flipper)
        fun bind(position: Int) {
            textView.text = "$position--->"
        }
    }
}