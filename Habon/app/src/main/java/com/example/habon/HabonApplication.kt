package com.example.habon

import android.app.Application
import com.example.habon.db.DataBaseHabits
import com.example.habon.db.HabitRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class HabonApplication: Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    lateinit var mInstance: HabonApplication

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val dataBase by lazy { DataBaseHabits.getDatabase(this, applicationScope) }
    val repository by lazy { HabitRepository(dataBase.habitDAO()) }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }

//    @Synchronized
//    fun getInstance(): HabonApplication? {
//        return mInstance
//    }

}