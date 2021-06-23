package at.fhooe.me.android.mydragon

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import at.fhooe.me.android.mydragon.databinding.SelectDragonEggBinding
import at.fhooe.me.android.mydragon.R.layout.select_dragon_name
import at.fhooe.me.android.mydragon.dragonController.Dragon
import at.fhooe.me.android.mydragon.dragonController.DragonManager
import at.fhooe.me.android.mydragon.dragonController.ElementSelect

const val TAG = "Dragon"

class SelectionDragon : Activity() {

    lateinit var binding: SelectDragonEggBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = SelectDragonEggBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Water Dragon
        binding.waterDragon.setOnClickListener {
            createDragonAndPlay(ElementSelect.Water)
        }


        // Fire Dragon --> Animation
        binding.Fire.setOnClickListener {
            startActivity(Intent(applicationContext,DragonHatch::class.java))
        }
    }


    // Create new Dialog and ask User for a name dragon --> return this name
    private fun createDragonAndPlay(element: ElementSelect) {
        val selectionName = layoutInflater.inflate(R.layout.select_dragon_name, null)
        val selectionBuilder = AlertDialog.Builder(this)
            .setView(selectionName)
        val selectShow = selectionBuilder.show()

        val continueButton = selectionName.findViewById<Button>(R.id.continue_button)
        continueButton.setOnClickListener {

            val dragonNameEditText = selectionName.findViewById<EditText>(R.id.dragon_name)
            val dragonName: String = dragonNameEditText.text.toString()
            if (TextUtils.isEmpty(dragonName)) {
                dragonNameEditText.setError("Dragon Name is required")
                return@setOnClickListener

            } else {
                val dragon = Dragon(dragonName, element, 0, 0)
                DragonManager.saveDragonInSharedPref(this, dragon)

                Log.d(TAG, "getNameDialog:: after Button Listener")
                selectShow.dismiss()
                startActivity(Intent(applicationContext, Game::class.java))
            }

        }


    }


}