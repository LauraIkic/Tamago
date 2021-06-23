package at.fhooe.me.android.mydragon.dragonController


enum class ElementSelect {
    Water, Fire
}

data class Dragon(
    var name: String,
    var element: ElementSelect,
    var daysCounter: Int,
    var lastTouchInput: Long
)


