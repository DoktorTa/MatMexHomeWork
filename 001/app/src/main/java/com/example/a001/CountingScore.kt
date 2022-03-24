package com.example.a001

import android.app.ActivityOptions
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import android.util.Pair
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

object ScoreData: Serializable {
    var score: Short = 0
}

class CountingScore : AppCompatActivity() {
    private var scoreData: ScoreData = ScoreData


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("", "onCreate() - CountingScore")
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_win1)
        refreshScoreOnScreen()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        scoreData.score++
        refreshScoreOnScreen()
    }

    fun transitionToSquareScore(view: View){
        val intent = Intent(this, SquareScore::class.java)
        val pair: Pair<View, String> = Pair(view.findViewById(R.id.buttonNext), "toSquareScore")
        val options: ActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this, pair)
        intent.putExtra("score", scoreData.score.toString())

        startActivity(intent, options.toBundle())
//        finish()
//        overridePendingTransition(R.anim.diagonaltranslate, R.anim.alpha)
    }

    fun showPointerDescription(view: View){
        val text = R.string.counting_score_info
        val duration = Toast.LENGTH_LONG

        Toast.makeText(applicationContext, text, duration).show()
    }

    private fun refreshScoreOnScreen(){
        val text: TextView = findViewById(R.id.score)
        text.text = scoreData.score.toString()
    }

    override fun onPause() {
        Log.d("", "onPause() - CountingScore")
        super.onPause()
    }

    override fun onStart() {
        Log.d("", "onStart() - CountingScore")
        super.onStart()
    }

    override fun onResume() {
        Log.d("", "onResume() - CountingScore")
        super.onResume()
    }

    override fun onStop() {
        Log.d("", "onStop() - CountingScore")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("", "onDestroy() - CountingScore")
        super.onDestroy()
    }

    override fun onRestart() {
        Log.d("", "onRestart() - CountingScore")
        super.onRestart()
    }

}
