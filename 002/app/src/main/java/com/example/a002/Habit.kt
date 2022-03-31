package com.example.a002

import android.os.Parcelable
import  kotlinx.serialization.Serializable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Habit(
    val name: String,
    val description: String,
    val color: String,
    val priority: String,
    val group: String,
    val periodRepeat: Int,
    var countRepeat: Int
) : Parcelable


