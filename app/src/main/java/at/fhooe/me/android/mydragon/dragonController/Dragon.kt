package at.fhooe.me.android.mydragon.dragonController

enum class ElementSelect {
    Water, Wind, Fire, Earth
}

data class Dragon(
    var name: String,
    var element: ElementSelect,
    var daysCounter: Int
)


