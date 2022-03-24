package com.example.a002

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a002.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var adapter: HabitAdapter = HabitAdapter(this)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val linerLayout = LinearLayoutManager(this)

        binding.habitsRecycle.layoutManager = linerLayout
        binding.habitsRecycle.adapter = adapter

    }



    fun transitionToHelp(view: View){
        val intent = Intent(this, HelpActivity::class.java)
        startActivity(intent)
    }

    fun translationToCreateHabit(view: View){
        val intent = Intent(this, CreateNewHabitActivity::class.java)
        startActivity(intent)
    }

    fun translationToEditHabit(habit: Habit){
        val intent = Intent(this, CreateNewHabitActivity::class.java)

        intent.putExtra("nameHabit", habit.name)
        intent.putExtra("descriptionHabit", habit.description)
        intent.putExtra("colorHabit", habit.color)
        intent.putExtra("priorityHabit", habit.priority)
        intent.putExtra("groupHabit", habit.group)
        intent.putExtra("periodRepeatHabit", habit.periodRepeat)
        intent.putExtra("countRepeatHabit", habit.countRepeat)

        startActivity(intent)
    }
}