package app.command.argument

import app.command.ClientCommand

class FilterStartsWithName : ClientCommand {


    private val name: String

    constructor(name: String) {
        this.name = name
    }


    override fun execute(): String? {
        val values = productCollection.products.entries.filter { it.value.name.startsWith(name) }
        println(values)
        return "Success"
    }
}