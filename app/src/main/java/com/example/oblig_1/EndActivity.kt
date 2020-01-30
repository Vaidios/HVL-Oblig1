package com.example.oblig_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_end.*

class EndActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end)

        val correctAnswers = intent.getIntExtra("correctAnswers", 0)
        val wrongAnswers = intent.getIntExtra("wrongAnswers", 0)

        correctTextView.text = "Correct answers: $correctAnswers"
        wrongTextView.text = "Wrong answers: $wrongAnswers"

    }

    override fun onBackPressed() {
        super.onBackPressed()

        val intent = Intent(this, MainActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
