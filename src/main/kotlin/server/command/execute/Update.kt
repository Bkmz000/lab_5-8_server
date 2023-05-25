package server.command.execute

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import server.product.Product

class Update : ClientCommand() {

    override val name: String = "update"


    private val productId: Int = args!![0] as Int
    private val product: Product = args!![1] as Product

    companion object{
        const val info = "Updates the product with the given id"
    }


    override fun execute(): JsonElement {
        return if (productCollection.products.containsKey(productId)) {
            productCollection.products.replace(productId, product)
            Json.encodeToJsonElement("Product was successfully updated")
        } else { Json.encodeToJsonElement("There is no element with id $productId") }
    }
}