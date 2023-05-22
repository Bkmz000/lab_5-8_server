package server.command.execute

import kotlin.reflect.KClass

class AllCommands {


    val commands = HashMap<String,KClass<out ClientCommand>>(25, 0.75F)


    init {
        commands["insert"] =  Insert::class
        commands["show"] = Show::class
        commands["help"] = Help::class
        commands["info"] = Info::class
        commands["update"] = Update::class
        commands["remove"] = Remove::class
        commands["clear"] = Clear::class
        commands["exit"] = Exit::class
        commands["replace_if_greater"] = ReplaceIfGreater::class
        commands["remove_greater_key"] = RemoveGreaterKey::class
        commands["max_by_coordinates"] = MaxByCoordinates::class
        commands["filter_starts_with_name"] = FilterStartsWithName::class
        commands["filter_greater_than_manufacturer"] = FilterGreaterThanManufacturer::class
        commands["save"] = Save::class
        commands["execute_script"] = ExecuteScript::class
        commands["history"] = History::class

    }


}