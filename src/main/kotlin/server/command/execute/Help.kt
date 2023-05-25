package server.command.execute

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import server.command.execute.samples.AllExecuteCommands
import kotlin.reflect.full.*

class Help : ClientCommand(), KoinComponent {

    override val name: String = "help"

    private val commandNames by inject<AllExecuteCommands>()

    companion object{
        const val info = "Show all commands"
    }


    override fun execute(): JsonElement {
        val listOfCommandsWithInfo = ArrayList<String>()

        for(command in commandNames.classesWithNames){
            val propertyWithTheInfo = command.value.companionObject?.declaredMemberProperties?.find { it.name == "info" }
            if(propertyWithTheInfo != null){
                val infoOfCommand = propertyWithTheInfo.getter.call(command.value.companionObjectInstance)
                listOfCommandsWithInfo.add("${command.key} - $infoOfCommand")
            } else {
                listOfCommandsWithInfo.add("${command.key} - no info yet")
            }
        }

        return if (listOfCommandsWithInfo.isNotEmpty()){
            return Json.encodeToJsonElement(listOfCommandsWithInfo.toFormatString())
        } else
            Json.encodeToJsonElement("No information about the commands")

    }

    private fun ArrayList<String>.toFormatString() : String{
        val builder = StringBuilder()
        builder.append("The list of all commands:\n")
        for (item in this){
            builder.append("\n")
                .append(item)

        }
        return builder.toString()
    }



}