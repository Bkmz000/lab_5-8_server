package app.command.nonargument

import app.collection.ProductCollection
import app.command.ClientCommand
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Show : ClientCommand(), KoinComponent {

    companion object{
        const val info = "Displays the list of all products in the collection"
    }


    override fun execute():JsonElement {
        return if(productCollection.products.isNotEmpty()){
            Json.encodeToJsonElement(productCollection.products.toString())
        } else {
            Json.encodeToJsonElement("The collection is empty")
        }
    }
}