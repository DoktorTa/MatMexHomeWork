package com.example.habon.ui.createHabit

import android.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habon.HabonApplication
import com.example.habon.databinding.FragmentCreateHabitBinding
import com.example.habon.db.Habit
import com.example.habon.uc.HabitUseCase
import com.example.habon.ui.habitList.HabitListActivity
import com.example.habon.util.getListGroupName
import com.example.habon.util.getListPriorityName
import com.example.habon.vm.HabitCreateViewModel
import com.example.habon.vm.HabitListViewModel

class CreateHabitFragment(private val intentBundle: Bundle?): Fragment() {
    private val priorityList: List<String> = getListPriorityName()
    private val groupHabitList: List<String> = getListGroupName()
    private lateinit var habitCreateViewModel: HabitCreateViewModel
    private lateinit var binding: FragmentCreateHabitBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateHabitBinding.inflate(inflater)
        if (isAdded){
            generateViewModel()
        }

        return binding.root
    }

    fun generateViewModel(){
        val habitUseCase = HabitUseCase(
            (requireActivity().application as HabonApplication).repository)
        habitCreateViewModel = HabitCreateViewModel.newHabitCreateViewModel(habitUseCase)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createOnClickListener()
        context?.let { loadPriorityData(it) }
        loadGroupData()

        if(intentBundle != null){
            getDataHabitEdit(intentBundle)
        }
    }

    private fun createOnClickListener(){
        val buttonCreate: Button = binding.toListHabit
        buttonCreate.setOnClickListener {view -> createHabit(view)}

        val buttonCansel: Button = binding.canselHabit
        buttonCansel.setOnClickListener {view -> translationToHabitList(view)}

//        val buttonColorPicker: Button = binding.colorPiker
//        buttonColorPicker.setOnClickListener {view -> createColorPickerDialog(view)}
    }

    private fun getDataHabitEdit(bundle: Bundle){
        val habit: Habit = bundle.get("habit") as Habit

        binding.nameHabit.setText(habit.name)
        binding.descriptionHabit.setText(habit.description)
        binding.countHabit.setText(habit.countRepeat!!.toString())
        binding.periodHabit.setText(habit.periodRepeat!!.toString())

        val radioId: Int = groupHabitList.indexOf(habit.group)
        Log.d("CreateHabitFragment", "${radioId}")
        val radioButton: RadioButton = binding.groupHabit.get(radioId) as RadioButton

        radioButton.setChecked(true)

        val idSelector: Int = priorityList.indexOf(habit.priority)
        binding.spinnerPriority.setSelection(idSelector)

    }

    private fun loadPriorityData(context: Context){
        val priorityHabit: Spinner = binding.spinnerPriority

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            context,
            R.layout.simple_spinner_dropdown_item,
            priorityList)
        priorityHabit.setAdapter(adapter)
    }

    private fun loadGroupData(){
        val groupHabit: RadioGroup = binding.groupHabit

        for (nameGroup in groupHabitList) {
            val radioButton: RadioButton = RadioButton(context)
            radioButton.setText(nameGroup)
            groupHabit.addView(radioButton);
        }
    }

    private fun createHabit(view: View){

        val radioId: Int = binding.groupHabit.getCheckedRadioButtonId()
        val radioButton: RadioButton = binding.groupHabit.findViewById(radioId)

        habitCreateViewModel.insertHabit(
            name = binding.nameHabit.text.toString(),
            description = binding.descriptionHabit.text.toString(),
            color = "#00FF00",
            priority = binding.spinnerPriority.selectedItem.toString(),
            group = radioButton.text.toString(),
            periodRepeat = binding.periodHabit.text.toString().toInt(),
            countRepeat = binding.countHabit.text.toString().toInt()
        )

        translationToHabitList(view)
    }

    private fun translationToHabitList(view: View){
        val intent = Intent(context, HabitListActivity::class.java)
        startActivity(intent)
    }
}