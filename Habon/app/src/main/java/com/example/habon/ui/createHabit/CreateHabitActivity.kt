package com.example.habon.ui.createHabit

import android.os.Bundle
import com.example.habon.R
import androidx.appcompat.app.AppCompatActivity
import com.example.habon.databinding.ActivityCreateHabitBinding

class CreateHabitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateHabitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_habit)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_create, CreateHabitFragment())
                .commit()
        }

    }




}