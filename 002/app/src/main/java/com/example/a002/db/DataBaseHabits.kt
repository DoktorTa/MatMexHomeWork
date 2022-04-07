package com.example.a002.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Habit::class], version = 1)
abstract class DataBaseHabits: RoomDatabase() {

    abstract fun habitDAO(): HabitDAO

    companion object {

        @Volatile
        private var INSTANCE: DataBaseHabits? = null

        fun getDatabase(context: Context, scope: CoroutineScope): DataBaseHabits {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBaseHabits::class.java,
                    "word_database"
                )
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance

                instance
            }
        }
    }
}
