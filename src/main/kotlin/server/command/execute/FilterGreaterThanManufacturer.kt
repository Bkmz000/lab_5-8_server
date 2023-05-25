package server.command.execute

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

class FilterGreaterThanManufacturer : ClientCommand() {

    override val name: String = "filter_greater_than_manufacturer"

    private val nameOfManufacturer: String = args!![0] as String


    override fun execute(): JsonElement {
        val manufactures = productCollection.products.entries.filter {
            it.value.manufacturer.name.length > this.nameOfManufacturer.length }
        return if(manufactures.isNotEmpty()){
            Json.encodeToJsonElement(manufactures)
        } else {
            Json.encodeToJsonElement("No such elements")
        }
    }
}