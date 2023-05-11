package app.command.nonargument

import app.collection.ProductCollection
import app.command.ClientCommand
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Info : ClientCommand(), KoinComponent {


    private val collectionType = productCollection.products::class.toString()
    private val creationDate = productCollection.creationDate
    private val numberOfElements = productCollection.products.size

    override fun execute(): JsonElement {

        return Json.encodeToJsonElement(this.toString())


    }

    override fun toString(): String {
        return "CollectionType = '${collectionType.substringAfter("util.")}', CreationDate = '$creationDate', Number of elements = $numberOfElements"
    }


}