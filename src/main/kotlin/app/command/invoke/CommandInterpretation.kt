package app.command.invoke

import app.command.execute.AllCommands
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.reflect.*

object CommandInterpretation : KoinComponent {

    private val allCommands by inject<AllCommands>()

    fun getPairOfCommandAndArgs(message: String): Pair<KFunction<Any>,ArrayList<Any>?>?{

        val listOfWords = split(message) ?: return null
        val commandName = listOfWords.removeFirst()

        if (allCommands.commands.containsKey(commandName)){
            val commandConstructor = createInstanceOfCommandConstructor(commandName)
            val typesOfCommandArgs = getTypeOfArgConstructor(commandConstructor)

            return when(getTypeOfCommandByCheckingValidOfArgs(typesOfCommandArgs,listOfWords)){
                TypeOfCommand.ARGUMENT -> {
                    getPairOfArgumentCommandAndArgs(commandConstructor = commandConstructor,typeOfArgs = typesOfCommandArgs!!, possibleArgs =  listOfWords)
                }

                TypeOfCommand.NON_ARGUMENT -> {
                    getPairOfNONArgumentCommandAndNull(commandConstructor = commandConstructor)
                }

                TypeOfCommand.NOT_A_COMMAND -> {
                    null
                }
            }
        }// if
        return null
    }

    private fun split(message: String): MutableList<String>? {

        if (message.isEmpty())  return null
        val listOfWords = message.split(" ").toMutableList()
        val iterator = listOfWords.listIterator()
        while (iterator.hasNext()) {
            if (iterator.next().isBlank()) {
                iterator.remove()
            }
        }

        return listOfWords.ifEmpty { null }
    }


    private fun createInstanceOfCommandConstructor(commandName : String): KFunction<Any>{
        try {
            val command = allCommands.commands[commandName]
            val constructors = command?.constructors as MutableList<KFunction<Any>>
            return constructors[0]
        } catch (e: ClassNotFoundException) {
            throw Error("There is no this command in AllCommands")
        }
    }
    private fun getTypeOfArgConstructor(constructor:  KFunction<Any>?): List<KParameter>?{
        val parameters = constructor?.parameters
        return parameters?.ifEmpty {
            null
        }
    }
    private fun getTypeOfCommandByCheckingValidOfArgs(typeOfArgs:  List<KParameter>?, listOfWords: MutableList<String>) : TypeOfCommand{
        if(typeOfArgs == null && listOfWords.isEmpty()){
            return TypeOfCommand.NON_ARGUMENT
        } else
            if (typeOfArgs != null && (listOfWords.size == typeOfArgs.size) ) {
                return TypeOfCommand.ARGUMENT
            }
        return TypeOfCommand.NOT_A_COMMAND
    }
    private enum class TypeOfCommand{
        ARGUMENT,
        NON_ARGUMENT,
        NOT_A_COMMAND
    }


    private fun getPairOfNONArgumentCommandAndNull(commandConstructor: KFunction<Any>) : Pair<KFunction<Any>,ArrayList<Any>?> {
            return Pair(commandConstructor,null)
    }
    private fun getPairOfArgumentCommandAndArgs(commandConstructor: KFunction<Any>, typeOfArgs: List<KParameter>, possibleArgs : MutableList<String>) : Pair<KFunction<Any>,ArrayList<Any>>? {

        val args = ArrayList<Any>()

        for(i in 0 until possibleArgs.size){
                when(val argument = possibleArgs[i].tryCastTo(typeOfArgs[i])){
                    is Outcome.Success -> args.add(argument.value)
                    is Outcome.Error -> return null
                }
            }

        return Pair(commandConstructor,args)
        }

    @OptIn(ExperimentalStdlibApi::class)
    private fun String.tryCastTo(type: KParameter) : Outcome<Any> = try {
            val castedArg: Any
            when (type.type.javaType) {
                (Int::class.java) -> {
                    castedArg = this.toInt()
                    Outcome.Success(castedArg)
                }
                (Double::class.java) -> {
                    castedArg = this.toDouble()
                    Outcome.Success(castedArg)
                } (String::class.java) -> {
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