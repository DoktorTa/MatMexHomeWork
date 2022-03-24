package com.example.a002

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class HelpActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
    }

    fun transitionToHabitsList(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}