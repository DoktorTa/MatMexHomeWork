package com.example.habon.db

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class HabitRepository (
    private val habitDAO: HabitDAO
) {

    fun getHabitsByGroup(nameGroup: String): Flow<List<Habit>> {
        return habitDAO.getHabitsByFilterGroup(nameGroup)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertHabit(name: String,
                            description: String,
                            color: String,
                            priority: String,
                            group: String,
                            periodRepeat: Int,
                            countRepeat: Int,
                            countRepeatLeft: Int,
                            data: String) {
        val habit = Habit(name, description, color, priority, group, periodRepeat, countRepeat, countRepeatLeft, data)
        habitDAO.insertHabit(habit)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteHabit(habit: Habit) {
        habitDAO.deleteHabit(habit)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun changeCountRepeatHabit(habit: Habit) {
        habitDAO.changeCountRepeatHabit(habit)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun searchHabitFromNameByEntry(name: String, nameGroup: String): Flow<List<Habit>> {
        return habitDAO.searchHabitFromNameByEntry(name, nameGroup)
    }
}