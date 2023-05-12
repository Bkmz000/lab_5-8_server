package app.command.execute

import app.collection.ProductCollection
import kotlinx.serialization.json.JsonElement
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class ClientCommand : KoinComponent {

    val productCollection by inject<ProductCollection>()

    abstract val name: String
    abstract fun execute(arg: Any? = null): JsonElement



}