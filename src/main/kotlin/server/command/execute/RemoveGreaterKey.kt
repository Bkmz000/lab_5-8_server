package server.command.execute

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

class RemoveGreaterKey : ClientCommand() {

    override val name: String = "remove_greater_key"

    private val productId: Int = args!![0] as Int

    override fun execute(): JsonElement {
        return if(productCollection.products.entries.removeAll { it.key > productId }){
            Json.encodeToJsonElement("Products were successfully removed")
        } else {
            Json.encodeToJsonElement("No such elements")
        }
    }

}