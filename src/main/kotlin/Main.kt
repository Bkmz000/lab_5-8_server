
import app.App
import modules.allModules
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject


fun main(args: Array<String>) {
    startKoin {
        modules(allModules)
    }



    val app by inject<App>(App::class.java)
    app.start()


}