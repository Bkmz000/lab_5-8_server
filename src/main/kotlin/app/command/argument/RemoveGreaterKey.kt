package app.command.argument

import app.command.ClientCommand

class RemoveGreaterKey : ClientCommand {

    private val key: Int

    constructor(key: Int) : super() {
        this.key = key
    }


    override fun execute(): String? {
        productCollection.removeGreaterKey(key)
        return "Success"
    }
}