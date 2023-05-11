package app.command.nonargument

import app.command.ClientCommand
import kotlin.system.exitProcess

class Exit : ClientCommand() {
    override fun execute(): String? {
        exitProcess(0)
    }
}