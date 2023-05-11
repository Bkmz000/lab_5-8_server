package app.command.nonargument

import app.command.AllCommands
import app.command.ClientCommand
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.internal.writeJson
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.reflect.full.*

class Help : ClientCommand(), KoinComponent {
    private val commandNames by inject<AllCommands>()

    companion object{
        val info = "Show all commands"
    }


    override fun execute(): JsonElement {
        val listOfCommands = ArrayList<String>()

        for(item in commandNames.commands){
            val propertyWithTheInfo = item.value.companionObject?.declaredMemberProperties?.find { it.name == "info" }
            if(propertyWithTheInfo != null){
                val n = propertyWithTheInfo?.getter?.call(item.value.companionObjectInstance)
                listOfCommands.add("${item.key} - $n")
            } else {
                listOfCommands.add("${item.key} - no info yet")
            }
        }

        return if (listOfCommands.isNotEmpty()){
            return Json.encodeToJsonElement(listOfCommands.toString())
        } else
            Json.encodeToJsonElement("No information about the commands")

    }
}