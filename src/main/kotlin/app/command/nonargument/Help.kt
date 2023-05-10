package app.command.nonargument

import app.command.AllCommandNames
import app.command.ClientCommand
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty
import kotlin.reflect.full.*

class Help : ClientCommand(), KoinComponent {
    private val commandNames by inject<AllCommandNames>()

    companion object{
        val info = "fdfdfd"
    }

    override val info: String
        get() = "fdfd"

    override fun execute(): String? {
        for(item in commandNames.commands.values){
//            val infoProperty = item.co   .find { it.name == "info" }
//            println(infoProperty?.call(item.companionObjectInstance))

        }
        return "fd"
    }
}