package server.command.execute

import server.collection.ProductCollection
import kotlinx.serialization.json.JsonElement
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class ClientCommand(open val args: MutableList<out Any>? = null) : KoinComponent {

    val productCollection by inject<ProductCollection>()

    abstract val name: String
    abstract fun execute(): JsonElement



}