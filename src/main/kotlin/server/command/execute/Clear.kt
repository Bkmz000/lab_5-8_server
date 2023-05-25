package server.command.execute

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

class Clear : ClientCommand() {

    override val name: String = "clear"

    override fun execute(): JsonElement {
        productCollection.products.clear()
        return Json.encodeToJsonElement("The collection is now empty")
    }

}