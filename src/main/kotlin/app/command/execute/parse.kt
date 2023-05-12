package app.command.execute

import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import product.Product
import java.util.SortedMap
import java.util.TreeMap

class parse : ClientCommand {

    constructor()
    override fun execute(): JsonElement {
        val json = Json {prettyPrint = true}
        return if(productCollection.products.isNotEmpty()){
            val collectionJson = json.encodeToString(MapSerializer(Int.serializer(),Product.serializer()),productCollection.products)
            println(collectionJson)
            val json = Json.parseToJsonElement(collectionJson)
            println("json = $json")

            val newMap  = json.jsonObject.toSortedMap()
            val newTreeMap = TreeMap<Int,Product>()
            newMap.forEach {
                val key = it.key.toInt()
                val value = Json.decodeFromJsonElement<Product>(it.value)
                newTreeMap.put(key, value)
            }

            println(newTreeMap.get(1)!!::class)
            Json.encodeToJsonElement(collectionJson)
        } else
            Json.encodeToJsonElement("The collection is empty")
    }
}