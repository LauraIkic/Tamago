package at.fhooe.me.android.mydragon

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import at.fhooe.me.android.mydragon.databinding.ActivityMainBinding

class MainActivity : Activity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener{

            startActivity(Intent(applicationContext,SelectionDragon::class.java))
            finish()
        }

    }
}