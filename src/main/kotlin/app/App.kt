package app

import app.command.ClientCommand
import app.command.argument.Insert
import app.command.cli.CommandInterpretation
import app.command.cli.CommandReaderCLI
import org.koin.core.component.KoinComponent


class App : KoinComponent {
   fun start() {

       repeat(20) {
           val a = CommandReaderCLI.readCommand()
           if (a != null) {
               val intCom = CommandInterpretation.interpretation(a)
               if (intCom != null) {
                   if (intCom.second != null) {
                       if(intCom.second!!.size == 1)
                       {
                           val b = intCom.first.call(intCom.second!![0]) as ClientCommand
                           b.execute()
                       } else if(intCom.second!!.size == 2){
                           val b = intCom.first.call(intCom.second!![0], intCom.second!![1]) as ClientCommand
                           b.execute()
                       }

                   } else {
                       val b = intCom.first.call() as ClientCommand
                       b.execute()
                   }


               }
           }
       }


//       val gg = Coordinates(1,2.0)
//       val json = Json {prettyPrint = true}
//       val f1 = json.encodeToString(gg)
//       println(f1)
//       val f2 = json.decodeFromString<Coordinates>(f1)
//       println(f2)


//       val f1 = CommandReaderCLI.readCommand()
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



