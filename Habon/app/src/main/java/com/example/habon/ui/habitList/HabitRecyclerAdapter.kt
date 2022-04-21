package com.example.habon.ui.habitList

import android.graphics.Color
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.habon.databinding.HabitItemBinding
import com.example.habon.db.Habit
import com.example.habon.vm.HabitListViewModel

class HabitRecyclerAdapter (
    val habitListViewModel: HabitListViewModel,
    private val editCallBack: EditCallBack
): ListAdapter<Habit, HabitRecyclerAdapter.HabitViewHolder>(HABITS_COMPARATOR), View.OnClickListener {
    private val habits: LiveData<List<Habit>> = habitListViewModel.allHabitOnThePage
//
//    fun HabitRecyclerAdapter(callBack: EditCallBack){
//        editCallBack = callBack
//    }

    class HabitViewHolder(
        val binging: HabitItemBinding): RecyclerView.ViewHolder(binging.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HabitItemBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habits.value!!.get(position)

        with(holder.binging){
            holder.itemView.tag = habit

            this.nameHabit.text = habit.name
            this.colorLine.setBackgroundColor(Color.parseColor(habit.color))
            this.descriptionHabit.text = habit.description
            this.countRepeat.text = habit.countRepeatLeft.toString()
            this.paramsHabit.text = String.format("" +
                    "${habit.group.toString()} " +
                    "${habit.priority.toString()} " +
                    "${habit.periodRepeat.toString()} day")

        }
    }

    companion object {
        private const val ID_ADD_COUNT_REPEAT = 1
        private const val ID_SUB_COUNT_REPEAT = 2
        private const val ID_EDIT_HABIT = 3
        private const val ID_DELETE_HABIT = 4

        private val HABITS_COMPARATOR = object : DiffUtil.ItemCallback<Habit>() {
            override fun areItemsTheSame(oldItem: Habit, newItem: Habit): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Habit, newItem: Habit): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

    private fun showMenuHabit(view: View){
        val popupMenu = PopupMenu(view.context, view)
        val context = view.context
        val habit = view.tag as Habit


        popupMenu.menu.add(0, ID_ADD_COUNT_REPEAT, Menu.NONE, "Add count")
        popupMenu.menu.add(0, ID_SUB_COUNT_REPEAT, Menu.NONE, "Sub count")
//            .apply {
//            isEnabled = habit.countRepeatLeft!! > 0
//        }
        popupMenu.menu.add(0, ID_EDIT_HABIT, Menu.NONE, "Edit")
        popupMenu.menu.add(0, ID_DELETE_HABIT, Menu.NONE, "Delete")

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                ID_ADD_COUNT_REPEAT -> {
                    habitListViewModel.changePeriodRepeatHabit(habit, 1)
                    notifyDataSetChanged()
                }
                ID_SUB_COUNT_REPEAT -> {
                    habitListViewModel.changePeriodRepeatHabit(habit, -1)
                    notifyDataSetChanged()
                }
                ID_EDIT_HABIT -> {
                    editCallBack.translationOnCreateHabitWithHabit(habit)
//                    HabitService.deleteHabit(habit)
//                    habitsListFragment.translationToEditHabit(habit)
                }
                ID_DELETE_HABIT -> {
                    habitListViewModel.deleteHabit(habit)
                    notifyDataSetChanged()
                }
            }
            return@setOnMenuItemClickListener true
        }

        popupMenu.show()
    }

    override fun onClick(p0: View?) {
        val user = p0?.tag as Habit
        showMenuHabit(p0)
    }

}