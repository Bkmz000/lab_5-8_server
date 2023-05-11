package app.command.argument


import app.command.ClientCommand
import org.koin.core.component.KoinComponent


class Insert : ClientCommand {

    private val arg: Int
    constructor( arg: Int) {
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