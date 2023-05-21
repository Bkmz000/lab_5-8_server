package server.command.execute.`object`

import server.command.execute.ClientCommand
import server.product.Product

abstract class ObjectCommand : ClientCommand() {
    abstract fun setProductBuilder(product: Product)
}