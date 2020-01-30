package com.example.oblig_1

import android.app.Application
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            showGame()
        }
        buttonBrowse.setOnClickListener {
            showBrowseQuestions()
        }
    }

    fun showGame() {

        val intent = Intent(this, gameActivity::class.java)
        startActivity(intent)

    }

    fun showBrowseQuestions() {
        val intent = Intent(this, browsePeople::class.java)
        startActivity(intent)
    }


}
