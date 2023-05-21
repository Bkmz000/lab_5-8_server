package server.command.execute

import server.collection.ProductCollection
import server.modules.allModules
import org.junit.jupiter.api.Test

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class ClearTest : KoinComponent {

    val collection by inject<ProductCollection>()

    @Test
    fun execute() {
        startKoin {
            allModules
        }

        return if(collection.products.isEmpty()) assert(true) else assert(false)
    }
}