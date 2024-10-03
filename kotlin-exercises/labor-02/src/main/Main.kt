package main

import java.util.*
import kotlin.Comparator

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

    // 3. Feladat

        val random = Random()
        val validDates = mutableListOf<Date>()
        var invalidCount = 0

        while (validDates.size < 10) {
            // Generate random dates
            val randomDate = Date(
                year = random.nextInt(3000 - 1000) + 1000,  // Year range: 1000 to 2999
                month = random.nextInt(12) + 1,  // Month range: 1 to 12
                day = random.nextInt(31) + 1  // Day range: 1 to 31 (we'll validate later)
            )

            // Check validity
            if (randomDate.isValid()) {
                validDates.add(randomDate)
            } else {
                println("Invalid date: $randomDate")
                invalidCount++
            }
        }

        // Print the list of valid dates
        println("\nValid Dates:")
        validDates.forEach { println(it) }

        // Sort the list (natural order by year, month, day)
        validDates.sort()
        println("\nSorted Dates:")
        validDates.forEach { println(it) }

        // Reverse the sorted list
        validDates.reverse()
        println("\nReversed Dates:")
        validDates.forEach { println(it) }

        // Sort the list by using custom ordering (sort by month, then day, then year)
        validDates.sortWith(Comparator { d1, d2 ->
            compareValuesBy(d1, d2, Date::month, Date::day, Date::year)
        })
        println("\nCustom Sorted Dates (by month, day, year):")
        validDates.forEach { println(it) }

}

// Extension function
fun String.nameMonogram(): String {
    return this.split("").map { it[0] }.joinToString("")
}

fun List<String>.joinElements(separator: String): String = this.joinToString(separator)

fun List<String>.getLogenstElement(): String = this.maxByOrNull { it.length }.toString()