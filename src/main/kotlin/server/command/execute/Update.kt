package server.command.execute

import server.command.execute.ClientCommand
import server.command.`object`.ProductBuilder
import server.command.`object`.ProductBuilderCLI
import server.command.`object`.ProductBuilderScript
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import server.product.Product

class Update : ClientCommand {


    private val productId: Int
    override val name = "update"

    constructor(arg: Int) {
        this.productId = arg
    }

    companion object{
        const val info = "Updates the product with the given id"
    }



    override fun execute(arg: Any?): JsonElement {
        return if(productCollection.products.containsKey(productId)){
            if(arg != null){
                productCollection.products.replace(productId,arg as Product)
                    Json.encodeToJsonElement("Product was successfully updated")
            } else {
                Json.encodeToJsonElement("Unable to add")
            }

        } else {
            Json.encodeToJsonElement("No such element")
        }
    }
}