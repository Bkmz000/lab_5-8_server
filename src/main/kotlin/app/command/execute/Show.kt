package app.command.execute

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import org.koin.core.component.KoinComponent

class Show : ClientCommand(), KoinComponent {

    override val name = "show"
    companion object{
        const val info = "Displays the list of all products in the collection"
    }


    override fun execute(arg: Any?):JsonElement {
        val json = Json {prettyPrint = true}

        return if(productCollection.products.isNotEmpty()){
            val mapAsString = StringBuilder()

            for (item in productCollection.products){
                mapAsString.append(item.toString())

            }
            json.encodeToJsonElement(mapAsString.toString())
        } else {
            json.encodeToJsonElement("The collection is empty")
        }
    }
}