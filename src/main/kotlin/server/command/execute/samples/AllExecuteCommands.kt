package server.command.execute.samples

import server.command.execute.*
import server.command.execute.samples.ExecuteType.*
import kotlin.reflect.KClass

object AllExecuteCommands {

    val samples = mutableListOf<ExecuteSample>()
    val classesWithNames = HashMap<String, KClass<out ClientCommand>>(25, 0.75F)

    init {
        addExecuteCommand(ExecuteSample("insert", OBJECT,listOf(Int::class)), Insert::class)
        addExecuteCommand(ExecuteSample("remove", ARGUMENT, listOf(Int::class)), Remove::class)
        addExecuteCommand(ExecuteSample("show", NON_ARGUMENT), Show::class)
        addExecuteCommand(ExecuteSample("execute_script", SCRIPT, listOf(String::class)), ExecuteScript::class)
    }

    private fun addExecuteCommand(executeSample: ExecuteSample, commandClass : KClass<out ClientCommand>){
        val commandName = executeSample.name

        addClass(commandName, commandClass)
        addSample(executeSample)

    }

    private fun addSample(executeSample: ExecuteSample) {
        if(executeSample.type != NON_ARGUMENT && executeSample.typeOfArgs == null) return
        samples.add(executeSample)
    }

    private fun addClass(commandName : String, commandClass: KClass<out ClientCommand>){
        classesWithNames[commandName] = commandClass
    }

}

//            commands["insert"] =  Insert::class
//            commands["show"] = Show::class
//            commands["help"] = Help::class
//            commands["info"] = Info::class
//            commands["update"] = Update::class
//            commands["remove"] = Remove::class
//            commands["clear"] = Clear::class
//            commands["exit"] = Exit::class
//            commands["replace_if_greater"] = ReplaceIfGreater::class
//            commands["remove_greater_key"] = RemoveGreaterKey::class
//            commands["max_by_coordinates"] = MaxByCoordinates::class
//            commands["filter_starts_with_name"] = FilterStartsWithName::class
//            commands["filter_greater_than_manufacturer"] = FilterGreaterThanManufacturer::class
//            commands["save"] = Save::class
//            commands["execute_script"] = ExecuteScript::class
//            commands["history"] = History::class