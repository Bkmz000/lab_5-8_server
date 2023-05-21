package server.modules

import server.StartApp
import server.collection.ProductCollection
import server.command.execute.AllCommands
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import server.command.`object`.ProductBuilderCLI
import server.command.invoke.CommandInvoker

val commandModule = module {
    singleOf(::AllCommands)
    factoryOf(::ProductBuilderCLI)
    singleOf(::CommandInvoker)
}


val collectionModule = module {
    singleOf(::ProductCollection)
}

val appModule = module {
    singleOf(::StartApp)
}


val allModules = module {
    includes(collectionModule, appModule, commandModule)
}
