package app.command.cli

object CommandReaderCLI {

    fun readCommand(): MutableList<String>? {

        val message = readln().ifEmpty { return null }

        val listOfWords = message.split(" ") as MutableList


            val iterator = listOfWords.listIterator()
            while (iterator.hasNext()) {
                if (iterator.next().isBlank()) {
                    iterator.remove()
                }
            }

        return listOfWords.ifEmpty { null }
    }

}

