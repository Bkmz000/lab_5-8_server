package server.command.execute

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

class FilterStartsWithName : ClientCommand() {

    override val name: String = "filter_starts_with_name"

    private val subName: String = args!![0] as String


    override fun execute(): JsonElement {
        val products = productCollection.products.entries.filter { it.value.name.startsWith(subName) }
        return if (products.isNotEmpty()){
            Json.encodeToJsonElement(products.toString())
        } else {
            Json.encodeToJsonElement("No such elements")
        }
    }
}