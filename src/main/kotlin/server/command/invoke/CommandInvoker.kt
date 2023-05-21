package server.command.invoke

import server.command.execute.ClientCommand
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import org.koin.core.component.KoinComponent

class CommandInvoker : KoinComponent {
    val commands = mutableListOf<ClientCommand>()

    fun executeCommand(command: ClientCommand){
        println(Json.decodeFromJsonElement<String>(command.execute()))
        commands.add(command)
        if(commands.size >= 5) commands.removeFirst()
    }


}