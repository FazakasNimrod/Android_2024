package main

fun main() {
    // 1. Feladat: Összeadás és String template használata -----------------------------

    val a = 2
    val b = 3
    val result = a + b
    println("1. Feladat: Összeadás")
    println("$a + $b = $result")
    println("-----------------------------------------------------------\n")

    // 2. Feladat: Napok listájának kezelése ------------------------------------------

    // Immutable list of days of the week
    val daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    // Print all days using a for loop
    println("2. Feladat: Napok listája")
    println("All days of the week:")
    for (day in daysOfWeek) {
        println(day)
    }

    // Print the days that start with 'T'
    val daysStartingWithT = daysOfWeek.filter { it.startsWith('T') }
    println("\nDays starting with 'T': ${daysStartingWithT.joinToString(", ")}")

    // Print the days that contain the letter 'e'
    val daysContainingE = daysOfWeek.filter { it.contains('e') }
    println("Days containing 'e': ${daysContainingE.joinToString(", ")}")

    // Print the days that have length 6
    val daysWithLengthSix = daysOfWeek.filter { it.length == 6 }
    println("Days with length 6: ${daysWithLengthSix.joinToString(", ")}")
    println("-----------------------------------------------------------\n")

    // 3. Feladat: Prímszámok kiíratása adott tartományban -----------------------------

    val start = 1
    val end = 50
    println("3. Feladat: Prímszámok $start és $end között")
    val primes = (start..end).filter { isPrime(it) }
    println("Prime numbers: ${primes.joinToString(", ")}")
    println("-----------------------------------------------------------\n")

    // 5. Feladat: Páros számok szűrése és kiíratása listából -------------------------

    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println("5. Feladat: Páros számok kiíratása")
    printEvenNumbers(numbers)
    println("-----------------------------------------------------------\n")

    // 4. Feladat: kódolás és dekódolás

    val message = "Hello World!"

    println("4. Feladat: Kódolás és dekódolás")

    // Test encoding
    val encodedMessage = messageCoding(message, ::encode)
    println("Encoded message: $encodedMessage")

    // Test decoding
    val decodedMessage = messageCoding(encodedMessage, ::decode)
    println("Decoded message: $decodedMessage")
    println("-----------------------------------------------------------\n")
}

// Function to check if a number is prime
fun isPrime(num: Int): Boolean {
    if (num < 2) return false
    for (i in 2..num / 2) {
        if (num % i == 0) {
            return false
        }
    }
    return true
}

// Compact function to print even numbers from a list
fun printEvenNumbers(numbers: List<Int>) = numbers.filter { it % 2 == 0 }.forEach { println(it) }

// Encode function using a Caesar cipher (shift characters by 3)
fun encode(msg: String): String = msg.map {
    if (it.isLetter()) shiftChar(it, 3) else it
}.joinToString("")

// Decode function (reverse the Caesar cipher shift)
fun decode(msg: String): String = msg.map {
    if (it.isLetter()) shiftChar(it, -3) else it
}.joinToString("")

// Helper function to shift characters by a given number of positions
fun shiftChar(c: Char, shift: Int): Char {
    val base = if (c.isUpperCase()) 'A' else 'a'
    return ((c - base + shift + 26) % 26 + base.toInt()).toChar()
}

// Higher-order function to encode or decode a message
fun messageCoding(msg: String, func: (String) -> String): String {
    return func(msg)
}