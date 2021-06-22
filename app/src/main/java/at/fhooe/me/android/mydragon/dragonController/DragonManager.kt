package at.fhooe.me.android.mydragon.dragonController

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData

class DragonManager {

    companion object {
        var defaultDragon: Dragon? = null

        fun saveDragonInSharedPref(context: Context, dragon: Dragon) {
            val appSharedPrefs = context.getSharedPreferences("Dragon", Activity.MODE_PRIVATE)
            val editor = appSharedPrefs.edit()
            editor.putString("Name", dragon.name)
            editor.putString("Element", dragon.element.toString())
            editor.putInt("DaysCounter", dragon.daysCounter)
            editor.putLong("lastTouchInput", dragon.lastTouchInput)
            editor.apply()

            Toast.makeText(context, "Saved Dragon in Shared Pref", Toast.LENGTH_SHORT).show()
            defaultDragon = dragon
        }

        fun getDragonFromSharedPref(context: Context): Dragon {
            val appSharedPrefs = context.getSharedPreferences("Dragon", Activity.MODE_PRIVATE)
            val name = appSharedPrefs.getString("Name", "")
            val elementString = appSharedPrefs.getString("Element", "")
            val daysCounter = appSharedPrefs.getInt("DaysCounter", -1)
            val lastTouchInput = appSharedPrefs.getLong("lastTouchInput", -1)

            var element: ElementSelect? = null

            if (elementString == "Water") {
                element = ElementSelect.Water
            }
            if (elementString == "Wind") {
                element = ElementSelect.Wind
            }

            if (elementString == "Fire"){
                element= ElementSelect.Fire
            }

            if (elementString == "Earth"){
                element = ElementSelect.Earth
            }



            return Dragon(
                name!!,
                element!!,
                daysCounter,
                lastTouchInput
            )
        }


    }

}