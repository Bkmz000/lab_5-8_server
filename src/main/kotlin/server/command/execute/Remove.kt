package server.command.execute

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

class Remove : ClientCommand() {

    override val name: String = "remove"

    private val productId: Int = args!![0] as Int


    override fun execute(): JsonElement {
        return if (productCollection.products.containsKey(productId)) {
            productCollection.products.remove(productId)
            Json.encodeToJsonElement("Product with id ($productId) was successfully removed")
        } else {
            Json.encodeToJsonElement("No such element with id = $productId")
        }
    }

}