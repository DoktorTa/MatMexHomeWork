package com.example.a002

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

class ColorPickerDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_color_picker, null)
        val v: View = view.findViewById(R.id.gradient)
        v.setBackground(generateGradient())
//        generateRectangle(view)
        return view
    }
//
//    private fun generateRectangle(view: View){
//        val v: LinearLayout = view.findViewById(R.id.linerLayoutGradient)
//        for (i in 0..countElement){
//            val but: Button = Button(context)
//            but.layoutParams = ViewGroup.LayoutParams(50, 50)
//            v.addView(but)
//        }
//    }

    fun generateGradient(): GradientDrawable {
        val colors = (0..360 step 60)
            .map { it.toFloat() }
            .map { floatArrayOf(it, 0.9f, 1f) }
            .map { Color.HSVToColor(it)}
            .toIntArray()

        return GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors)
    }

    private val countElement: Int = 16
    private val colors: List<Int> = (0 until countElement).map{
        Color.HSVToColor(floatArrayOf(360f / 16f * (it + 1) - 360f / 16f / 2f, 0.9f, 1f))
    }
}