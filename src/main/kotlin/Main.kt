
import app.App
import app.allModules
import com.google.gson.Gson
import org.koin.core.context.startKoin
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject

import product.Coordinates


fun main(args: Array<String>) {
    startKoin {
        modules(allModules)
    }



    val app by inject<App>(App::class.java)
    app.start()


}