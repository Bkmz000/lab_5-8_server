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

    fun addProduct(index: Int, product: Product) : String{
        return if (products.containsKey(index)){
            "Index already exists"
        }
        else {
            products[index] = product
            "Success"
        }
    }

    fun showProducts(): kotlinx.serialization.json.JsonElement {
        return Json.encodeToJsonElement(this.products.toString())
    }

    fun replaceProduct(index: Int, product: Product) : String{
        return if (products.containsKey(index)){
            products.replace(index,product)
            "Success"
        } else {
            "No such element"
        }
    }

    fun removeProduct(index: Int) : String{
        return if(products.containsKey(index)){
            products.remove(index)
            "Success"
        } else {
            "No such element"
        }
    }

    fun clear() : String{
        products.clear()
        return "Success"
    }




}