package server.command.packets

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import server.command.execute.samples.ExecuteSample
import server.product.Product

@Serializable
data class ExecutePacket(
    val executeSample: ExecuteSample,
    val listOfArgs: MutableList<out @Contextual Any>? = null,
)