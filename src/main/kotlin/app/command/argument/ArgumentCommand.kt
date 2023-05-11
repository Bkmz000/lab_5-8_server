package app.command.argument

import app.collection.ProductCollection
import app.command.ClientCommand
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class ArgumentCommand: ClientCommand, KoinComponent {
    constructor(arg: Any){

    }



    abstract val arg: Any
    abstract override fun execute(): String?
}