package app.command.nonargument

import app.command.AllCommands
import app.command.ClientCommand
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.reflect.full.*

class Help : ClientCommand(), KoinComponent {
    private val commandNames by inject<AllCommands>()

    companion object{
        val info = "Show all commands"
    }


    override fun execute(): String? {
        for(item in commandNames.commands){
            val propertyWithTheInfo = item.value.companionObject?.declaredMemberProperties?.find { it.name == "info" }
            if(propertyWithTheInfo != null){
                val n = propertyWithTheInfo?.getter?.call(item.value.companionObjectInstance)
                println("${item.key} - $n")
            } else {
                println("${item.key} - no info yet")
            }

        }
        return "Success"
    }
}