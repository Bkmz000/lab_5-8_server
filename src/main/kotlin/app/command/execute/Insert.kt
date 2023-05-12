package app.command.execute


import app.command.`object`.ProductBuilder
import app.command.`object`.ProductBuilderCLI
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import org.koin.core.component.inject


class Insert : ClientCommand {

    private val productId: Int

    val productBuilder by inject<ProductBuilderCLI>()
    constructor( arg: Int) {
        this.productId = arg
    }

    companion object{
        const val info: String = "Adds a product to the collection"
    }


    override fun execute(): JsonElement {

          return if(!productCollection.products.containsKey(productId)){
             val product = productBuilder.build()
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