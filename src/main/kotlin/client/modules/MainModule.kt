import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import client.StartApp
import client.command.AllCommandSamples

val commandModule = module {
    singleOf(::AllCommandSamples)
//    factoryOf(server.command.`object`::ProductBuilderCLI)
//    singleOf(server.command.invoke::CommandInvoker)
}


val collectionModule = module {
//    singleOf(server.collection::ProductCollection)
}

val appModule = module {
    singleOf(::StartApp)
}


val allModules = module {
    includes(collectionModule, appModule, commandModule)
}