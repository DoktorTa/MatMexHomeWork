package com.example.a002

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a002.databinding.FragmentCreateNewHabitBinding

class CreateNewHabitFragment(val intentBundle: Bundle? ): Fragment() {
    private val habitService: HabitService = HabitService
    private val priorityList: List<String> = habitService.getListPriorityName()
    private val groupHabitList: List<String> = habitService.getListGroupsName()
    private lateinit var binding: FragmentCreateNewHabitBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateNewHabitBinding.inflate(inflater)


        return binding.root
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
        val habit = bundle.get("habit")
        Log.d("", "${habit}")

        binding.nameHabit.setText(bundle.get("nameHabit").toString())
        binding.descriptionHabit.setText(bundle.get("descriptionHabit").toString())
        binding.countHabit.setText(bundle.get("countRepeatHabit").toString())
        binding.periodHabit.setText(bundle.get("periodRepeatHabit").toString())

        val radioId: Int = groupHabitList.indexOf(bundle.get("groupHabit").toString())
        val radioButton: RadioButton = binding.groupHabit.findViewById(radioId)

        radioButton.setChecked(true)

        val idSelector: Int = priorityList.indexOf(bundle.get("priorityHabit").toString())
        binding.spinnerPriority.setSelection(idSelector)

    }

    private fun loadPriorityData(context: Context){
        val priorityHabit: Spinner = binding.spinnerPriority

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            context,
            android.R.layout.simple_spinner_dropdown_item,
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

        habitService.createHabit(
            name = binding.nameHabit.text.toString(),
            description = binding.descriptionHabit.text.toString(),
            color = "#00FF00",
            priority = binding.spinnerPriority.selectedItem.toString(),
            group =  radioButton.text.toString(),
            periodRepeat = binding.periodHabit.text.toString().toInt(),
            countRepeat = binding.countHabit.text.toString().toInt())

        translationToHabitList(view)
    }

    private fun translationToHabitList(view: View){
        val intent = Intent(context, HabitsListActivity::class.java)
        startActivity(intent)
    }
}