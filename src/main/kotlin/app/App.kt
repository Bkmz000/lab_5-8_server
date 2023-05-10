package app

import app.collection.ProductCollection
import app.command.ClientCommand
import app.command.argument.InsertProduct
import app.command.cli.CommandInterpretation
import app.command.cli.CommandReaderCLI
import app.command.nonargument.Help
import com.google.gson.Gson
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import org.koin.core.component.KoinComponent
import org.koin.core.component.getScopeId
import org.koin.core.component.inject
import product.Coordinates
import kotlin.reflect.KFunction
import kotlin.reflect.KType
import kotlin.reflect.full.*


class App : KoinComponent {
   fun start(){
       repeat(10) {
//            val prop = InsertProduct::class.memberProperties.find { it.name == "name" }
//           println(String::class == prop::class)
//            val f1 = Help::class
//           val c = f1.companionObject?.memberProperties?.first()?.getter?.call(f1.companionObjectInstance)
//           val d = f1.memberProperties.find { it.name == "info" }?.returnType
//           println(d)
//           println(String::class)



           val a = CommandReaderCLI.readCommand()
           if (a != null) {
               val intCom = CommandInterpretation.interpretation(a)
               if (intCom != null) {
                   if (intCom.second != null) {
                       val b = intCom.first.call(intCom.second) as ClientCommand
                       b.execute()
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
//    val a = InsertProduct::class
//       val b = a.primaryConstructor
//       println(b)



   }
   }
