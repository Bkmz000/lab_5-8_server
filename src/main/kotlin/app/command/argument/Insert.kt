package app.command.argument


import app.command.ClientCommand
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement


class Insert : ClientCommand {

    private val productId: Int
    constructor( arg: Int) {
        this.productId = arg
    }

    companion object{
        const val info: String = "Adds a product to the collection"
    }


    override fun execute(): JsonElement {

          return if(!productCollection.products.containsKey(productId)){
             val product = ProductBuilder().build()
                return if(product != null){
                 productCollection.products[productId] = product
                    return if(productCollection.products.containsKey(productId)){
                        Json.encodeToJsonElement("Product with id ($productId) was successfully added")
                    } else {
                        Json.encodeToJsonElement("Unable to add")
                    }
             } else {
                    Json.encodeToJsonElement("Unable to add")
             }
         } else {
              Json.encodeToJsonElement("The product with id($productId) already exist")
         }

    }


}