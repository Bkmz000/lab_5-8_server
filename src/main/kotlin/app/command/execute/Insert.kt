package app.command.execute


import app.command.`object`.ProductBuilder
import app.command.`object`.ProductBuilderCLI
import app.command.`object`.ProductBuilderScript
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import org.koin.core.component.inject


class Insert : ClientCommand {

    private val productId: Int
    override val name = "insert"

    constructor( arg: Int) {
        this.productId = arg
    }

    companion object{
        const val info: String = "Adds a product to the collection"
    }




    override fun execute(arg: Any?): JsonElement {

          return if(!productCollection.products.containsKey(productId)){

              val productBuilder: ProductBuilder = if (arg != null) ProductBuilderScript() else ProductBuilderCLI()
              val product = productBuilder.build(arg)
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