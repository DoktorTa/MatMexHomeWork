package com.example.a002

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a002.databinding.FragmentHabitsListBinding
import kotlinx.serialization.json.Json

class HabitsListFragment: Fragment() {

    private var adapter: HabitAdapter = HabitAdapter(this)
    private lateinit var binding: FragmentHabitsListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_habits_list, null)

        val linerLayout = LinearLayoutManager(context)

        binding.habitsRecycle.layoutManager = linerLayout
        binding.habitsRecycle.adapter = adapter

        return view
    }

    fun translationToCreateHabit(view: View){
        val intent = Intent(context, CreateNewHabitActivity::class.java)
        startActivity(intent)
    }

    fun translationToEditHabit(habit: Habit){
        val jsonHabit = Json.encodeToString(Habit.serializer(), habit)
        val intent = Intent(context, CreateNewHabitActivity::class.java)
        intent.putExtra("jasonHabit", jsonHabit)
        startActivity(intent)
    }


}