package com.example.a002

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class HabitsListActivity : AppCompatActivity() {

    private val namesPage: List<String> = listOf("Bad habit", "Good habit")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habits_list)

        val pager = findViewById<ViewPager2>(R.id.pager)
        val pageAdapter: FragmentStateAdapter = HabitPagerAdapter(this)
        pager.adapter = pageAdapter

        TabLayoutMediator(findViewById<TabLayout>(R.id.tabLayoutGroup), pager) {
                tab, position ->

                tab.text = namesPage[position]
            }.attach()

//        val navHost = supportFragmentManager
//            .findFragmentById(R.id.left_drawer) as NavHostFragment? ?: return
//        val navController = navHost.navController
//
//        val sideBar = findViewById<NavigationView>(R.id.left_drawer)
//        sideBar?.inflateHeaderView(R.layout.info_header_app)
//        sideBar?.setupWithNavController(navController)


//        if (savedInstanceState == null) {
//            supportFragmentManager
//                .beginTransaction()
//                .add(R.id.fragment_habits_list, HabitsListFragment())
//                .commit()
//        }

    }



}
