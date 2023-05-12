package app.command.invoke

import app.command.execute.ClientCommand
import kotlinx.serialization.json.decodeFromJsonElement
import kotlin.reflect.KFunction

class CommandBuilder {

    fun build(pairOfConstructAndArgs:  Pair<KFunction<Any>, ArrayList<Any>?>) : ClientCommand{

            val command = pairOfConstructAndArgs.first
            val args = pairOfConstructAndArgs.second

                return when(args?.size) {
                    1 -> command.call(args[0]) as ClientCommand
                    2 -> command.call(args[0], args[1]) as ClientCommand
                    else -> command.call() as ClientCommand
                }
    }
}