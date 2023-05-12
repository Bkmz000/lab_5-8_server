package app.command.execute

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

class FilterStartsWithName : ClientCommand {


    private val name: String

    constructor(name: String) {
        this.name = name
    }


    override fun execute(): JsonElement {
        val products = productCollection.products.entries.filter { it.value.name.startsWith(name) }
        return if (products.isNotEmpty()){
            Json.encodeToJsonElement(products)
        } else {
            Json.encodeToJsonElement("No such elements")
        }
    }
}