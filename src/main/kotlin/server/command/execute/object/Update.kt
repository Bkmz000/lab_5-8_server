package server.command.execute.`object`

import server.command.execute.ClientCommand
import server.command.`object`.ProductBuilder
import server.command.`object`.ProductBuilderCLI
import server.command.`object`.ProductBuilderScript
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

class Update : ClientCommand {


    private val productId: Int
    private lateinit var productBuilder: ProductBuilder
    override val name = "update"

    constructor(arg: Int) {
        this.productId = arg
    }

    companion object{
        const val info = "Updates the product with the given id"
    }



    override fun execute(arg: Any?): JsonElement {
        return if(productCollection.products.containsKey(productId)){
            val productBuilder: ProductBuilder = if (arg != null) ProductBuilderScript() else ProductBuilderCLI()
            val newProduct = productBuilder.build(arg)
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