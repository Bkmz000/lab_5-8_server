package server.command.invoke

import server.command.execute.ClientCommand
import server.command.execute.`object`.ObjectCommand
import server.command.`object`.ProductBuilder
import server.command.`object`.ProductBuilderCLI
import kotlin.reflect.KFunction
import kotlin.reflect.full.superclasses

class CommandBuilder {

    var productBuilder: ProductBuilder = ProductBuilderCLI()

    fun getCommandOrFailureMessage(pairOfConstructAndArgs:  Pair<KFunction<Any>, ArrayList<Any>?>) : Any {

        val commandConstructor = pairOfConstructAndArgs.first
        val args = pairOfConstructAndArgs.second

        val builtCommand : ClientCommand =
            when(args?.size) {
                1 -> commandConstructor.call(args[0]) as ClientCommand
                2 -> commandConstructor.call(args[0], args[1]) as ClientCommand
            else -> commandConstructor.call() as ClientCommand
            }

        val isObjectCommand = builtCommand::class.superclasses.find { it == ObjectCommand::class }
        if(isObjectCommand != null){
            val product = productBuilder.build() ?: return "Cannot build the Product"
            val objectCommand = builtCommand as ObjectCommand
            objectCommand.setProductBuilder(product)
        }

        return builtCommand
    }
}