package at.fhooe.me.android.mydragon

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.view.isVisible
import at.fhooe.me.android.mydragon.databinding.ActivityDragonHatchBinding

class DragonHatch : AppCompatActivity() {

    lateinit var binding: ActivityDragonHatchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDragonHatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // start Animation from Egg
        binding.animationEgg.speed = 0.5F
        binding.animationEgg.setBackgroundColor(Color.BLACK)
        binding.animationEgg.playAnimation()

        // start Animation from Dragon after 8 sec
        Handler().postDelayed({
            animateDragon()
        }, 8000)

    }


    private fun animateDragon() {
        binding.animationEgg.isVisible = false
        binding.animationDragon.isVisible = true
        binding.animationDragon.speed = 0.7F
        binding.animationDragon.setBackgroundColor(Color.BLACK)
        binding.animationDragon.playAnimation()

        Handler().postDelayed({
            startActivity(Intent(applicationContext, SelectionDragon::class.java))
        }, 8000)
    }
}