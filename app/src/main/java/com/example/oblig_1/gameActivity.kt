package com.example.oblig_1

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
    var bools = BooleanArray(2)

    val playLimit: Int = 5

    val handler = Handler()

    val runnable = Runnable {
        kotlin.run {
            startGame()
        }
    }

    var correctAnswers = 0
    var wrongAnswers = 0

    var currentProfile: Profile? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        bools[0] = true
        bools[1] = false

        allProfiles = getProfiles()
        startGame()


        leftButton.setOnClickListener {
            leftButtonClicked(it as Button)
        }

        rightButton.setOnClickListener {
            rightButtonClicked(it as Button)
        }
    }

    fun startGame() {
        resetButtons()
        //Choose random person from our Profiles class
        var leftProfiles = allProfiles.toMutableList()
        usedProfile.forEach {
            leftProfiles.remove(it)
        }
        val randProfile = leftProfiles.random()
        currentProfile = randProfile
        usedProfile.add(randProfile)
        profileView.setImageResource(randProfile.picture)



        when(bools.random()) {
            true -> {
                leftButton.text = randProfile.name
                rightButton.text = leftProfiles.random().name
            }
            false -> {
                rightButton.text = randProfile.name
                leftButton.text = leftProfiles.random().name
            }
        }
    }

    fun leftButtonClicked(button: Button) {
        val isCorrect = checkAnswer(button.text.toString())
        if (isCorrect) {

            turnOffInteractionWith()
            correctAnswers += 1
            button.setBackgroundColor(Color.GREEN)
            rightButton.setBackgroundColor(Color.RED)
            Timer().schedule(timerTask {
                handler.post(runnable)
            }, 2000)
        } else {
            turnOffInteractionWith()
            wrongAnswers += 1
            button.setBackgroundColor(Color.RED)
            rightButton.setBackgroundColor(Color.GREEN)
            Timer().schedule(timerTask {
                handler.post(runnable)
            }, 2000)
        }

    }

    fun rightButtonClicked(button: Button) {
        val isCorrect = checkAnswer(button.text.toString())
        if (isCorrect) {
            turnOffInteractionWith()
            correctAnswers += 1
            button.setBackgroundColor(Color.GREEN)
            leftButton.setBackgroundColor(Color.RED)
            Timer().schedule(timerTask {
                handler.post(runnable)
            }, 2000)

        } else {
            turnOffInteractionWith()
            wrongAnswers += 1

            button.setBackgroundColor(Color.RED)
            leftButton.setBackgroundColor(Color.GREEN)
            Timer().schedule(timerTask {
                handler.post(runnable)
            }, 2000)
        }
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

}
