package server.command.execute.`object`


import server.product.Product
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement


class Insert : ObjectCommand {

    private val productId: Int
    override val name = "insert"

    private lateinit var product : Product

    constructor( productId: Int) {
        this.productId = productId
    }

    companion object{
        const val info: String = "Adds a product to the collection"
    }


    override fun setProductBuilder(product: Product) {
        this.product = product
    }

    override fun execute(arg: Any?): JsonElement {

          return if(!productCollection.products.containsKey(productId)){
                 productCollection.products[productId] = product
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