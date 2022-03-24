package com.example.a001

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Pair
import android.view.View
import android.widget.TextView
import android.widget.Toast

class SquareScore : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("", "onCreate() - SquareScore")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_square_score)
        showSquareScore()
    }

    private fun showSquareScore(){
        val squareScoreText: TextView = findViewById(R.id.squareScore)
        val score = getSquareScore()
        squareScoreText.text = score.toString()
    }

    private fun getSquareScore(): Short{
        val maxUShort: Short = 32766
        val score: Short = getScore()
        return ((score * score) % maxUShort).toShort()
    }

    private fun getScore(): Short{
        val bundle :Bundle ?=intent.extras
        val score = bundle!!.getString("score")
        return score!!.toShort()
    }

    fun transitionToCalculationScore(view: View){
        val intent = Intent(this, CountingScore::class.java)
        val pair: Pair<View, String> = Pair(view.findViewById(R.id.butBackToCounter), "backToCounter")
        val options: ActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this, pair)

        startActivity(intent, options.toBundle())
//        overridePendingTransition(R.anim.alpha, R.anim.diagonaltranslate)

    }

    fun showSquarePointerDescription(view: View){
        val text = R.string.square_score_info
        val duration = Toast.LENGTH_LONG

        Toast.makeText(applicationContext, text, duration).show()
    }

    override fun onPause() {
        Log.d("", "onPause() - SquareScore")
        super.onPause()
    }

    override fun onStart() {
        Log.d("", "onStart() - SquareScore")
        super.onStart()
    }

    override fun onResume() {
        Log.d("", "onResume() - SquareScore")
        super.onResume()
    }

    override fun onStop() {
        Log.d("", "onStop() - SquareScore")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("", "onDestroy() - SquareScore")
        super.onDestroy()
    }

    override fun onRestart() {
        Log.d("", "onRestart() - SquareScore")
        super.onRestart()
    }
}