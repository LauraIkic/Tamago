package at.fhooe.me.android.mydragon

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import at.fhooe.me.android.mydragon.databinding.ActivityMainBinding
import at.fhooe.me.android.mydragon.databinding.CleanWaterEggBinding
import at.fhooe.me.android.mydragon.databinding.GameBinding
import at.fhooe.me.android.mydragon.dragonController.DragonManager

class CleanEgg:AppCompatActivity() {
    lateinit var binding: CleanWaterEggBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CleanWaterEggBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dragonName.text = DragonManager.defaultDragon?.name
        binding.element.text = DragonManager.defaultDragon?.element.toString()
        binding.gameTvCounter.text = DragonManager.defaultDragon?.daysCounter.toString()


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


    }



}