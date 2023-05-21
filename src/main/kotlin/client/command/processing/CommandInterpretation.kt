package client.command.processing

import client.command.*
import client.command.RequestType.COMMAND_EXECUTE
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.reflect.*

object CommandInterpretation : KoinComponent {

    private val allCommandSamples by inject<AllCommandSamples>()

    fun getCommandPacket(message: String) : CommandPacket?{

        val listOfWords = splitStringToListWithoutBlanks(message)  ?: return null
        val commandName = listOfWords.removeFirst()
        val commandSample = allCommandSamples.samples
                                                                    .filter { it.equals(commandName) }
                                                                    .let { if(it.isNotEmpty()) it[0] else return null }

        val commandPacket = when(commandSample.type) {
            CommandType.ARGUMENT -> getArgumentCommandPacket(commandSample, listOfWords)
            CommandType.OBJECT -> TODO()
            CommandType.NON_ARGUMENT -> getNonArgumentCommandPacket(commandSample, listOfWords)
            CommandType.FILE -> TODO()
        }

        return commandPacket



    }

    private fun splitStringToListWithoutBlanks(message: String): MutableList<String>? {

        message.ifEmpty { null }
        val listOfWords = message.split(" ").toMutableList()
        listOfWords.removeAll { it.isBlank() }
        return listOfWords.ifEmpty { null }
    }


    private fun getNonArgumentCommandPacket(commandSample: CommandSample, listOfWords: MutableList<String>) : CommandPacket?{
        return if(listOfWords.isEmpty()) CommandPacket(COMMAND_EXECUTE, commandSample.name) else null
    }

    private fun getArgumentCommandPacket(commandSample: CommandSample, possibleArgs: MutableList<String>) : CommandPacket? {
        val commandArgs = getListWithCastedArgs(commandSample.typeOfArgs!!, possibleArgs)

        return if(possibleArgs.isEmpty()) CommandPacket(COMMAND_EXECUTE, commandSample.name, commandArgs) else null

    }

    private fun getObjectCommandPacket(commandSample: CommandSample, possibleArgs: MutableList<String>) : CommandPacket? {
       return null

    }





    private fun getListWithCastedArgs(typeOfArgs: List<KClass<out Any>>, possibleArgs: MutableList<String>) : MutableList<Any>?{
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