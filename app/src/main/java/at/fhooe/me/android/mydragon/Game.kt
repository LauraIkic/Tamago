package at.fhooe.me.android.mydragon

import android.app.Activity
import android.os.Bundle
import at.fhooe.me.android.mydragon.databinding.GameBinding
import at.fhooe.me.android.mydragon.databinding.SelectDragonEggBinding

class Game: Activity() {
    lateinit var binding: GameBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = GameBinding.inflate(layoutInflater)
        setContentView(R.layout.game
        )


    }

}