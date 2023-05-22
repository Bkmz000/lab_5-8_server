package server.command.execute

import server.product.Product

abstract class ObjectCommand : ClientCommand() {

    open lateinit var product: Product

}