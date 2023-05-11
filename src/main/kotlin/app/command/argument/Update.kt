package app.command.argument

import app.command.ClientCommand
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

class Update : ClientCommand {


    private val productId: Int

    constructor(arg: Int) {
        this.productId = arg
    }

    companion object{
        const val info = "Updates the product with the given id"
    }



    override fun execute(): JsonElement {
        return if(productCollection.products.containsKey(productId)){
            val newProduct = ProductBuilder().build()
            if(newProduct != null){
                productCollection.products.replace(productId,newProduct)
                    Json.encodeToJsonElement("Product was successfully updated")
            } else {
                Json.encodeToJsonElement("Unable to add")
            }

        } else {
            Json.encodeToJsonElement("No such element")
        }
    }
}