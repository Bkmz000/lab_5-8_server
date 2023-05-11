package app

import app.collection.ProductCollection
import app.command.AllCommands
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val commandModule = module {
    singleOf(::AllCommands)
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
