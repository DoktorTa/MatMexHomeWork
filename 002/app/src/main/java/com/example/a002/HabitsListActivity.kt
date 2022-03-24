package com.example.a002

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class HabitsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (savedInstanceState == null) {
            val fragment = HabitsListFragment()

            val habitListFragment =
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_habits_list, fragment)
                    .commit()

        }

        setContentView(R.layout.activity_habits_list)
    }

}
