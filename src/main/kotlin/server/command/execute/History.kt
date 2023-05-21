package server.command.execute

import server.command.invoke.CommandInvoker
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import org.koin.core.component.inject

class History : ClientCommand() {

    private val commandInvoker by inject<CommandInvoker>()

    override val name: String = "history"

    override fun execute(arg: Any?): JsonElement {
        val listOfCommands = ArrayList<String>()
        commandInvoker.commands.forEach {
            listOfCommands.add(it.name)
        }

        return if(listOfCommands.isNotEmpty()){
            val result = "Last 5 commands : $listOfCommands"
            Json.encodeToJsonElement(result)
        }
        else
            Json.encodeToJsonElement("You haven't entered any commands yet")


    }
}