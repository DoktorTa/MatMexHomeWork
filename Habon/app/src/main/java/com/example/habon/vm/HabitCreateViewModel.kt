package com.example.habon.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habon.db.Habit
import com.example.habon.db.HabitRepository
import com.example.habon.uc.HabitUseCase
import kotlinx.coroutines.launch

class HabitCreateViewModel(
    private val useCase: HabitUseCase
): ViewModel() {

    lateinit var habit: Habit

    companion object{
        fun newHabitCreateViewModel(useCase: HabitUseCase): HabitCreateViewModel{
            return HabitCreateViewModel(useCase)
        }
    }

    fun loadDataHabit(habitNew: Habit){
        habit = habitNew
    }

    fun insertHabit(name: String,
                    description: String,
                    color: String,
                    priority: String,
                    group: String,
                    periodRepeat: Int,
                    countRepeat: Int) = let { viewModelScope.launch {
        useCase.insertHabit(name, description, color, priority, group, periodRepeat, countRepeat)
    } }
}