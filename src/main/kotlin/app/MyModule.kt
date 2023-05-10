package app

import app.collection.ProductCollection
import app.command.AllCommandNames
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val commandModule = module {
    singleOf(::AllCommandNames)
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
