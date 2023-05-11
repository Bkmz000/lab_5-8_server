package app.command.argument

import app.command.ClientCommand
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

class Remove : ClientCommand {

    private val productId: Int

    constructor(arg: Int) {
        this.productId = arg
    }

    override fun execute(): JsonElement {
        return if(productCollection.products.containsKey(productId)){
             productCollection.products.remove(productId)
            if (!productCollection.products.containsKey(productId)){
                Json.encodeToJsonElement("Product with id ($productId) was successfully removed")
            } else {
                Json.encodeToJsonElement("Product with id ($productId) was not removed")
            }

        } else {
            Json.encodeToJsonElement("No such element")
        }
    }
}