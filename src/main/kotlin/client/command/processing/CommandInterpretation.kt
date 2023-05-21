package client.command.processing

import client.command.*
import client.command.processing.RequestType.COMMAND_EXECUTE
import client.command.`object`.ProductBuilderCLI
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.File
import kotlin.reflect.*

object CommandInterpretation : KoinComponent {

    private val allCommandSamples by inject<AllCommandSamples>()

    fun getCommandPacket(message: String) : CommandPacket?{

        val listOfWords = message.toListWithoutBlanks() ?: return null
        val commandName = listOfWords.removeFirst()
        val commandSample = allCommandSamples.samples
                                                                    .filter { it.equals(commandName) }
                                                                    .let { if(it.isNotEmpty()) it[0] else return null }

        val commandPacket = when(commandSample.type) {
            CommandType.ARGUMENT -> getArgumentCommandPacket(commandSample, listOfWords)
            CommandType.OBJECT -> getObjectCommandPacket(commandSample, listOfWords)
            CommandType.NON_ARGUMENT -> getNonArgumentCommandPacket(commandSample, listOfWords)
            CommandType.SCRIPT -> getScriptCommandPacket(commandSample, listOfWords)
        }

        return commandPacket



    }

    private fun String.toListWithoutBlanks() : MutableList<String>? {

        this.ifEmpty { null }
        val listOfWords = this.split(" ").toMutableList()
        listOfWords.removeAll { this.isBlank() }
        return listOfWords.ifEmpty { null }

    }


    private fun getNonArgumentCommandPacket(commandSample: CommandSample, listOfWords: MutableList<String>) : CommandPacket?{
        return if(listOfWords.isEmpty()) CommandPacket(COMMAND_EXECUTE, commandSample.name) else null
    }

    private fun getArgumentCommandPacket(commandSample: CommandSample, possibleArgs: MutableList<String>) : CommandPacket? {

        val commandArgs = possibleArgs.tryCastTo(commandSample.typeOfArgs!!) ?: return null

        return  CommandPacket(COMMAND_EXECUTE, commandSample.name, commandArgs)

    }

    private fun getObjectCommandPacket(commandSample: CommandSample, possibleArgs: MutableList<String>) : CommandPacket? {
        val commandArg = possibleArgs.tryCastTo(commandSample.typeOfArgs!!) ?: return null

        val product = ProductBuilderCLI().build() ?: return null

        return CommandPacket(COMMAND_EXECUTE, commandSample.name, commandArg, product)
    }

    private fun getScriptCommandPacket(commandSample: CommandSample, possibleArgs: MutableList<String>) : CommandPacket? {
        val fileWithScript = File(possibleArgs[0]).also { if (!it.isFile) return null }
        val linesFromTheFile = fileWithScript.bufferedReader().readLines()

        return CommandPacket(COMMAND_EXECUTE, commandSample.name, linesFromTheFile)
    }





    private fun MutableList<String>.tryCastTo(typeOfArgs: List<KClass<out Any>>) : MutableList<Any>?{
        val possibleArgs = this

        if(typeOfArgs.size != possibleArgs.size) return null

        val commandArgs = mutableListOf<Any>()

        typeOfArgs.forEachIndexed { index, it ->
            val argument = possibleArgs[index].tryCastTo(it)
            when(argument){
                is Outcome.Success -> commandArgs.add(argument.value)
                is Outcome.Error -> return null
            }
        }

        return commandArgs
    }


    @OptIn(ExperimentalStdlibApi::class)
    private fun String.tryCastTo(type: KClass<out Any>) : Outcome<Any> =
        try {
            val castedArg: Any
            when (type) {
                (Int::class) -> {
                    castedArg = this.toInt()
                    Outcome.Success(castedArg)
                }
                (Double::class) -> {
                    castedArg = this.toDouble()
                    Outcome.Success(castedArg)
                } (String::class) -> {
                    castedArg = this
                Outcome.Success(castedArg)
                }

                else -> {
                    Outcome.Error()
                }
            }

        } catch (ex: NumberFormatException){
        Outcome.Error()
        }

    sealed class Outcome<out T : Any> {
        data class Success<out T : Any>(val value: T) : Outcome<T>()
        data class Error(val message: String? = null, val cause: Exception? = null) : Outcome<Nothing>()
    }
}