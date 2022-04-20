package com.example.habon.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.habon.util.Group
import com.example.habon.util.Priority
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
                    .addCallback(WordDatabaseCallback(scope))
                    .build()

                INSTANCE = instance

                instance
            }
        }

        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.habitDAO())
                    }
                }
            }
        }

        suspend fun populateDatabase(habitDAO: HabitDAO) {
            habitDAO.insertHabit(Habit("1", "kernel", "#FF0000", Priority.Medium.name, Group.Good.name, 14, 2, 2,"2018-12-06"))
            habitDAO.insertHabit(Habit("2", "Herna", "#00FF00", Priority.Hard.name, Group.Bad.name, 7, 1, 1,"2018-12-06"))
            habitDAO.insertHabit(Habit("3", "Verna", "#0000FF", Priority.Easy.name, Group.Good.name, 1, 20, 20,"2018-12-06"))
            habitDAO.insertHabit(Habit("4", "kernel", "#FF0000", Priority.Medium.name, Group.Bad.name, 14, 1, 1,"2018-12-06"))
            habitDAO.insertHabit(Habit("5", "Herna", "#00FF00", Priority.Hard.name,  Group.Bad.name, 7, 7, 7,"2018-12-06"))
            habitDAO.insertHabit(Habit("6", "Verna", "#0000FF", Priority.Easy.name, Group.Good.name, 1, 20, 20,"2018-12-06"))
            habitDAO.insertHabit(Habit("7", "kernel", "#FF0000", Priority.Medium.name, Group.Bad.name, 14, 1, 1,"2018-12-06"))
            habitDAO.insertHabit(Habit("8", "Herna", "#00FF00", Priority.Hard.name, Group.Bad.name, 7, 7, 7,"2018-12-06"))
            habitDAO.insertHabit(Habit("9", "Verna", "#0000FF", Priority.Easy.name, Group.Bad.name, 1, 20, 20,"2018-12-06"))
            habitDAO.insertHabit(Habit("10", "kernel", "#FF0000", Priority.Medium.name,  Group.Bad.name, 14, 1, 1,"2018-12-06"))
            habitDAO.insertHabit(Habit("11", "Herna", "#00FF00", Priority.Hard.name, Group.Bad.name, 7, 7, 7,"2018-12-06"))
            habitDAO.insertHabit(Habit("12", "Verna", "#0000FF", Priority.Easy.name, Group.Bad.name, 1, 20, 20,"2018-12-06"))
            habitDAO.insertHabit(Habit("13", "kernel", "#FF0000", Priority.Medium.name, Group.Bad.name, 14, 1, 1,"2018-12-06"))
            habitDAO.insertHabit(Habit("14", "Herna", "#00FF00", Priority.Hard.name,  Group.Bad.name, 7, 7, 7,"2018-12-06"))
            habitDAO.insertHabit(Habit("15", "Verna", "#0000FF", Priority.Easy.name, Group.Good.name, 1, 20, 20,"2018-12-06"))
            habitDAO.insertHabit(Habit("16", "kernel", "#FF0000", Priority.Medium.name, Group.Bad.name, 14, 1, 1,"2018-12-06"))
            habitDAO.insertHabit(Habit("17", "Herna", "#00FF00", Priority.Hard.name, Group.Bad.name, 7, 7, 7,"2018-12-06"))
            habitDAO.insertHabit(Habit("18", "Verna", "#0000FF", Priority.Easy.name,  Group.Good.name, 1, 20, 20,"2018-12-06"))
            habitDAO.insertHabit(Habit("19", "kernel", "#FF0000", Priority.Medium.name, Group.Bad.name, 14, 1, 1,"2018-12-06"))
            habitDAO.insertHabit(Habit("20", "Herna", "#00FF00", Priority.Hard.name, Group.Bad.name, 7, 7, 7,"2018-12-06"))
            habitDAO.insertHabit(Habit("21", "Verna", "#0000FF", Priority.Easy.name,  Group.Bad.name, 1, 20, 20,"2018-12-06"))
            habitDAO.insertHabit(Habit("22", "kernel", "#FF0000", Priority.Medium.name, Group.Bad.name, 14, 1, 1,"2018-12-06"))
            habitDAO.insertHabit(Habit("23", "Herna", "#00FF00", Priority.Hard.name, Group.Bad.name, 7, 7, 7,"2018-12-06"))
            habitDAO.insertHabit(Habit("24", "Verna", "#0000FF", Priority.Easy.name, Group.Bad.name, 1, 20, 20,"2018-12-06"))
        }
    }
}
