package app.command.execute

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

class MaxByCoordinates : ClientCommand() {

    override val name: String = "max_by_coordinates"

    override fun execute(arg: Any?): JsonElement {

        return if(productCollection.products.isNotEmpty()){
            val productWithMaxCoordinates = productCollection.products.values.maxBy { it.coordinates.x + it.coordinates.y }
            Json.encodeToJsonElement(productWithMaxCoordinates.toString())
        }
         else
            Json.encodeToJsonElement("The collection is empty")
    }
}