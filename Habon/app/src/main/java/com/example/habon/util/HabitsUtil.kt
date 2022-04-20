package com.example.habon.util

class HabitsUtil {
}

fun getListGroupName(): List<String> = Group.values().map{it.name}
fun getListPriorityName(): List<String> = Priority.values().map{it.name}

enum class Priority{
    Hard,
    Medium,
    Easy
}

enum class Group{
    Bad,
    Good
}