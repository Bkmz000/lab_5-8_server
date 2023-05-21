package server.command.`object`

import server.product.Product

abstract class ProductBuilder {
    abstract fun build(args: Any? = null): Product?

}