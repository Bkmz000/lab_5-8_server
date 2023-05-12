package app.command.invoke

import app.command.execute.ClientCommand
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement

object CommandInvoker {
    val commands = mutableListOf<ClientCommand>()

    fun executeCommand(command: ClientCommand){
        println(Json.decodeFromJsonElement<String>(command.execute()))
        commands.add(command)
        if(commands.size >= 5) commands.removeFirst()
    }


}