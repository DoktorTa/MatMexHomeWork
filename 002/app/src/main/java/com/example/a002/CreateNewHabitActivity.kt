package com.example.a002

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get

class CreateNewHabitActivity : AppCompatActivity() {
    private val habitService: HabitService = HabitService
    private val priorityList: List<String> = habitService.getListPriorityName()
    private val groupHabitList: List<String> = habitService.getListGroupsName()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_habit)

        createOnClickListener()

        loadPriorityData()
        loadGroupData()

        val bundle: Bundle? = intent.extras
        if(bundle != null){
            getDataHabitEdit(bundle)
        }
    }

    private fun createOnClickListener(){
        val buttonCreate: Button = findViewById(R.id.toListHabit)
        buttonCreate.setOnClickListener {view -> createHabit(view)}

        val buttonCansel: Button = findViewById(R.id.canselHabit)
        buttonCansel.setOnClickListener {view -> translationToHabitList(view)}

        val buttonColorPicker: Button = findViewById(R.id.colorPiker)
        buttonColorPicker.setOnClickListener {view -> createColorPickerDialog(view)}
    }

    private fun createColorPickerDialog(view: View){
        val myDialogFragment = ColorPickerDialog()
        val manager = supportFragmentManager
        myDialogFragment.show(manager, "colorPicker")
        Log.d("", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
    }

    private fun getDataHabitEdit(bundle: Bundle){

        val nameHabit: EditText = findViewById(R.id.nameHabit)
        val descriptionHabit: EditText = findViewById(R.id.descriptionHabit)
        val countRepeatHabit: EditText = findViewById(R.id.countHabit)
        val periodHabit: EditText = findViewById(R.id.periodHabit)
        val priorityHabit: Spinner = findViewById(R.id.spinnerPriority)
        val groupHabit: RadioGroup = findViewById(R.id.groupHabit)

        nameHabit.setText(bundle.get("nameHabit").toString())
        descriptionHabit.setText(bundle.get("descriptionHabit").toString())
        countRepeatHabit.setText(bundle.get("countRepeatHabit").toString())
        periodHabit.setText(bundle.get("periodRepeatHabit").toString())

        val idGroup: Int = groupHabitList.indexOf(bundle.get("groupHabit").toString())
        val but: RadioButton = groupHabit[idGroup] as RadioButton
        but.setChecked(true)

        val idSelector: Int = priorityList.indexOf(bundle.get("priorityHabit").toString())
        priorityHabit.setSelection(idSelector)

    }

    private fun translationToHabitList(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun loadPriorityData(){
        val priorityHabit: Spinner = findViewById(R.id.spinnerPriority)

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                priorityList)
        priorityHabit.setAdapter(adapter)
    }

    private fun loadGroupData(){
        val groupHabit: RadioGroup = findViewById(R.id.groupHabit)

        for (nameGroup in groupHabitList) {
            val radioButton: RadioButton = RadioButton(this)
            radioButton.setText(nameGroup)
            groupHabit.addView(radioButton);
        }
    }

    private fun createHabit(view: View){
        val nameHabit: EditText = findViewById(R.id.nameHabit)
        val descriptionHabit: EditText = findViewById(R.id.descriptionHabit)
        val countRepeatHabit: EditText = findViewById(R.id.countHabit)
        val periodHabit: EditText = findViewById(R.id.periodHabit)
        val priorityHabit: Spinner = findViewById(R.id.spinnerPriority)
        val groupHabit: RadioGroup = findViewById(R.id.groupHabit)

        val radioId: Int = groupHabit.getCheckedRadioButtonId()
        val radioButton: RadioButton = findViewById(radioId)

        habitService.createHabit(
            name = nameHabit.text.toString(),
            description = descriptionHabit.text.toString(),
            color = "#00FF00",
            priority = priorityHabit.selectedItem.toString(),
            group =  radioButton.text.toString(),
            periodRepeat = periodHabit.text.toString().toInt(),
            countRepeat = countRepeatHabit.text.toString().toInt())

        translationToHabitList(view)
    }



}