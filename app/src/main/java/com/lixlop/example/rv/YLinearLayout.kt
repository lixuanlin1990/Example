package com.lixlop.example.rv

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.lixlop.example.R

/**
 * 提前初始化 YLinearLayout
 * @author fangkw on 2019-05-29
 **/
class YLinearLayout(context: Context) : LinearLayout(context) {

    private var viewPager: ViewPager? = null
    val innerRecyclerView: RecyclerView?
        get() {
            return viewPager?.findViewWithTag("inner_${viewPager?.currentItem}")
        }

    init {
        orientation = VERTICAL
        View.inflate(context, R.layout.item_sub, this)
        initView()
    }

    private fun initView() {
        val fragmentManager = (context as AppCompatActivity).supportFragmentManager
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        viewPager = findViewById(R.id.vp_pager)
        tabLayout.addTab(tabLayout.newTab().setText("0"))
        tabLayout.addTab(tabLayout.newTab().setText("1"))
        tabLayout.addTab(tabLayout.newTab().setText("2"))
        tabLayout.addTab(tabLayout.newTab().setText("3"))
        tabLayout.addTab(tabLayout.newTab().setText("4"))
        tabLayout.addTab(tabLayout.newTab().setText("5"))
        tabLayout.addTab(tabLayout.newTab().setText("6"))
        tabLayout.addTab(tabLayout.newTab().setText("7"))
        // TODO: fangkw 2019-06-02  setupWithViewPager 会导致轻微卡顿感
        tabLayout.setupWithViewPager(viewPager)

        viewPager?.adapter = PAdapter(fragmentManager, RecyclerView.RecycledViewPool())
        viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {

            }
        })
    }

    class PAdapter(fragmentManager: FragmentManager, pool: RecyclerView.RecycledViewPool?) :
        FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val recyclerViewPool = pool

        override fun getItem(position: Int): Fragment {
            return TestFragment.getInstance(position)
        }

        override fun getCount(): Int {
            return 8
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return position.toString()
        }
    }
}