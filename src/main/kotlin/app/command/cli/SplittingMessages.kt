package app.command.cli

object SplittingMessages {

    fun split(message: String): MutableList<String>? {

        if (message.isEmpty())  return null

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

