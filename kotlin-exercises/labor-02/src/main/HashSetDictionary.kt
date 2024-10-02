package main

import java.io.File
import java.util.*

object HashSetDictionary : IDictionary {
    private var words = HashSet<String>()

    init {
        File(IDictionary.DICTIONARY_NAME).forEachLine { add(it) }
    }

    override fun add(word: String): Boolean {
        return words.add(word)
    }

    override fun find(word: String): Boolean {
        return words.find { it == word } != null
    }

    override fun size(): Int {
        return words.size
    }
}
