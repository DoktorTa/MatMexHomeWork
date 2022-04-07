package com.example.a002.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a002.databinding.ActivityCreateNewHabitBinding
import com.example.a002.R

class CreateNewHabitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateNewHabitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_habit)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_create, CreateNewHabitFragment(intent.extras))
                .commit()
        }

    }




}