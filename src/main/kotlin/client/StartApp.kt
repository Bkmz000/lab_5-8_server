package client

import client.command.processing.CommandInterpretation

class StartApp {

    fun start(){
        println("Welcome to the CLI \"Product Collection\"")
        while(true) {
            val a = readln()
            val b = CommandInterpretation.getCommandPacket(a)
            println(b)
        }
    }
}