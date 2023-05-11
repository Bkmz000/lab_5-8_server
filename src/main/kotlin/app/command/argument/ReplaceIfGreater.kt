package app.command.argument

import app.command.ClientCommand

class ReplaceIfGreater : ClientCommand {

    val key:Int
    val price: Int

    constructor(key: Int, price: Int) {
        this.key = key
        this.price = price
    }


    override fun execute(): String? {
        return if(productCollection.products.containsKey(key)){
            if(productCollection.products[key]?.price!! < this.price){
                val newProduct = ProductBuilder().build()
                if(newProduct != null) {
                    productCollection.replaceProduct(key,newProduct)
                    "Success"
                } else "Unable to add"
            } else "The price is not greater"
        } else "No such element"



    }
}