package app.command.nonargument

import app.command.ClientCommand

class Clear : ClientCommand() {
    override fun execute(): String? {
        val result = productCollection.clear()
        return if (result == "Success") "Success" else "Failure"
    }
}