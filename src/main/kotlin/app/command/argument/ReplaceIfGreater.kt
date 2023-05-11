package app.command.argument

import app.command.ClientCommand
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

class ReplaceIfGreater : ClientCommand {

    val productId:Int
    val price: Int

    constructor(key: Int, price: Int) {
        this.productId = key
        this.price = price
    }


    override fun execute(): JsonElement {
        return if(productCollection.products.containsKey(productId)){
            val priceOfProduct = productCollection.products[productId]?.price
            if(priceOfProduct!! < this.price){
                val newProduct = ProductBuilder().build()
                if(newProduct != null) {
                    productCollection.products.replace(productId,newProduct)
                    Json.encodeToJsonElement("Product was successfully replaced")
                } else Json.encodeToJsonElement("Unable to add")
            } else Json.encodeToJsonElement("The price ($price) is not greater then $priceOfProduct")
        } else Json.encodeToJsonElement("No such element")



    }
}