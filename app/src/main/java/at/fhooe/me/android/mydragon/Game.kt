package at.fhooe.me.android.mydragon

import android.app.Activity
import android.os.Bundle
import at.fhooe.me.android.mydragon.databinding.GameBinding
import at.fhooe.me.android.mydragon.databinding.SelectDragonEggBinding
import at.fhooe.me.android.mydragon.dragonController.DragonManager

class Game: Activity() {
    lateinit var binding: GameBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = GameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView2.text = DragonManager.defaultDragon?.name
        binding.textView3.text = DragonManager.defaultDragon?.element.toString()
        binding.textView4.text = DragonManager.defaultDragon?.daysCounter.toString()


    }

}