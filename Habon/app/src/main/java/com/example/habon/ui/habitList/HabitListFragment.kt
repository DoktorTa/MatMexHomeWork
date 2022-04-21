package com.example.habon.ui.habitList


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habon.HabonApplication
import com.example.habon.R
import com.example.habon.databinding.FragmentHabitListBinding
import com.example.habon.db.Habit
import com.example.habon.uc.HabitUseCase
import com.example.habon.ui.createHabit.CreateHabitActivity
import com.example.habon.ui.createHabit.CreateHabitFragment
import com.example.habon.vm.HabitCreateViewModel
import com.example.habon.vm.HabitListViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HabitsListFragment(): Fragment(), EditCallBack {

    private lateinit var binding: FragmentHabitListBinding
    lateinit var name: String
    private lateinit var habitViewModel: HabitListViewModel
    private lateinit var adapter: HabitRecyclerAdapter

    companion object{
        private const val NAME_PAGE: String = "name_page"

        fun newInstance(name: String): Fragment{
            val fragment: Fragment = HabitsListFragment()

            val args = Bundle()
            args.putString(NAME_PAGE, name)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHabitListBinding.inflate(inflater)

        arguments?.let {
            name = it.getString(NAME_PAGE, "")
        }
        if (isAdded){
            generateViewModel()
        }
        adapter = HabitRecyclerAdapter(habitViewModel, this)

        val linerLayout = LinearLayoutManager(context)
        binding.habitsRecycle.adapter = adapter
        binding.habitsRecycle.layoutManager = linerLayout


        habitViewModel.allHabitOnThePage.observe(viewLifecycleOwner) {
            habit -> habit.let {adapter.submitList(it)}
        }
        habitViewModel.textToast.observe(viewLifecycleOwner){
            showToast(it)
        }
        createOnClickListener()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager
            .beginTransaction()
            .add(R.id.containerBottomSheet, SearchFragment(habitViewModel))
            .commit()
    }

    fun createOnClickListener(){
        binding.buttonNewHabit.setOnClickListener {view -> translationToHabitCreate(view)}
    }

    private fun generateViewModel(){
        val habitUseCase = HabitUseCase(
            (requireActivity().application as HabonApplication).repository)
        habitViewModel = HabitListViewModel.newHabitViewModel(habitUseCase, name)
    }

    private fun showToast(text: String){
        val duration = Toast.LENGTH_LONG
        val toast = Toast.makeText(context, text, duration)
        toast.show()
    }

    private fun translationToHabitCreate(view: View){
        val intent = Intent(context, CreateHabitActivity::class.java)
        startActivity(intent)
    }

    override fun translationOnCreateHabitWithHabit(habit: Habit) {
        val intent = Intent(context, CreateHabitActivity::class.java)
        Log.d("HabitListFragment", habit.name)
        intent.putExtra("habit", habit)
        startActivity(intent)
    }
}