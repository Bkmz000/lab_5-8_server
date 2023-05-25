package server.command.execute

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import org.koin.core.component.KoinComponent

class Show : ClientCommand(), KoinComponent {

    override val name: String = "show"

    companion object{
        const val info = "Displays the list of all products in the collection"
    }


    override fun execute(): JsonElement {
        val json = Json {prettyPrint = true}

        return if(productCollection.products.isNotEmpty()){
            val productsAsString = productCollection.products.toList().joinToString {
                "\n" + it.toString()
            }
            Json.encodeToJsonElement(productsAsString)
        } else {
            json.encodeToJsonElement("The collection is empty")
        }
    }
}