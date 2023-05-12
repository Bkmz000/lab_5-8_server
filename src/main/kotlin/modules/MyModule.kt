package modules

import app.App
import app.collection.ProductCollection
import app.command.execute.AllCommands
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import app.command.`object`.ProductBuilderCLI

val commandModule = module {
    singleOf(::AllCommands)
    factoryOf(::ProductBuilderCLI)
}


val collectionModule = module {
    singleOf(::ProductCollection)
}

val appModule = module {
    singleOf(::App)
}


val allModules = module {
    includes(collectionModule, appModule, commandModule)
}
