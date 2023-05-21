package server.command.execute

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.reflect.full.*

class Help : ClientCommand(), KoinComponent {
    private val commandNames by inject<AllCommands>()
    override val name = "help"
    companion object{
        const val info = "Show all commands"
    }


    private fun ArrayList<String>.toCustomString() : String{
        val builder = StringBuilder()
        builder.append("The list of all commands:\n")
        for (item in this){
            builder.append("\n")
                  .append(item)

        }
        return builder.toString()
    }


    override fun execute(arg: Any?): JsonElement {
        val listOfCommands = ArrayList<String>()

        for(item in commandNames.commands){
            val propertyWithTheInfo = item.value.companionObject?.declaredMemberProperties?.find { it.name == "info" }
            if(propertyWithTheInfo != null){
                val n = propertyWithTheInfo.getter.call(item.value.companionObjectInstance)
                listOfCommands.add("${item.key} - $n")
            } else {
                listOfCommands.add("${item.key} - no info yet")
            }
        }

        return if (listOfCommands.isNotEmpty()){
            return Json.encodeToJsonElement(listOfCommands.toCustomString())
        } else
            Json.encodeToJsonElement("No information about the commands")

    }



}