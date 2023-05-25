package server.command.execute

import server.command.`object`.file.FileCollection
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import server.product.Product

class Save : ClientCommand() {

    override val name: String = "save"

    private val file = FileCollection.getFile()

    private val json = Json { prettyPrint = true }

    override fun execute(): JsonElement {

        return if (file != null){
            val collectionJson = json.encodeToString(MapSerializer(Int.serializer(), Product.serializer()),productCollection.products)
            file.writeText(collectionJson)
            json.encodeToJsonElement("Collection was successfully saved")
        } else
            json.encodeToJsonElement("File with collection was not found")


    }
}