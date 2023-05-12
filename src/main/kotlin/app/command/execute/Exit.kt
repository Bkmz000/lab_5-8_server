package app.command.execute

import kotlinx.serialization.json.JsonElement
import kotlin.system.exitProcess

class Exit : ClientCommand() {
    override fun execute(): JsonElement {
        exitProcess(0)
    }
}