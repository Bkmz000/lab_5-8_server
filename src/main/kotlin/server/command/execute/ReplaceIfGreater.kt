package server.command.execute

import server.command.`object`.ProductBuilderCLI
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

class ReplaceIfGreater : ClientCommand() {

    override val name: String = "replace_if_greater"

    private val productId:Int = args!![0] as Int
    private val price: Int = args!![1] as Int

    override fun execute(): JsonElement {
        return if(productCollection.products.containsKey(productId)){
            val priceOfProduct = productCollection.products[productId]?.price
            if(priceOfProduct!! < this.price){
                val newProduct = ProductBuilderCLI().build()
                if(newProduct != null) {
                    productCollection.products.replace(productId,newProduct)
                    Json.encodeToJsonElement("Product was successfully replaced")
                } else Json.encodeToJsonElement("Unable to add")
            } else Json.encodeToJsonElement("The price ($price) is not greater then $priceOfProduct")
        } else Json.encodeToJsonElement("No such element")
    }
}