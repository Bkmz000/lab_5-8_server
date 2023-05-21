package server.command.`object`

import org.junit.jupiter.api.Test

class ProductBuilderScriptTest {
    val b = mutableListOf("1","2","3","4","KILOGRAMS","1","2","PUBLIC")
    @Test
    fun build() {
        val a = ProductBuilderScript().build(b)
        a?.also { assert(true) } ?: assert(false)

    }
}