package at.fhooe.me.android.mydragon

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.os.CountDownTimer
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import at.fhooe.me.android.mydragon.databinding.GameBinding
import at.fhooe.me.android.mydragon.dragonController.DragonManager
import at.fhooe.me.android.mydragon.dragonController.ElementSelect
import java.text.DecimalFormat

// in ms
const val DURATION_TO_NEXT_CLICK: Long = 10000
const val NUMBER_OF_INTERACTIONS_FINISH: Int = 2

class Game : AppCompatActivity() {
    lateinit var binding: GameBinding

    // Counter for sponge moves
    val spongeMotionCounter: MutableLiveData<Int> = MutableLiveData()

    // Background Image
    lateinit var backgroundImage: ImageView

    // necessary in Time, because of loading a new background image before animation (looks not so good)
    var eggIsHatched = false


    @SuppressLint("ResourceType", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = GameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.gameDragonNameTv.text = DragonManager.defaultDragon?.name
        binding.gameElementTv.text = DragonManager.defaultDragon?.element.toString() + "-Dragon: "
        binding.gameCounterTv.text =
            (NUMBER_OF_INTERACTIONS_FINISH + 1 - DragonManager.defaultDragon?.daysCounter!!).toString()

        backgroundImage = findViewById(R.id.game_egg)

        // set Background Image
        setCorrectEggImage()

        // show correct counter status in textview
        val daysCounterObservable: MutableLiveData<Int> = MutableLiveData()
        daysCounterObservable.observe(this, {
            binding.gameCounterTv.text =
                (NUMBER_OF_INTERACTIONS_FINISH + 1 - DragonManager.defaultDragon?.daysCounter!!).toString()

        })

        // Counter for sponge moves
        spongeMotionCounter.observe(this, {
            // Touch Event fired
            if (spongeMotionCounter.value == 250) {
                // when egg hatch --> do something
                if (NUMBER_OF_INTERACTIONS_FINISH <= DragonManager.defaultDragon?.daysCounter!!) {
                    eggIsHatched = true
                    startActivity(Intent(applicationContext, DragonHatch::class.java))
                }

                // check if egg needs attention from user
                if (eggReadyForInput()) {
                    // increase daysCounter
                    var counter = DragonManager.defaultDragon?.daysCounter!!
                    counter++
                    DragonManager.defaultDragon?.daysCounter = counter!!
                    daysCounterObservable.value = counter

                    // save actual Time in Dragon
                    DragonManager.defaultDragon?.lastTouchInput = System.currentTimeMillis()

                    // set up a new Time
                    createTimer()

                    // save Dragon permanent
                    DragonManager.saveDragonInSharedPref(
                        applicationContext,
                        DragonManager.defaultDragon!!
                    )
                } else {
                    Toast.makeText(baseContext, "You need to wait ...", Toast.LENGTH_SHORT).show()
                }

                // set Egg to clean one
                setCorrectEggImage()

            }
        }) // spongeMotionCounter Observer


        // Exit Button
        binding.gameExitDoor.setOnClickListener {
            finishAffinity()
        }

        spongeMotionCounter.value = 0

        val listener = View.OnTouchListener(function = { view, motionEvent ->
            spongeMotionCounter.value = spongeMotionCounter.value?.plus(1)
            if (motionEvent.action == MotionEvent.ACTION_MOVE) {
                view.y = motionEvent.rawY - view.height / 2
                view.x = motionEvent.rawX - view.width / 2

            } else {
                view.y = view.height * 4.toFloat()
                view.x = view.width * 3.3.toFloat()
            }
            true
        })

        // Sponge Listener
        binding.gameSponge.setOnTouchListener(listener)

        // show a timer for next possible touch input
        createTimer()

    } // end onCreate


    private fun createTimer() {
        // calculate amount of time for next input is possible
        var duration: Long = 0
        val currentTime = System.currentTimeMillis()
        val lastTouchInput = DragonManager.defaultDragon?.lastTouchInput
        val timeLeft = currentTime - lastTouchInput!!

        if (timeLeft < DURATION_TO_NEXT_CLICK) {
            duration = DURATION_TO_NEXT_CLICK - timeLeft
        }

        // create CountDownTimer Object and show it in GUI
        object : CountDownTimer(duration, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                // necessary because of loading a new background image before animation (looks not so good)
                if (!eggIsHatched) {
                    val f = DecimalFormat("00")
                    val hour = (millisUntilFinished / 3600000) % 24
                    val minutes = (millisUntilFinished / 60000) % 60;
                    val seconds = (millisUntilFinished / 1000) % 60;

                    binding.gameTimerTv.text =
                        "${f.format(hour)}:${f.format(minutes)}:${f.format(seconds)}"

                    // set new background, if 2/3 of time is reached
                    setCorrectEggImage()
                }

            }

            override fun onFinish() {
                // reset counter and change picture
                spongeMotionCounter.value = 0
                setCorrectEggImage()
                binding.gameTimerTv.text = "Egg needs your attention!"
            }
        }.start()
    }


    private fun eggReadyForInput(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastTouchInput = DragonManager.defaultDragon?.lastTouchInput

        return (currentTime - lastTouchInput!!) > DURATION_TO_NEXT_CLICK
    }

    private fun setCorrectEggImage() {
        val dragonElement = DragonManager.defaultDragon?.element
        if (eggReadyForInput()) {
            // Dirty Eggs
            when (dragonElement) {
                ElementSelect.Water -> {
//                    backgroundImage.setBackgroundResource(R.drawable.water_egg_dirty)
                }
                ElementSelect.Fire -> {
//                    backgroundImage.setBackgroundResource(R.drawable.fire_pic)
                }
                else -> startActivity(Intent(applicationContext, MainActivity::class.java))
            }
        } else {
            if ((System.currentTimeMillis() - DragonManager.defaultDragon?.lastTouchInput!!) > (DURATION_TO_NEXT_CLICK / 1.5)) {
                // Dirty Eggs
                when (dragonElement) {
                    ElementSelect.Water -> {
//                        backgroundImage.setBackgroundResource(R.drawable.water_egg_dirty)
                    }
                    ElementSelect.Fire -> {
//                        backgroundImage.setBackgroundResource(R.drawable.fire_pic)
                    }
                    else -> startActivity(
                        Intent(
                            applicationContext,
                            MainActivity::class.java
                        )
                    )
                }
            } else {
                // Clean Eggs
                when (dragonElement) {
                    ElementSelect.Water -> {
                        backgroundImage.setBackgroundResource(R.drawable.select_blue)
                    }
                    ElementSelect.Fire -> {
                        backgroundImage.setBackgroundResource(R.drawable.select_red)
                    }
                    else -> startActivity(
                        Intent(
                            applicationContext,
                            MainActivity::class.java
                        )
                    )
                }
            }
        }
    } // end setCorrectBackgroundImage


}