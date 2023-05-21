package server.command.execute

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

class RemoveGreaterKey : ClientCommand {

    private val productId: Int

    constructor(key: Int) : super() {
        this.productId = key
    }
    override val name = "remove_greater_key"


    override fun execute(arg: Any?): JsonElement {
        return if(productCollection.products.entries.removeAll { it.key > productId }){
            Json.encodeToJsonElement("Products were successfully removed")
        } else {
            Json.encodeToJsonElement("No such elements")
        }

    }
}