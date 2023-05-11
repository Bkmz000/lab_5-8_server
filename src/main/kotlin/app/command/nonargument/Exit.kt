package app.command.nonargument

import app.command.ClientCommand
import kotlinx.serialization.json.JsonElement
import kotlin.system.exitProcess

class Exit : ClientCommand() {
    override fun execute(): JsonElement {
        exitProcess(0)
    }
}