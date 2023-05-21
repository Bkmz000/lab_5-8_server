package client.command.processing

import client.product.Product
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class CommandPacket (
    val requestType: RequestType,
    val command: String,
    val args: List<@Contextual Any>? = null,
    val product: Product? = null,
    )