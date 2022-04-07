package com.example.a002

import android.app.Application
import com.example.a002.db.DataBaseHabits
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class HabitsApp: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val dataBaseHabits by lazy { DataBaseHabits.getDatabase(this, applicationScope) }
    val repository by lazy { HabitRepository(dataBaseHabits.habitDAO()) }

}