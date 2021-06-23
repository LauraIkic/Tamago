package at.fhooe.me.android.mydragon.dragonController

import android.app.Activity
import android.content.Context
import android.widget.Toast

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
            defaultDragon = dragon
        }

        fun getDragonFromSharedPref(context: Context): Dragon? {
            val appSharedPrefs = context.getSharedPreferences("Dragon", Activity.MODE_PRIVATE)
            val name = appSharedPrefs.getString("Name", null)
            val elementString = appSharedPrefs.getString("Element", null)
            val daysCounter = appSharedPrefs.getInt("DaysCounter", -1)
            val lastTouchInput = appSharedPrefs.getLong("lastTouchInput", -1)

            var element: ElementSelect? = null

            if (elementString == "Water") {
                element = ElementSelect.Water
            }
            if (elementString == "Fire") {
                element = ElementSelect.Fire
            }

            if (name == null && elementString == null && daysCounter == -1) {
                return null
            }

            return Dragon(
                name!!,
                element!!,
                daysCounter,
                lastTouchInput
            )
        }

        fun resetDragon(context: Context) {
            defaultDragon = null
            val appSharedPrefs = context.getSharedPreferences("Dragon", Activity.MODE_PRIVATE)
            val editor = appSharedPrefs.edit()
            editor.clear()
            editor.apply()
        }


    }

}