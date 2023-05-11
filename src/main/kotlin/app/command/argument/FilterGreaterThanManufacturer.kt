package app.command.argument

import app.command.ClientCommand

class FilterGreaterThanManufacturer : ClientCommand {

    private val nameOfManufacturer: String

    constructor(nameOfManufacturer: String) {
        this.nameOfManufacturer = nameOfManufacturer
    }


    override fun execute(): String? {
        val values = productCollection.products.entries.filter {
            it.value.manufacturer.name.length > this.nameOfManufacturer.length }
        println(values)
        return "Success"
    }
}