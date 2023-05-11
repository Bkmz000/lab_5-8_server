package app.command.argument

import app.command.ClientCommand
import org.koin.core.component.KoinComponent

class Update : ClientCommand {


    private val arg: Int

    constructor(arg: Int) {
        this.arg = arg
    }

    companion object{
        const val info = "Updates the product with the given id"
    }



    override fun execute(): String? {
        return if(productCollection.products.containsKey(arg)){
            val product = ProductBuilder().build()
            if(product != null){
                productCollection.replaceProduct(arg,product)
                "Success"
            } else {
                "Unable to add"
            }

        } else {
            "No such element"
        }
    }
}