package at.fhooe.me.android.mydragon

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import at.fhooe.me.android.mydragon.databinding.GameBinding
import at.fhooe.me.android.mydragon.dragonController.DragonManager
import java.text.DecimalFormat

// in ms
const val DURATION_TO_NEXT_CLICK: Long = 30000
const val NUMBER_OF_INTERACTIONS_FINISH: Int = 29

class Game : AppCompatActivity() {
    lateinit var binding: GameBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = GameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dragonName.text = DragonManager.defaultDragon?.name
        binding.element.text = DragonManager.defaultDragon?.element.toString()
        binding.number.text = DragonManager.defaultDragon?.daysCounter.toString()


        var listener = View.OnTouchListener(function = { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_MOVE) {
                view.y = motionEvent.rawY - view.height / 2
                view.x = motionEvent.rawX - view.width / 2
            } else {
                view.y = view.height * 4.toFloat()
                view.x = view.width * 3.3.toFloat()

            }

            true
        })


        binding.sponge.setOnTouchListener(listener)


        //if dirty
        // Inital values


        binding.gameTvCounter.text = DragonManager.defaultDragon?.daysCounter.toString()


        // show correct counter status in textview
        val daysCounterObservable: MutableLiveData<Int> = MutableLiveData()
        daysCounterObservable.observe(this, {
            binding.gameTvCounter.text = DragonManager.defaultDragon?.daysCounter.toString()
        })

        // show a timer for next possible touch input
        createTimer()

        // Touch Event fired
        binding.gameTouchSimulationButton.setOnClickListener {
            // when egg hutch --> do something
            if (NUMBER_OF_INTERACTIONS_FINISH <= DragonManager.defaultDragon?.daysCounter!!) {
                Toast.makeText(baseContext, "Ei geschlüft, Hurraa", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, SelectionDragon::class.java))
                // do something fancy
            }

            // check if user can hutch the egg
            val currentTime = System.currentTimeMillis()
            val lastTouchInput = DragonManager.defaultDragon?.lastTouchInput

            if ((currentTime - lastTouchInput!!) > DURATION_TO_NEXT_CLICK) {
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
                Toast.makeText(baseContext, "Dauer noch nicht erreicht", Toast.LENGTH_SHORT).show()
            }


        }


    }

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
                val f = DecimalFormat("00")
                val hour = (millisUntilFinished / 3600000) % 24
                val minutes = (millisUntilFinished / 60000) % 60;
                val seconds = (millisUntilFinished / 1000) % 60;

                binding.textView7.text =
                    "${f.format(hour)}:${f.format(minutes)}:${f.format(seconds)}"

            }

            override fun onFinish() {
                binding.textView7.text = "Ready for hutch the egg :) !"
            }
        }.start()
    }


}