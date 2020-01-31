package com.example.oblig_1

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_profile.*

class AddProfile : AppCompatActivity() {
    private var m_picBitmap: Bitmap? = null
    private var m_nameText: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_profile)

        setupAddProfilePic(addProfilePic)


        finishButton.setOnClickListener {
            addProfileAndFinish()
        }
        cancelButton.setOnClickListener {
            finish()
        }
    }

    fun setupAddProfilePic(imageView: ImageView) {
        imageView.setOnClickListener {
            showCamera()
        }
    }


    val REQUEST_IMAGE_CAPTURE = 1

    fun showCamera() {

        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->

            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitMap = data?.extras?.get("data") as Bitmap
            addProfilePic.setImageBitmap(imageBitMap)
            m_picBitmap = imageBitMap
        }
    }

    fun getName(): Boolean {
        m_nameText = nameText.text.toString()
        if (m_nameText == "") {
            Toast.makeText(this, "Please input the name of the profile", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun addProfileAndFinish() {
        if (getName()) {
            val profile = Profile(m_nameText, null, m_picBitmap)
            val profiles = getProfiles()
            profiles.add(profile)
            finish()
        }

    }
    fun getProfiles(): MutableList<Profile> {
        val myApp: MyApp = application as MyApp
        val profiles = myApp.profiles
        return profiles
    }

}
