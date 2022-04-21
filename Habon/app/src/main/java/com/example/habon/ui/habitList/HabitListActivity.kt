package com.example.habon.ui.habitList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.habon.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HabitListActivity : AppCompatActivity() {

    private val namesPage: List<String> = listOf("Bad habit", "Good habit")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit_list)

        val pager = findViewById<ViewPager2>(R.id.pager)
        val pageAdapter: FragmentStateAdapter = HabitListPageAdapter(this)
        pager.adapter = pageAdapter

        TabLayoutMediator(findViewById<TabLayout>(R.id.tabLayoutGroup), pager) {
                tab, position ->

            tab.text = namesPage[position]
        }.attach()


    }
}