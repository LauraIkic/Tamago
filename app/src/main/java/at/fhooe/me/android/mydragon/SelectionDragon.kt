package at.fhooe.me.android.mydragon

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import at.fhooe.me.android.mydragon.databinding.SelectDragonEggBinding
import at.fhooe.me.android.mydragon.R.layout.select_dragon_name
import at.fhooe.me.android.mydragon.dragonController.Dragon
import at.fhooe.me.android.mydragon.dragonController.DragonManager
import at.fhooe.me.android.mydragon.dragonController.ElementSelect

class SelectionDragon: Activity(){
    lateinit var binding:SelectDragonEggBinding



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = SelectDragonEggBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // val element = ManageDragon.getElement(applicationContext)

        var element: String = ""


            binding.waterDragon.setOnClickListener{

            element = "water"





            val selectionName = LayoutInflater.from(this).inflate(select_dragon_name, null);
            val selectionBuilder = AlertDialog.Builder(this)
                    .setView(selectionName)
             val selectShow = selectionBuilder.show()


                val button = selectionName.findViewById<Button>(R.id.continue_button)
                button.setOnClickListener{
                    selectShow.dismiss()
                }
             /*  selectShow.getButton(R.id.continue_button).setOnClickListener{
                   startActivity(Intent(applicationContext,Game::class.java))
               }
*/
        }
    }




}