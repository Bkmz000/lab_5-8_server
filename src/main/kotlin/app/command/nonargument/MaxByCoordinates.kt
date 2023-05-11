package app.command.nonargument

import app.command.ClientCommand

class MaxByCoordinates : ClientCommand() {

    override fun execute(): String? {
        println(productCollection.products.values.maxBy { it.coordinates.x + it.coordinates.y })
        return "Success"
    }
}