package app.command.argument

import app.collection.ProductCollection
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import product.Product

class Update : ArgumentCommand, KoinComponent {


    override val arg: Int

    constructor(arg: Int) : super(arg) {
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