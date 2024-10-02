package main

import main.IDictionary.Companion.DICTIONARY_NAME
import java.io.File
import java.util.*

object TreeSetDictionary : IDictionary {

    private var words = TreeSet<String>()

    init {
        File(DICTIONARY_NAME).forEachLine { add(it) }
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
