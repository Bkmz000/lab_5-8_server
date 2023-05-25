package server.command.execute


import server.product.Product
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement


class Insert : ClientCommand() {

    override val name: String = "insert"

    private val productId: Int = args!![0] as Int
    private var product: Product = args!![1] as Product


    companion object{
        const val info: String = "Adds a product to the collection"
    }


    override fun execute(): JsonElement {
        return if (!productCollection.products.containsKey(productId)) {
            productCollection.products[productId] = product
            return Json.encodeToJsonElement("Product with id ($productId) was successfully added")
        } else {
            Json.encodeToJsonElement("The product with id($productId) already exist")
        }
    }
}