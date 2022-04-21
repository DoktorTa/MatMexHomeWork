package com.example.habon.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Habit(
    @PrimaryKey val name: String,
    val description: String?,
    val color: String?,
    val priority: String?,
    val group: String?,
    val periodRepeat: Int?,
    val countRepeat: Int?,
    var countRepeatLeft: Int?,
    var dataCreate: String?
) : Parcelable