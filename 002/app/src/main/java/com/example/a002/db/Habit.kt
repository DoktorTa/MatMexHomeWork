package com.example.a002.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Habit(
    @PrimaryKey val name: String,
    val description: String?,
    val color: String?,
    val priority: String?,
    val group: String?,
    val periodRepeat: Int?,
    var countRepeat: Int?
)