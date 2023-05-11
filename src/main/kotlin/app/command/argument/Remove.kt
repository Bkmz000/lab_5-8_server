package app.command.argument

import app.command.ClientCommand

class Remove : ClientCommand {

    private val arg: Int

    constructor(arg: Int) {
        this.arg = arg
    }

    override fun execute(): String? {
        return if(productCollection.products.containsKey(arg)){
            val result = productCollection.removeProduct(arg)
            if(result == "Success") {
                "Product with id ($arg) was successfully removed"
            } else {
                result
            }

        } else {
            "No such element"
        }
    }
}