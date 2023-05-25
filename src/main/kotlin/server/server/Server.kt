package server.server

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import server.command.packets.RequestPacket
import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel

class Server(private val address : InetSocketAddress ) {


    fun start(){

        val channel = DatagramChannel.open()
                            channel.bind(address)
                            channel.configureBlocking(false)

        val buffer = ByteBuffer.allocateDirect(1024)
        println("Server started...")


        while (true){

            val clientAddress = channel.receive(buffer)
            val messageFromClient = extractMessage(buffer)
            if(clientAddress != null){
                val packet = messageFromClient.tryDecodeToRequestPacket()?.let {
                    println("$clientAddress -  ${it.args}")
                } ?: println("Problem with file")
            }

            buffer.clear()
        }
    }

    private fun extractMessage(buffer: ByteBuffer): String {
        buffer.flip()
        val bytes = ByteArray(buffer.remaining())
        buffer[bytes]
        return String(bytes)
    }

    private fun String.tryDecodeToRequestPacket() : RequestPacket? {
        return try {
            Json.decodeFromString<RequestPacket>(this)
        } catch (e: Exception){
            null
        }
    }

}


