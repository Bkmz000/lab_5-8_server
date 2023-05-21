package server
import server.modules.allModules
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject


fun main() {
    startKoin {
        modules(allModules)
    }



    val app by inject<StartApp>(StartApp::class.java)
    app.start()


}