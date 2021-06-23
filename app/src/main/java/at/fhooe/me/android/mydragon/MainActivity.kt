package at.fhooe.me.android.mydragon

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import at.fhooe.me.android.mydragon.databinding.ActivityMainBinding
import at.fhooe.me.android.mydragon.dragonController.DragonManager

class MainActivity : Activity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.startButton.setOnClickListener {
            if (DragonManager.getDragonFromSharedPref(baseContext) == null) {
                startActivity(Intent(applicationContext, Story::class.java))
                finish()
            } else {
                DragonManager.defaultDragon = DragonManager.getDragonFromSharedPref(this)
                startActivity(Intent(applicationContext, Game::class.java))
            }
        }
    }
}