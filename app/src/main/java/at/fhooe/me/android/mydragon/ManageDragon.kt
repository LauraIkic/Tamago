package at.fhooe.me.android.mydragon

import android.app.Activity
import android.os.Bundle

data class ManageDragon(val element: String) {

/*
  fun setElement( element: String){
    this.element =element
}

*/
   @JvmName("getElement1")
   fun getElement(): String {
       return element
   }


}