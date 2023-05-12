package app.command.`object`

import app.product.Product

abstract class ProductBuilder {
    abstract fun build(args: Any? = null): Product?

}