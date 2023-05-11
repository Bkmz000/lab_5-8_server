package app.command.nonargument

import app.collection.ProductCollection
import app.command.ClientCommand
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Info : ClientCommand(), KoinComponent {


    private val collectionType = productCollection.products::class.toString()
    private val creationDate = productCollection.creationDate
    private val numberOfElements = productCollection.products.size

    override fun execute(): String? {

        println(this.toString())
        return "Success"


    }

    override fun toString(): String {
        return "CollectionType = '${collectionType.substringAfter("util.")}', CreationDate = '$creationDate', Number of elements = $numberOfElements"
    }


}