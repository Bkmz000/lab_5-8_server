package server

import server.command.invoke.CommandInvoker
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class StartApp : KoinComponent {

    private val commandInvoker by inject<CommandInvoker>()


    fun start() {
//        println(Json.decodeFromJsonElement(LoadCollection().load()) as String)
//
//        while (true) {
//
//            val message = readln()
//            val pairOfConstructorAndArgs = CommandInterpretation.getExecuteCommand(message)
//            if (pairOfConstructorAndArgs != null) {
//
//                val command = CommandBuilder().getCommandOrFailureMessage(pairOfConstructorAndArgs)
//                println(command::class)
//                if(command::class == ClientCommand::class) {
//
//                    commandInvoker.executeCommand(command as ClientCommand)
//                } else {
//                    println(command)
//                    println(1)
//                }
//
//            } else
//                println("Unknown Command")
//        }
    }
}



