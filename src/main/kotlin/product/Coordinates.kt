package product

import kotlinx.serialization.Serializable

@Serializable
class Coordinates {

    private val x: Int
    private val y: Double

    constructor(x:Int, y:Double){
        this.x = x
        this.y = y
    }

    override fun toString(): String {
        return "Coordinates(x=$x, y=$y)"
    }


}