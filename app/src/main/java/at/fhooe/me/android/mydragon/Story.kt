package at.fhooe.me.android.mydragon

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import at.fhooe.me.android.mydragon.databinding.ActivityMainBinding
import at.fhooe.me.android.mydragon.databinding.StoryBookBinding

class Story:AppCompatActivity() {
    lateinit var binding: StoryBookBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StoryBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.story.text = "You entered an ancient forest that used to belong to the oldest species in the wrold. \n" +
                "A species that has been forgotten for a long long time. \n" +
                "But there right underneath a tree you find a glowing egg.\n" +
                "It´s the last dragon egg and it´s your responsibility to take care of it…… \n"

        binding.continueToSelect.setOnClickListener{
            startActivity(Intent(applicationContext, SelectionDragon::class.java))
        }

    }
}