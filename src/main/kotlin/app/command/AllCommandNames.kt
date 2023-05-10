package app.command

import app.command.argument.InsertProduct
import app.command.nonargument.Help
import app.command.nonargument.Show
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

class AllCommandNames {


    val commands = HashMap<String,KClass<*>>()


    init {
        commands["insert"] =  InsertProduct::class
        commands["show"] = Show::class
        commands["help"] = Help::class
    }


}