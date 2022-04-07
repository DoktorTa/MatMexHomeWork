package com.example.a002.db;

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDAO {

    @Query("SELECT * FROM habit")
    fun getAll(): Flow<List<Habit>>

    @Query("SELECT * FROM habit WHERE `group` IN (:nameGroup)")
    fun getHabitsByFilterGroup(nameGroup: String): Flow<List<Habit>>

    @Query("SELECT * FROM habit WHERE name LIKE (:name)")
    fun findHabitByName(name: String): Habit

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertHabit(vararg habit: Habit)

    @Delete
    fun deleteHabit(habit: Habit): Unit

}
