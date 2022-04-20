package com.example.habon.ui.habitList

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val COUNT_PAGE: Int = 2

class HabitListPageAdapter (fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int = COUNT_PAGE

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> {
                return HabitsListFragment.newInstance("Bad")
            }
            else -> {
                return HabitsListFragment.newInstance("Good")
            }
        }
    }
}