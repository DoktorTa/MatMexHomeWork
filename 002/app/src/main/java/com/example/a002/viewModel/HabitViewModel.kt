package com.example.a002.viewModel

import androidx.lifecycle.*
import com.example.a002.HabitRepository
import com.example.a002.db.Habit
import kotlinx.coroutines.launch

class HabitViewModel(
    private val repository: HabitRepository,
    private val nameGroupOnThePage: String
    ): ViewModel(){

    val allHabitOnThePage: LiveData<List<Habit>> = repository
        .getHabitByGroup(nameGroupOnThePage)
        .asLiveData()

    fun insertHabit(habit: Habit) = let { viewModelScope.launch {
        repository.insertHabit(habit)
    } }

    fun deleteHabit(habit: Habit) = let { viewModelScope.launch {
        repository.deleteHabit(habit)
    } }
}

class HabitViewModelFactory(
    private val repository: HabitRepository,
    private val nameGroupOnThePage: String
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HabitViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HabitViewModel(repository, nameGroupOnThePage) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}