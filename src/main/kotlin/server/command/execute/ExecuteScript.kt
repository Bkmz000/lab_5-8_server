package server.command.execute

import server.command.invoke.CommandInvoker
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.File

class ExecuteScript : ClientCommand, KoinComponent {

    override val name: String = "execute_script"

    private val fileName: String
    private val fileWithScript: File?
    private val commandInvoker by inject<CommandInvoker>()


    constructor(fileName: String) : super() {
        this.fileName = fileName
        fileWithScript = if (File(fileName).isFile) File(fileName) else null
    }


    //execute_script C:/itmo/labs/kotlin/lab5/script1.txt


    override fun execute(): JsonElement {
//
//
//        if (fileWithScript == null) return Json.encodeToJsonElement("File with script was not found")
//
//        val textAsArray = fileWithScript.bufferedReader().readLines() as ArrayList<String>
//
//        var outerIndex: Int = 0
//        try {
//            for (indexOfLine in 0 until textAsArray.size step 1) {
//                    indexOfLine.also { outerIndex = it }
//                    val command = CommandInterpretation.getPairOfConstructorAndArgs(textAsArray[indexOfLine])
//                    if (command != null) {
//
//                            val resultOfCommand: JsonElement
//                            println("Number of Command = $outerIndex")
//                            when (command.first.returnType.classifier) {
//                            Insert::class -> {
                                    //elementAtOrNull использовать!!! AlementAtOrElse еще есть
//                                val listOfArgs = textAsArray.slice(indexOfLine + 1.. indexOfLine + 8)  as MutableList
//                                var i: Int = indexOfLine + 1
//                                repeat(listOfArgs.size){
//                                    textAsArray.removeAt(i)
//                                }
//
//                                resultOfCommand = CommandBuilder.build(command).execute(listOfArgs)
//
//                            }
//
//                            Update::class -> {
//                                val listOfArgs = textAsArray.slice(indexOfLine + 1.. indexOfLine + 8)
//                                resultOfCommand = CommandBuilder.build(command).execute(listOfArgs)
//                                var i: Int = indexOfLine +1
//                                repeat(listOfArgs.size){
//                                    textAsArray.removeAt(i)
//                                }
//
//                            }
//
//                            else -> {
//                                resultOfCommand = CommandBuilder.build(command).execute()
//                            }
//                        }
//                        println(Json.decodeFromJsonElement<String>(resultOfCommand))
//                        println()
//
//                    } else {
//                        println("Unknown command '${textAsArray[indexOfLine]}'")
//                        return Json.encodeToJsonElement("The script was completed only up to this part")
//                    }
//                }
//            } catch (e: IndexOutOfBoundsException) {
//                return if(outerIndex != textAsArray.size){
//                    Json.encodeToJsonElement("Not enough elements")
//                } else
//                    Json.encodeToJsonElement("The script was successfully completed")
//            }
//            return Json.encodeToJsonElement("The script was successfully completed")
//    }
//
//    private fun v2(textAsArray: ArrayList<String>) : JsonElement {
//        if (fileWithScript == null) return Json.encodeToJsonElement("File with script was not found")
//
//        val bufferedReaderFile = fileWithScript.bufferedReader()
//        val textFromFileAsList = bufferedReaderFile.readLines().toMutableList()
//
//
//
//
//
//
        return Json.encodeToJsonElement("fd")
    }

}

