package com.example.habon.db

import androidx.annotation.WorkerThread
import com.example.habon.db.Habit
import com.example.habon.db.HabitDAO
import com.example.habon.util.Group
import com.example.habon.util.Priority
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class HabitRepository (
    private val habitDAO: HabitDAO
) {

    val allHabits: Flow<List<Habit>> = habitDAO.getAll()

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
                            data: String) {
        val habit = Habit(name, description, color, priority, group, periodRepeat, countRepeat, countRepeat, data)
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

}