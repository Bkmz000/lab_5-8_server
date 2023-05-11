package app.command.argument


import app.collection.ProductCollection
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import product.*


class Insert : ArgumentCommand, KoinComponent {

    override val arg: Int

    constructor(arg: Int) : super(arg) {
        this.arg = arg
    }

    companion object{
        const val info: String = "Adds a product to the collection"
    }


    override fun execute(): String? {

         if(!productCollection.products.containsKey(arg)){
             val product = ProductBuilder().build()
             if(product != null){
                 productCollection.addProduct(arg,product)
             } else {
                 return "Unable to add!"
             }
         } else {
             return "The product with id($arg) already exist"
         }
        return null
    }



}