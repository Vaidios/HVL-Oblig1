package com.example.oblig_1

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask

class gameActivity : AppCompatActivity() {

    var allProfiles = mutableListOf<Profile>()
    var usedProfile: MutableList<Profile> = mutableListOf<Profile>()
    var bools = arrayOf(true, false)

    val playLimit: Int = 3
    val delay: Long = 1200

    //Handler to update the UI on global thread
    private val handler = Handler()



    val runnable = Runnable {
        kotlin.run {
            startGame()
        }
    }

    private var correctAnswers: Int = 0
    private var wrongAnswers: Int = 0

    private var currentProfile: Profile? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        //Get all profiles from global settings
        allProfiles = getProfiles()

        //Call a function to start a game
        startGame()


        leftButton.setOnClickListener {
            userClicked(it as Button, rightButton)
        }

        rightButton.setOnClickListener {
            userClicked(it as Button, leftButton)
        }
    }

    fun startGame() {
        resetButtons()
        val leftProfiles = allProfiles.toMutableList()

        //Remove profiles that were used for the game
        usedProfile.forEach {
            leftProfiles.remove(it)
        }
        if (leftProfiles.count() == 0 || (correctAnswers + wrongAnswers) == playLimit) {
            moveToEndScreen()
            finish()
            return
        }
        //Choose random person from our Profiles class
        val randProfile = leftProfiles.random()
        currentProfile = randProfile
        usedProfile.add(randProfile)
        if (randProfile.picture != null) {
            profileView.setImageResource(randProfile.picture)
        } else if ( randProfile.pictureBitmap != null ) {
            profileView.setImageBitmap(randProfile.pictureBitmap)
        }



        when(bools.random()) {
            true -> {
                leftButton.text = randProfile.name
                rightButton.text = getNames().random()
            }
            false -> {
                leftButton.text = getNames().random()
                rightButton.text = randProfile.name
            }
        }
    }



    fun userClicked(button: Button, secondButton: Button) {
        val isCorrect = checkAnswer(button.text.toString())
        if (isCorrect) {
            turnOffInteractionWith()
            correctAnswers += 1
            button.setBackgroundColor(Color.GREEN)
            secondButton.setBackgroundColor(Color.RED)
            Timer().schedule(timerTask {
                handler.post(runnable)
            }, delay)

        } else {
            turnOffInteractionWith()
            wrongAnswers += 1

            button.setBackgroundColor(Color.RED)
            secondButton.setBackgroundColor(Color.GREEN)
            Timer().schedule(timerTask {
                handler.post(runnable)
            }, delay)
        }
    }

    fun moveToEndScreen() {
        val intent = Intent(this, EndActivity::class.java)
        intent.putExtra("correctAnswers", correctAnswers)
        intent.putExtra("wrongAnswers", wrongAnswers)
        startActivity(intent)
    }
    fun resetButtons() {
        turnOnInteractionWith()
        leftButton.setBackgroundColor(Color.GRAY)
        rightButton.setBackgroundColor(Color.GRAY)
    }

    fun turnOnInteractionWith() {
        leftButton.isActivated = true
        rightButton.isActivated = true
    }

    fun turnOffInteractionWith() {
        leftButton.isActivated = false
        rightButton.isActivated = false
    }



    fun checkAnswer(answer: String): Boolean {
        return answer == currentProfile?.name
    }

    fun getProfiles(): MutableList<Profile> {
        val myApp: MyApp = application as MyApp
        val profiles = myApp.profiles
        return profiles
    }
    fun getNames(): Array<String> {
        val myApp: MyApp = application as MyApp
        val names = myApp.names
        return names
    }

}
