package at.fhooe.me.android.mydragon.dragonController

import android.app.Activity
import android.content.Context

class DragonManager {

    companion object {
        var defaultDragon: Dragon? = null

        fun saveDragonInSharedPref(context: Context, dragon: Dragon) {
            val appSharedPrefs = context.getSharedPreferences("Dragon", Activity.MODE_PRIVATE)
            val editor = appSharedPrefs.edit()
            editor.putString("Name", dragon.name)
            editor.putString("Element", dragon.element.toString())
            editor.putInt("DaysCounter", dragon.daysCounter)
            editor.apply()

            defaultDragon = dragon
        }

        fun getDragonFromSharedPref(context: Context): Dragon {
            val appSharedPrefs = context.getSharedPreferences("Dragon", Activity.MODE_PRIVATE)
            val name = appSharedPrefs.getString("Name", "")
            val elementString = appSharedPrefs.getString("Element", "")
            val daysCounter = appSharedPrefs.getInt("DaysCounter", -1)

            var element: ElementSelect? = null

            if (elementString == "Water") {
                element = ElementSelect.Water
            }
            if (elementString == "Wind") {
                element = ElementSelect.Wind
            }

            return Dragon(
                name!!,
                element!!,
                daysCounter
            )
        }


    }

}