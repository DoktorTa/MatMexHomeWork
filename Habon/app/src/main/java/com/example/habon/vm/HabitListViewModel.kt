package com.example.habon.vm

import androidx.lifecycle.*
import com.example.habon.db.Habit
import com.example.habon.db.HabitRepository
import com.example.habon.uc.HabitUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HabitListViewModel (
    private val habitUseCase: HabitUseCase,
    private val nameGroupOnThePage: String
): ViewModel(){

    companion object{
        fun newHabitViewModel(
            habitUseCase: HabitUseCase,
            nameGroupOnThePage: String)
        : HabitListViewModel{
            return HabitListViewModel(habitUseCase , nameGroupOnThePage)
        }
    }

    var textToast: MutableLiveData<String> = MutableLiveData("Hello")

    val allHabitOnThePage: LiveData<List<Habit>> = habitUseCase
        .getAllHabitOnPage(nameGroupOnThePage)
        .asLiveData()

    fun deleteHabit(habit: Habit) = let { viewModelScope.launch {
        habitUseCase.deleteHabit(habit)
    } }

    fun changePeriodRepeatHabit(habit: Habit, count: Int) = let { viewModelScope.async {
        val textToastHabit: String = habitUseCase.changeHabitCountRepeat(habit, count)
        textToast.value = textToastHabit
    } }
}
