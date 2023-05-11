package app.command

import app.command.argument.Insert
import app.command.argument.Remove
import app.command.argument.Update
import app.command.nonargument.*
import kotlin.reflect.KClass

class AllCommands {


    val commands = HashMap<String,KClass<*>>()


    init {
        commands["insert"] =  Insert::class
        commands["show"] = Show::class
        commands["help"] = Help::class
        commands["info"] = Info::class
        commands["update"] = Update::class
        commands["remove"] = Remove::class
        commands["clear"] = Clear::class
        commands["exit"] = Exit::class
    }


}