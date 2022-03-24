package com.example.a002

import  kotlinx.serialization.Serializable


@Serializable
data class Habit(
    val name: String,
    val description: String,
    val color: String,
    val priority: String,
    val group: String,
    val periodRepeat: Int,
    var countRepeat: Int
)


