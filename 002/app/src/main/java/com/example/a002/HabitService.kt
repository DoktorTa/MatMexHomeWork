package com.example.a002


object HabitService {
    private val habits: MutableList<Habit> = mutableListOf()

    fun getListPriorityName(): List<String> = Priority.values().contentToString().replace("[", "").replace("]", "").split(", ")
    fun getListGroupsName(): List<String> = Groups.values().contentDeepToString().replace("[", "").replace("]", "").split(", ")

    enum class Priority{
        HARD,
        MEDIUM,
        EASY
    }


    enum class Groups(name: String){
        SPORT("Sport"),
        SMOKED("Smoked"),
        WORK("Work"),
        EAT("Eat")
    }


//    init{
//        createHabit("Red", "kernel", "#FF0000", Priority.MEDIUM.name, Groups.SMOKED.name, 14, 0)
//        createHabit("Green", "Herna", "#00FF00", Priority.HARD.name, Groups.SPORT.name, 7, 1)
//        createHabit("Blue", "Verna", "#0000FF", Priority.EASY.name, Groups.WORK.name, 1, 20)
//        createHabit("Red", "kernel", "#FF0000", Priority.MEDIUM.name, Groups.WORK.name, 14, 1)
//        createHabit("Green", "Herna", "#00FF00", Priority.HARD.name,  Groups.EAT.name, 7, 7)
//        createHabit("Blue", "Verna", "#0000FF", Priority.EASY.name, Groups.WORK.name, 1, 20)
//        createHabit("Red", "kernel", "#FF0000", Priority.MEDIUM.name, Groups.SPORT.name, 14, 1)
//        createHabit("Green", "Herna", "#00FF00", Priority.HARD.name, Groups.EAT.name, 7, 7)
//        createHabit("Blue", "Verna", "#0000FF", Priority.EASY.name, Groups.SPORT.name, 1, 20)
//        createHabit("Red", "kernel", "#FF0000", Priority.MEDIUM.name,  Groups.SMOKED.name, 14, 1)
//        createHabit("Green", "Herna", "#00FF00", Priority.HARD.name, Groups.EAT.name, 7, 7)
//        createHabit("Blue", "Verna", "#0000FF", Priority.EASY.name, Groups.WORK.name, 1, 20)
//        createHabit("Red", "kernel", "#FF0000", Priority.MEDIUM.name, Groups.EAT.name, 14, 1)
//        createHabit("Green", "Herna", "#00FF00", Priority.HARD.name,  Groups.SMOKED.name, 7, 7)
//        createHabit("Blue", "Verna", "#0000FF", Priority.EASY.name, Groups.WORK.name, 1, 20)
//        createHabit("Red", "kernel", "#FF0000", Priority.MEDIUM.name, Groups.SPORT.name, 14, 1)
//        createHabit("Green", "Herna", "#00FF00", Priority.HARD.name, Groups.EAT.name, 7, 7)
//        createHabit("Blue", "Verna", "#0000FF", Priority.EASY.name,  Groups.SMOKED.name, 1, 20)
//        createHabit("Red", "kernel", "#FF0000", Priority.MEDIUM.name, Groups.EAT.name, 14, 1)
//        createHabit("Green", "Herna", "#00FF00", Priority.HARD.name, Groups.EAT.name, 7, 7)
//        createHabit("Blue", "Verna", "#0000FF", Priority.EASY.name,  Groups.SMOKED.name, 1, 20)
//        createHabit("Red", "kernel", "#FF0000", Priority.MEDIUM.name, Groups.EAT.name, 14, 1)
//        createHabit("Green", "Herna", "#00FF00", Priority.HARD.name, Groups.SPORT.name, 7, 7)
//        createHabit("Blue", "Verna", "#0000FF", Priority.EASY.name, Groups.WORK.name, 1, 20)
//    }

    fun createHabit(name: String, description: String, color: String, priority: String, group: String, periodRepeat: Int, countRepeat: Int){
        habits.add(Habit(name, description, color, priority, group, periodRepeat, countRepeat))
        sortHabitsByPriority()
    }

    private fun sortHabitsByPriority(){
        habits.sortWith(
            compareBy{it.priority}
        )
    }

    fun getHabit(position: Int): Habit{
        return habits[position]
    }

    fun deleteHabit(habit: Habit){
        habits.removeAt(habits.indexOf(habit))
    }

    fun getSize(): Int{
        return habits.size
    }

    fun onHabitCountChange(habit: Habit, countRepeatChange: Int){
        habit.countRepeat += countRepeatChange
    }

}