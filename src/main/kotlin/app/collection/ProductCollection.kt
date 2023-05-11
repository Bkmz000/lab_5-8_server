package app.collection


import com.google.gson.JsonElement
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.encodeToJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Single
import product.Product
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.swing.text.DateFormatter
import kotlin.collections.ArrayList

class ProductCollection {
    val products: TreeMap<Int, Product> = TreeMap()

    val creationDate  = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))

}