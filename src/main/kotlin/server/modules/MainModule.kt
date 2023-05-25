package server.modules

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import server.StartApp
import server.collection.ProductCollection
import server.command.invoke.CommandInvoker
import server.command.`object`.ProductBuilderCLI

val commandModule = module {
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
