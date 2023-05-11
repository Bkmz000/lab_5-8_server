package app.command.nonargument

import app.collection.ProductCollection
import app.command.ClientCommand
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Show : ClientCommand(), KoinComponent {

    companion object{
        const val info = "Displays the list of all products in the collection"
    }


    override fun execute(): String? {
        val products = Json.decodeFromJsonElement<String>(productCollection.showProducts())
        println(productCollection.products)
        return "збс"
    }
}