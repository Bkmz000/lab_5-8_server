package server.command.execute

import kotlinx.serialization.json.JsonElement
import kotlin.system.exitProcess

class Exit : ClientCommand() {

    override val name: String = "exit"

    override fun execute(arg: Any?): JsonElement {
        exitProcess(0)
    }
}