package com.example.oblig_1

import android.app.Application
import android.widget.Toolbar

class Profile(name: String, picID: Int) {
    val name: String = name
    val picture: Int = picID
}

class MyApp: Application() {
    var profiles = mutableListOf<Profile>()
    override fun onCreate() {
        super.onCreate()

        profiles.add(Profile("Tony", R.drawable.ironman))
        profiles.add(Profile("Bruce", R.drawable.hulk))
        profiles.add(Profile("Stephen", R.drawable.drstrange))
    }
}