package app.command.`object`.file

import org.koin.core.component.KoinComponent
import java.io.File
import java.io.FileNotFoundException

object FileCollection : KoinComponent {

    const val fileName = "C:/itmo/labs/kotlin/lab5/sample.json"

    fun getFile() : File? {
        return if (File(fileName).isFile) {
             File(fileName)
        } else
            null
    }
}