package com.example.a002

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.a002.databinding.ActivityCreateNewHabitBinding
import androidx.core.view.get

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