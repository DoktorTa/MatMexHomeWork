package com.example.habon.ui.createHabit

import android.os.Bundle
import android.util.Log
import com.example.habon.R
import androidx.appcompat.app.AppCompatActivity
import com.example.habon.databinding.ActivityCreateHabitBinding

class CreateHabitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateHabitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_habit)

//        Log.d("CreateHabitActivity", intent.extras.get("habit"))

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_create, CreateHabitFragment(intent.extras))
                .commit()
        }



    }




}