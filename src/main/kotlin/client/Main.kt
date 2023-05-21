package client

import allModules
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent
import client.StartApp

fun main(){
    startKoin {
        modules(allModules)
    }



    val app by KoinJavaComponent.inject<StartApp>(StartApp::class.java)
    app.start()


}