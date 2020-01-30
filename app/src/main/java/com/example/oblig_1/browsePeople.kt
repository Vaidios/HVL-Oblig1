package com.example.oblig_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_browse_people.*

class browsePeople : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse_people)
        setupProfiles()
        val customAdapter = CustomAdapter(this, setupProfiles())

        profilesList.adapter = customAdapter
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
    fun setupProfiles(): MutableList<Profile> {
        val myApp: MyApp = application as MyApp
        val profiles = myApp.profiles
        return profiles
    }
}

