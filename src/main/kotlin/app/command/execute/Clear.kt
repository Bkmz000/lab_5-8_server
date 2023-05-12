package app.command.execute

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

class Clear : ClientCommand() {


    override val name: String = "clear"


    override fun execute(arg: Any?): JsonElement {
        productCollection.products.clear()
        return if(productCollection.products.isEmpty())
            Json.encodeToJsonElement("Success")
        else
            Json.encodeToJsonElement("The collection has not been cleaned")

    }
}