package com.example.oblig_1

import android.app.Application

class MyApp: Application() {
    var profiles = mutableListOf<Profile>()
    val names = arrayOf("Nils",
        "Steven",
        "Tom",
        "Andreas",
        "Kamil",
        "Charles",
        "Barack",
        "Nelson",
        "Matthew",
        "Vincent",
        "Radek",
        "Frankie",
        "Ramin",
        "Azillis",
        "Hannah",
        "Siri",
        "Pauline",
        "Andrew",
        "Paul",
        "John")
    override fun onCreate() {
        super.onCreate()

        profiles.add(Profile("Tony", R.drawable.ironman))
        profiles.add(Profile("Bruce", R.drawable.hulk))
        profiles.add(Profile("Stephen", R.drawable.drstrange))
        profiles.add(Profile("Peter", R.drawable.spiderman))
        profiles.add(Profile("Steven", R.drawable.captainamerica))
        profiles.add(Profile("Samuel", R.drawable.falcon))
    }
}