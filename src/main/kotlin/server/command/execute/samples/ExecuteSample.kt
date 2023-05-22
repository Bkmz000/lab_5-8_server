package server.command.execute.samples

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

@Serializable
data class ExecuteSample(
    val name: String,
    val type: ExecuteType,
    val typeOfArgs: List<KClass<out @Contextual Any>>? = null){


    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        return name == other
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}