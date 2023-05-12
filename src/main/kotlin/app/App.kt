package app

import app.command.execute.ClientCommand
import app.command.cli.CommandInterpretation
import app.command.cli.SplittingMessages
import app.command.`object`.file.LoadCollection
import kotlinx.serialization.json.*
import org.koin.core.component.KoinComponent


class App : KoinComponent {



   fun start() {
       val r = LoadCollection().load()

       repeat(20) {

            val json = Json { prettyPrint = true }


           val a = SplittingMessages.split(readln())
           if (a != null) {
               val intCom = CommandInterpretation.interpretation(a)
               if (intCom != null) {
                   if (intCom.second != null) {
                       if(intCom.second!!.size == 1)
                       {
                           val b = intCom.first.call(intCom.second!![0]) as ClientCommand
                           println(json.decodeFromJsonElement(b.execute()) as String)
                       } else if(intCom.second!!.size == 2){
                           val b = intCom.first.call(intCom.second!![0], intCom.second!![1]) as ClientCommand
                           println(json.decodeFromJsonElement(b.execute()) as String)
                       }

                   } else {
                       val b = intCom.first.call() as ClientCommand
                       println(json.decodeFromJsonElement(b.execute()) as String)
                   }


               } else {
                   println("Unknown command")
               }
           }
       }


//       val gg = Coordinates(1,2.0)
//       val json = Json {prettyPrint = true}
//       val f1 = json.encodeToString(gg)
//       println(f1)
//       val f2 = json.decodeFromString<Coordinates>(f1)
//       println(f2)


//       val f1 = SplittingMessages.readCommand()
//       val json = Json { this.prettyPrint = true}
//       if (f1 != null){
//           val f2 = json.encodeToJsonElement(f1)
//           println(Json.decodeFromJsonElement<MutableList<String>>(f2))
//       }
//    val a = Insert::class
//       val b = a.primaryConstructor
//       println(b)

       }
   }



