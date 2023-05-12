package app.command.invoke

import app.command.execute.ClientCommand

object CollectionInvoker {
    private val commands = mutableListOf<ClientCommand>()

    fun executeCommand(command: ClientCommand){
        commands.add(command)
        command.execute()
    }


}