package app

import app.command.invoke.CommandInvoker
import app.command.invoke.CommandInterpretation
import app.command.invoke.CommandBuilder
import app.command.`object`.file.LoadCollection
import kotlinx.serialization.json.*
import org.koin.core.component.KoinComponent


class App : KoinComponent {


    fun start() {
        println("Welcome to the CLI \"Product Collection\"")
        println(Json.decodeFromJsonElement(LoadCollection().load()) as String)

        while (true) {

            val message = CommandInterpretation.getPairOfCommandAndArgs(readln())
            if (message != null) {
                val command = CommandBuilder().build(message)
                CommandInvoker.executeCommand(command)
            } else
                println("Unknown Command")

        }
    }
}



