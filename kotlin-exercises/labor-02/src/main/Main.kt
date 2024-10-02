package main

fun main(){
    // 1. Feladat

    //val dict: IDictionary = ListDictionary()
    val dict: IDictionary = DictionaryProvider.createDictionary(DictionaryType.HASH_SET)
    println("Number of words: ${dict.size()}")
    var word: String?
    while(true){
        print("What to find? ")
        word = readLine()
        if( word.equals("quit")){
            break
        }
        println("Result: ${word?.let { dict.find(it) }}")
    }

    // 2. Feladat

    // 2.1
    val name = "John Smith"
    println(name.nameMonogram())

    // 2.2
    val list = listOf("apple", "pear", "melon", "strawberry")
    println(list.joinElements("#"))

    //2.3
    println(list.getLogenstElement())
}

// Extension function
fun String.nameMonogram(): String {
    return this.split("").map { it[0] }.joinToString("")
}

fun List<String>.joinElements(separator: String): String = this.joinToString(separator)

fun List<String>.getLogenstElement(): String = this.maxByOrNull { it.length }.toString()