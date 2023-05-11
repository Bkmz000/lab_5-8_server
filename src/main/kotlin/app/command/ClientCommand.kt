package app.command

import app.collection.ProductCollection
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class ClientCommand : KoinComponent {



    val productCollection by inject<ProductCollection>()
    abstract fun execute(): String?



}