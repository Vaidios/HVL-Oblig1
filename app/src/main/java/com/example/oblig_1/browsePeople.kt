package com.example.oblig_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_browse_people.*

class browsePeople : AppCompatActivity() {

    var adapter: CustomAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse_people)
        getProfiles()


        val customAdapter = CustomAdapter(this, getProfiles())
        adapter = customAdapter
        profilesList.adapter = customAdapter
        supportActionBar?.setDisplayShowTitleEnabled(false)

        fab.setOnClickListener {
            showAddProfile()
        }
    }
    fun getProfiles(): MutableList<Profile> {
        val myApp: MyApp = application as MyApp
        val profiles = myApp.profiles
        return profiles
    }

    fun showAddProfile() {
        val intent = Intent(this, AddProfile::class.java)
        startActivity(intent)
    }


    override fun onStart() {
        super.onStart()
        adapter?.notifyDataSetChanged()
    }


}

