package app.command.argument

import app.command.ClientCommand

abstract class ArgumentCommand: ClientCommand() {

    abstract val arg: Any
    abstract override fun execute(): String?
}