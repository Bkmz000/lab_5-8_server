package app.command

abstract class ClientCommand {

    companion object{

    }

    abstract val info: String
    abstract fun execute(): String?



}