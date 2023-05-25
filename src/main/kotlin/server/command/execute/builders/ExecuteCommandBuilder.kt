package server.command.execute.builders

import org.koin.core.component.KoinComponent
import server.command.execute.ClientCommand
import server.command.execute.samples.AllExecuteCommands
import server.command.execute.samples.ExecuteSample
import server.command.packets.ExecutePacket
import kotlin.reflect.*
import kotlin.reflect.full.primaryConstructor

object ExecuteCommandBuilder : KoinComponent {


    fun getExecuteCommand(executePacket: ExecutePacket): ClientCommand? {
        val executeSample = executePacket.executeSample
        val commandConstructor = getInstanceOfCommandConstructor(executeSample) ?: return null

        val listOfArgs = executePacket.listOfArgs
        listOfArgs?.let { return commandConstructor.call(it) as ClientCommand }
            ?: return commandConstructor.call() as ClientCommand

    }


    private fun getInstanceOfCommandConstructor(executeSample: ExecuteSample): KFunction<Any>?{
            val commandName = executeSample.name
            val commandKClass = AllExecuteCommands.classesWithNames[commandName] ?: return null
            return commandKClass.primaryConstructor
    }
}