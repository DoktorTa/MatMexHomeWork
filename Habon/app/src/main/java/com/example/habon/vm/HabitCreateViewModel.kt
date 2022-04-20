package com.example.habon.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habon.db.HabitRepository
import kotlinx.coroutines.launch

class HabitCreateViewModel(
    private val repository: HabitRepository
): ViewModel() {

//    val habit: Habit

    companion object{
        fun newHabitCreateViewModel(repository: HabitRepository): HabitCreateViewModel{
            return HabitCreateViewModel(repository)
        }
    }
//
//    fun insertHabit(name: String,
//                    description: String,
//                    color: String,
//                    priority: String,
//                    group: String,
//                    periodRepeat: Int,
//                    countRepeat: Int) = let { viewModelScope.launch {
//        repository.insertHabit(name, description, color, priority, group, periodRepeat, countRepeat, )
//    } }
}