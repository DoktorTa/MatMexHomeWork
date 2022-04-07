package com.example.a002.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.a002.ui.HabitsListFragment

class HabitPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> {
                return HabitsListFragment.newInstance("Bad", "Bad")
            }
            else -> {
                return HabitsListFragment.newInstance("Good","Good")
            }
        }
    }

}