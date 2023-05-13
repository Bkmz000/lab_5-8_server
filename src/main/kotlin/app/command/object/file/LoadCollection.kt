package app.command.`object`.file


import app.collection.ProductCollection
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import app.product.Product
import java.util.*

class LoadCollection : KoinComponent {

    private val file = FileCollection.getFile()
    private val collectionOfProducts by inject<ProductCollection>()

    fun load(): JsonElement {
        return if(file != null) {
            val jsonText = file.readText()
            collectionOfProducts.products.clear()
            val textToSortedMap = Json.decodeFromString<JsonElement>(jsonText).jsonObject.toSortedMap()
            addElementsFromMapToProductCollection(textToSortedMap)
            Json.encodeToJsonElement("Collection was loaded")
        } else {
            Json.encodeToJsonElement("File with collection was not found")
        }

    }

    private fun addElementsFromMapToProductCollection(givenSortedMap: SortedMap<String, JsonElement>){
        givenSortedMap.forEach {
            val key = it.key.toInt()
            val value = Json.decodeFromJsonElement<Product>(it.value)
            collectionOfProducts.products[key] = value
        }
    }
}