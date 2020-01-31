package com.example.oblig_1

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomAdapter(private val context: Activity, private val profiles: List<Profile>):
    BaseAdapter(){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_row, null, true)

        val nameText = rowView.findViewById<TextView>(R.id.name)
        val imageView = rowView.findViewById<ImageView>(R.id.profilePic)

        nameText.text = profiles[position].name
        if (profiles[position].picture != null) {
            imageView.setImageResource(profiles[position].picture as Int)
        } else if (profiles[position].pictureBitmap != null) {
            imageView.setImageBitmap(profiles[position].pictureBitmap as Bitmap)
        }


        return rowView
    }

    override fun getItem(p0: Int): Any {
        return profiles[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        print(profiles.count())
        return profiles.count()
    }
}