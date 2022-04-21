package com.example.habon.ui.habitList

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.habon.databinding.FragmentSearchSortBinding
import com.example.habon.vm.HabitListViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SearchFragment(val habitListViewModel: HabitListViewModel): BottomSheetDialogFragment(){

    private lateinit var binding: FragmentSearchSortBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchSortBinding.inflate(inflater)
        return binding.root
    }

    companion object{
        const val TAG = "containerBottomSheet"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val search: EditText = binding.searchHabit
        search.addTextChangedListener(
            object : TextWatcher {
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                        // текст только что изменили
                    Log.d("SearchFragment OnTextChanged", s.toString())
                    habitListViewModel.searchHabitForNameAndDescription(s.toString())
                }

                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    Log.d("SearchFragment BeforeTextChanged", s.toString())
                    // текст будет изменен
                }

                override fun afterTextChanged(s: Editable) {
                    Log.d("SearchFragment AfterTextChanged", s.toString())
//                    habitListViewModel.searchHabitForNameAndDescription(s.toString())
                }
            }
        )

    }
}