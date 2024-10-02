package main;

interface IDictionary {

    fun add(word: String): Boolean
    fun find(word: String): Boolean
    fun size(): Int

    companion object {
        const val DICTIONARY_NAME = "C:\\SAPIENTIA EMTE\\Negyedik ev\\ANDROID\\Android_2024-main\\kotlin-exercises\\labor-02\\src\\resources\\dict"
    }
}