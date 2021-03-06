package com.example.a002

import android.graphics.Color
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.a002.databinding.ItemHabitBinding


class HabitAdapter(
    val mainActivity: MainActivity
): RecyclerView.Adapter<HabitAdapter.HabitViewHolder>(), View.OnClickListener {
    private val habitService: HabitService = HabitService

    class HabitViewHolder(
        val binging: ItemHabitBinding): RecyclerView.ViewHolder(binging.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHabitBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)

        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habitService.getHabit(holder.getAdapterPosition())

        with(holder.binging){
            holder.itemView.tag = habit

            this.nameHabit.text = habit.name
            this.colorLine.setBackgroundColor(Color.parseColor(habit.color))
            this.descriptionHabit.text = habit.description
            this.paramsHabit.text = String.format("" +
                    "[${habit.group.toString()}]-" +
                    "[${habit.priority.toString()}]-" +
                    "[${habit.countRepeat.toString()} repeat]-" +
                    "[${habit.periodRepeat.toString()} day]")

        }
    }

    private fun showMenuHabit(view: View){
        val popupMenu = PopupMenu(view.context, view)
        val context = view.context
        val habit = view.tag as Habit


        popupMenu.menu.add(0, ID_ADD_COUNT_REPEAT, Menu.NONE, "Add count")
        popupMenu.menu.add(0, ID_SUB_COUNT_REPEAT, Menu.NONE, "Sub count").apply {
            isEnabled = habit.countRepeat > 0
        }
        popupMenu.menu.add(0, ID_EDIT_HABIT, Menu.NONE, "Edit")
        popupMenu.menu.add(0, ID_DELETE_HABIT, Menu.NONE, "Delete")

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                ID_ADD_COUNT_REPEAT -> {
                    habitService.onHabitCountChange(habit, 1)
                    this.notifyDataSetChanged()
                }
                ID_SUB_COUNT_REPEAT -> {
                    habitService.onHabitCountChange(habit, -1)
                    this.notifyDataSetChanged()
                }
                ID_EDIT_HABIT -> {
                    habitService.deleteHabit(habit)
                    mainActivity.translationToEditHabit(habit)
                }
                ID_DELETE_HABIT -> {
                    habitService.deleteHabit(habit)
                    notifyDataSetChanged()
                }
            }
            return@setOnMenuItemClickListener true
        }

        popupMenu.show()
    }


    override fun getItemCount(): Int {
        return habitService.getSize()
    }

    override fun onClick(p0: View?) {
        val user = p0?.tag as Habit
        showMenuHabit(p0)
    }

    companion object {
        private const val ID_ADD_COUNT_REPEAT = 1
        private const val ID_SUB_COUNT_REPEAT = 2
        private const val ID_EDIT_HABIT = 3
        private const val ID_DELETE_HABIT = 4
    }
}