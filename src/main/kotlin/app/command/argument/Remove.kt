package app.command.argument

class Remove : ArgumentCommand {

    override val arg: Int

    constructor(arg: Int) : super(arg) {
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