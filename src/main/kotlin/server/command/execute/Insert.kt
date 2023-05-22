package server.command.execute


import server.product.Product
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import server.command.execute.ClientCommand


class Insert : ClientCommand {

    private val productId: Int
    override val name = "insert"


    constructor( productId: Int) {
        this.productId = productId
    }

    companion object{
        const val info: String = "Adds a product to the collection"
    }


    override fun execute(arg: Any?): JsonElement {

          return if(!productCollection.products.containsKey(productId)){
                 productCollection.products[productId] = arg as Product
                    return if(productCollection.products.containsKey(productId)){
                        Json.encodeToJsonElement("Product with id ($productId) was successfully added")
                    } else {
                        Json.encodeToJsonElement("Unable to add")
                    }
          } else {
              Json.encodeToJsonElement("The product with id($productId) already exist")
          }

    }
}