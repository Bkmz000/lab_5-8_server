package app.command.execute

import app.command.invoke.CommandBuilder
import app.command.invoke.CommandInterpretation
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import java.io.File

class ExecuteScript : ClientCommand {

    private val fileName: String

    private val file: File?

    constructor(fileName: String) {
        this.fileName = fileName
        file = if (File(fileName).isFile) File(fileName) else null
    }

    override val name: String = "execute_script"


    //execute_script C:/itmo/labs/kotlin/lab5/script1.txt


    override fun execute(arg: Any?): JsonElement {


        if (file == null) return Json.encodeToJsonElement("File with script was not found")

        val textAsArray = file.bufferedReader().readLines() as ArrayList

        var outerIndex: Int = 0
        try {
            for (indexOfLine in 0 until textAsArray.size step 1) {
                    indexOfLine.also { outerIndex = it }
                    val command = CommandInterpretation.getPairOfCommandAndArgs(textAsArray[indexOfLine])
                    if (command != null) {

                            val resultOfCommand: JsonElement
                            println("Number of Command = $outerIndex")
                            when (command.first.returnType.classifier) {
                            Insert::class -> {
                                val listOfArgs = textAsArray.slice(indexOfLine + 1.. indexOfLine + 8) as MutableList
                                var i: Int = indexOfLine +1
                                repeat(listOfArgs.size){
                                    textAsArray.removeAt(i)
                                }

                                resultOfCommand = CommandBuilder().build(command).execute(listOfArgs)

                            }

                            Update::class -> {
                                val listOfArgs = textAsArray.slice(indexOfLine + 1.. indexOfLine + 8)
                                resultOfCommand = CommandBuilder().build(command).execute(listOfArgs)
                                var i: Int = indexOfLine +1
                                repeat(listOfArgs.size){
                                    textAsArray.removeAt(i)
                                }

                            }

                            else -> {
                                resultOfCommand = CommandBuilder().build(command).execute()
                            }
                        }
                        println(Json.decodeFromJsonElement<String>(resultOfCommand))
                        println()

                    } else {
                        println("Unknown command '${textAsArray[indexOfLine]}'")
                        return Json.encodeToJsonElement("The script was completed only up to this part")
                    }
                }
            } catch (e: IndexOutOfBoundsException) {
                return if(outerIndex != textAsArray.size){
                    Json.encodeToJsonElement("Not enough elements")
                } else
                    Json.encodeToJsonElement("The script was successfully completed")
            }
            return Json.encodeToJsonElement("The script was successfully completed")
    }
}