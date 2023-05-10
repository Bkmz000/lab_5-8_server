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
import java.util.*
class ProductCollection {
    val products: TreeMap<Int, Product> = TreeMap()

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


}