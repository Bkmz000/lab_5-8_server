package app.command.execute

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

class FilterGreaterThanManufacturer : ClientCommand {

    private val nameOfManufacturer: String

    constructor(nameOfManufacturer: String) {
        this.nameOfManufacturer = nameOfManufacturer
    }
    override val name: String = "filter_greater_than_manufacturer"


    override fun execute(arg: Any?): JsonElement {
        val manufactures = productCollection.products.entries.filter {
            it.value.manufacturer.name.length > this.nameOfManufacturer.length }
        return if(manufactures.isNotEmpty()){
            Json.encodeToJsonElement(manufactures)
        } else {
            Json.encodeToJsonElement("No such elements")
        }
    }
}