package app.command.execute

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

class RemoveGreaterKey : ClientCommand {

    private val productId: Int

    constructor(key: Int) : super() {
        this.productId = key
    }


    override fun execute(): JsonElement {
        return if(productCollection.products.entries.removeAll { it.key > productId }){
            Json.encodeToJsonElement("Products were successfully removed")
        } else {
            Json.encodeToJsonElement("No such elements")
        }

    }
}