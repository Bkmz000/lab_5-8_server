package app.command.`object`.file

import org.koin.core.component.KoinComponent
import java.io.File
import java.io.FileNotFoundException

object FileCollection : KoinComponent {

    const val fileName = "C:/itmo/labs/kotlin/lab_5/src/main/kotlin/sample.json"

    fun getFile() : File? {
        return try {
            return File(fileName)
        } catch (e: FileNotFoundException) {
            null
        }
    }
}