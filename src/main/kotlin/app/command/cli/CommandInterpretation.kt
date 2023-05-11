package app.command.cli

import app.command.AllCommands
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KType
import kotlin.reflect.full.memberProperties

object CommandInterpretation : KoinComponent {

    private val allCommands by inject<AllCommands>()


    fun interpretation(listOfWords: MutableList<String>): Pair<KFunction<Any>,Any?>?{
        val commandName = listOfWords[0]

        if (allCommands.commands.containsKey(commandName)){
            val commandConstructor = createInstanceOfCommandConstructor(commandName)
            val typeOfArg = getTypeOfArgConstructor(allCommands.commands[commandName])


            if(typeOfArg != null && listOfWords.size == 2){
               return argumentCommand(commandConstructor = commandConstructor, typeOfArg = typeOfArg, possibleArg = listOfWords[1])
            } else if(typeOfArg == null && listOfWords.size == 1){
                return nonArgumentCommand(commandConstructor = commandConstructor)
            }

        }
        return null

    }


    private fun nonArgumentCommand(commandConstructor: KFunction<Any>) : Pair<KFunction<Any>,Any?>? {

            return Pair(commandConstructor,null)

    }







    private fun argumentCommand(commandConstructor: KFunction<Any>, typeOfArg: KType, possibleArg : String) : Pair<KFunction<Any>,Any>? {

            return when(val  argument =  possibleArg.castTo(typeOfArg)){
                is Outcome.Success -> Pair(commandConstructor, argument.value)
                is Outcome.Error -> null
            }
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

    private fun String.castTo(type: KType) : Outcome<Any> = try {
            var castedArg: Any
            when (type.toString()) {
                "kotlin.Int" -> {
                    castedArg = this.toInt()
                    Outcome.Success(castedArg)
                }
                "kotlin.Double" -> {
                    castedArg = this.toDouble()
                    Outcome.Success(castedArg)
                } "kotlin.String" -> {
                    castedArg = this
                    Outcome.Success(castedArg)
                }

                else -> {Outcome.Error()}
            }

        } catch (ex: NumberFormatException){
            Outcome.Error()
        }

    private fun getTypeOfArgConstructor(constructor: KClass<*>?): KType?{
        return constructor?.memberProperties?.find { it.name == "arg" }?.returnType
    }

    sealed class Outcome<out T : Any> {
        data class Success<out T : Any>(val value: T) : Outcome<T>()
        data class Error(val message: String? = null, val cause: Exception? = null) : Outcome<Nothing>()
    }
}