package com.example.a002

import android.util.Log


object HabitService {
    private val habits: MutableList<Habit> = mutableListOf()


    private val habitsGood: MutableList<Habit> = mutableListOf()
    private val habitsBad: MutableList<Habit> = mutableListOf()

    fun getListPriorityName(): List<String> = Priority.values().contentToString().replace("[", "").replace("]", "").split(", ")
    fun getListGroupsName(): List<String> = Groups.values().contentDeepToString().replace("[", "").replace("]", "").split(", ")

    enum class Priority{
        HARD,
        EASY,
    }


    enum class Groups(name: String){
        Bad("Sport"),
        Good("Smoked"),

    }


    init{
        createHabit("1", "kernel", "#FF0000", Priority.HARD.name, Groups.Bad.name, 14, 0)
        createHabit("2", "Herna", "#00FF00", Priority.EASY.name, Groups.Good.name, 7, 1)
        createHabit("3", "Verna", "#0000FF", Priority.HARD.name, Groups.Bad.name, 1, 20)
        createHabit("4", "kernel", "#FF0000", Priority.EASY.name, Groups.Good.name, 14, 1)
        createHabit("5", "Herna", "#00FF00", Priority.HARD.name,  Groups.Bad.name, 7, 7)
        createHabit("6", "Verna", "#0000FF", Priority.EASY.name, Groups.Bad.name, 1, 20)
        createHabit("7", "kernel", "#FF0000", Priority.HARD.name, Groups.Bad.name, 14, 1)
        createHabit("8", "Herna", "#00FF00", Priority.EASY.name, Groups.Good.name, 7, 7)
        createHabit("9", "Verna", "#0000FF", Priority.HARD.name, Groups.Bad.name, 1, 20)
        createHabit("10", "kernel", "#FF0000", Priority.EASY.name,  Groups.Bad.name, 14, 1)
        createHabit("11", "Herna", "#00FF00", Priority.HARD.name, Groups.Good.name, 7, 7)
        createHabit("12", "Verna", "#0000FF", Priority.EASY.name, Groups.Good.name, 1, 20)
        createHabit("13", "kernel", "#FF0000", Priority.HARD.name, Groups.Bad.name, 14, 1)
        createHabit("14", "Herna", "#00FF00", Priority.EASY.name,  Groups.Good.name, 7, 7)
        createHabit("15", "Verna", "#0000FF", Priority.HARD.name, Groups.Bad.name, 1, 20)
        createHabit("16", "kernel", "#FF0000", Priority.EASY.name, Groups.Good.name, 14, 1)
        createHabit("17", "Herna", "#00FF00", Priority.HARD.name, Groups.Good.name, 7, 7)
        createHabit("18", "Verna", "#0000FF", Priority.EASY.name,  Groups.Bad.name, 1, 20)
        createHabit("19", "kernel", "#FF0000", Priority.HARD.name, Groups.Bad.name, 14, 1)
        createHabit("20", "Herna", "#00FF00", Priority.EASY.name, Groups.Bad.name, 7, 7)
        createHabit("21", "Verna", "#0000FF", Priority.HARD.name,  Groups.Bad.name, 1, 20)
        createHabit("22", "kernel", "#FF0000", Priority.EASY.name, Groups.Bad.name, 14, 1)
        createHabit("23", "Herna", "#00FF00", Priority.HARD.name, Groups.Bad.name, 7, 7)
        createHabit("24", "Verna", "#0000FF", Priority.EASY.name, Groups.Good.name, 1, 20)
    }

    fun createHabit(name: String, description: String, color: String, priority: String, group: String, periodRepeat: Int, countRepeat: Int){
        if (group == Groups.Good.name){
            habitsGood.add(Habit(name, description, color, priority, group, periodRepeat, countRepeat))
        } else {
            habitsBad.add(Habit(name, description, color, priority, group, periodRepeat, countRepeat))
        }

//        sortHabitsByPriority()
    }

    private fun sortHabitsByPriority(){
        habits.sortWith(
            compareBy{it.group}
        )
    }

    fun getHabit(position: Int, filterByGroup: String): Habit{
        if (filterByGroup == Groups.Good.name){
            return habitsGood[position]
        } else {
            return habitsBad[position]
        }
    }

    fun deleteHabit(habit: Habit){

        if (habit.group == Groups.Good.name){
            habitsGood.removeAt(habitsGood.indexOf(habit))
        } else {
            habitsBad.removeAt(habitsBad.indexOf(habit))
        }
    }

    fun getSizeWithFilterGroup(nameGroup: String): Int{
        if (nameGroup == Groups.Good.name){
            return habitsGood.size
        } else {
            return habitsBad.size
        }
    }

    fun onHabitCountChange(habit: Habit, countRepeatChange: Int){
        habit.countRepeat += countRepeatChange
    }

}