package com.example.habon.db

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHabit(vararg habit: Habit): Unit

    @Delete
    fun deleteHabit(habit: Habit): Unit

    @Update
    fun changeCountRepeatHabit(habit: Habit): Unit

    @Query("SELECT * FROM habit WHERE `name` IN (:name) AND `group` LIKE (:nameGroup)")
    fun searchHabitFromNameByEntry(name: String, nameGroup: String): Flow<List<Habit>>
}
