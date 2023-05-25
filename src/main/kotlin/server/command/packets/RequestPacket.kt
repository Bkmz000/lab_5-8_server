package server.command.packets

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import server.command.execute.samples.ExecuteSample

@Serializable
data class RequestPacket (
    val requestType: RequestType,
    val args: List<ExecuteSample>? = null,
){
    constructor(requestType: RequestType, executeSample: ExecuteSample ) : this(requestType, mutableListOf(executeSample))

}