package com.example.habon.uc

import com.example.habon.db.Habit
import com.example.habon.db.HabitRepository
import com.example.habon.util.Group
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class HabitUseCase(private val repository: HabitRepository) {

    private val dataFormat: SimpleDateFormat = SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH)
    private val nowData = dataFormat.parse(LocalDate.now().toString())

    // TODO: async
    fun getAllHabitOnPage(nameGroup: String): Flow<List<Habit>> {
        return updatePeriodAllHabit(repository.getHabitsByGroup(nameGroup))
    }

    private fun updatePeriodAllHabit(allHabit: Flow<List<Habit>>): Flow<List<Habit>>{
        allHabit.map { habit ->
            habit.forEach {
                if (getDiffDataInDay(it.dataCreate!!) >= it.periodRepeat!!) {
                    it.countRepeatLeft = it.countRepeat
                    it.dataCreate = nowData!!.toString()
                }
            }
        }
        return allHabit
    }

    private fun getDiffDataInDay(dataCreate: String): Int{
        return (dataFormat.parse(dataCreate)!!.time - nowData!!.time / 24 * 60 * 60 * 1000).toInt()
    }

    suspend fun searchHabitFromNameByEntry(name: String, nameGroup: String): Flow<List<Habit>>{
        return repository.searchHabitFromNameByEntry(name, nameGroup)
    }

    suspend fun deleteHabit(habit: Habit) {
        repository.deleteHabit(habit)
    }

    suspend fun insertHabit(name: String,
                            description: String,
                            color: String,
                            priority: String,
                            group: String,
                            periodRepeat: Int,
                            countRepeat: Int){
        repository.insertHabit(
            name,
            description,
            color,
            priority,
            group,
            periodRepeat,
            countRepeat,
            countRepeat,
            nowData!!.toString())
    }

    suspend fun changeHabitCountRepeat(habit: Habit, count: Int): String{
        habit.countRepeatLeft = habit.countRepeatLeft!! + count
        repository.changeCountRepeatHabit(habit)
        return getControlText(habit)
    }

    //TODO: Как получить здесь R.string
    private fun getControlText(habit: Habit): String{
        if (habit.group == Group.Bad.name){
            if (habit.countRepeatLeft!! <= 0){
                return "Ты хватит это делать, помрешь не дай бог"
            } else {
                return "Ты же не будешь это делать еще ${habit.countRepeatLeft} раз?"
            }
        } else {
            if (habit.countRepeatLeft!! <= 0){
                return "Хорошей привычки много не бывает. Ведь так?"
            } else {
                return "А нука бегом сделай еще ${habit.countRepeatLeft} раз!"
            }
        }
    }


}