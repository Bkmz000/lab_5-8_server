package app.command.execute

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import java.io.File
import java.io.FileNotFoundException

class Execute_script : ClientCommand {

    private val fileName: String

    private val file: File?

    constructor(fileName: String) {
        this.fileName = fileName
        file =
            try {
                File(fileName)
            } catch (e: FileNotFoundException) {
                null
            }
    }

    init {

    }


    override fun execute(): JsonElement {
//        if(file == null) return Json.encodeToJsonElement("File with collection was not found")
//
//        val = scriptText
        TODO()


    }


}