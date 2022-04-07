package com.example.a002.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a002.HabitsApp
import com.example.a002.databinding.FragmentHabitsListBinding
import com.example.a002.db.Habit
import com.example.a002.viewModel.HabitViewModel
import com.example.a002.viewModel.HabitViewModelFactory

class HabitsListFragment: Fragment() {

    private var adapter: HabitAdapter = HabitAdapter()
    private lateinit var binding: FragmentHabitsListBinding
    lateinit var name: String
    lateinit var filter_group_name: String

    private val wordViewModel: HabitViewModel by viewModels {
        HabitViewModelFactory((application as HabitsApp).repository)
    }

    companion object{
        private const val NAME_ARGS: String = "args_name"
        private const val FILTER_GROUP_NAME: String = "filter_group_name"

        fun newInstance(name: String, filter_group_name: String): Fragment{
            val fragment: Fragment = HabitsListFragment()

            val args = Bundle()
            args.putString(NAME_ARGS, name)
            args.putString(FILTER_GROUP_NAME, filter_group_name)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHabitsListBinding.inflate(inflater)

        val linerLayout = LinearLayoutManager(context)

        binding.habitsRecycle.layoutManager = linerLayout
        binding.habitsRecycle.adapter = adapter
        createOnClickListener()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            name = it.getString(NAME_ARGS, "")
            filter_group_name = it.getString(FILTER_GROUP_NAME, "")
            adapter.filterByGroup = filter_group_name
        }
    }

    private fun createOnClickListener() {
        binding.buttonNewHabit.setOnClickListener {view -> translationToCreateHabit(view)}
    }

    private fun translationToCreateHabit(view: View){
        val intent = Intent(context, CreateNewHabitActivity::class.java)
        startActivity(intent)
    }

//    fun translationToEditHabit(habit: Habit){
//        val intent = Intent(context, CreateNewHabitActivity::class.java)
//        intent.putExtra("habit", habit)
//        startActivity(intent)
//    }


}