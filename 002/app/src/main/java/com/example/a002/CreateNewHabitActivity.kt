package com.example.a002

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a002.databinding.ActivityCreateNewHabitBinding

class CreateNewHabitActivity : AppCompatActivity() {

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