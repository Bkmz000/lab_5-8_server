package client.command

data class CommandPacket (
    val requestType: RequestType,
    val command: String,
    val args: List<Any>? = null)