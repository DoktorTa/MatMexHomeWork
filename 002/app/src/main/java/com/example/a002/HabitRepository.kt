package com.example.a002

import androidx.annotation.WorkerThread
import com.example.a002.db.Habit
import com.example.a002.db.HabitDAO
import kotlinx.coroutines.flow.Flow

class HabitRepository(private val habitDAO: HabitDAO) {

    val allHabits: Flow<List<Habit>> = habitDAO.getAll()

    fun getHabitByGroup(nameGroup: String): Flow<List<Habit>>{
        return habitDAO.getHabitsByFilterGroup(nameGroup)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertHabit(habit: Habit) {
        habitDAO.insertHabit(habit)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteHabit(habit: Habit){
        habitDAO.deleteHabit(habit)
    }




}