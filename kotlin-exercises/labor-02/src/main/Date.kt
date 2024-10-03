package main

import java.time.LocalDate
import java.util.*

data class Date(var year: Int = LocalDate.now().year,
                var month: Int = LocalDate.now().monthValue,
                var day: Int = LocalDate.now().dayOfMonth) : Comparable<Date> {

    // Implementing the natural ordering of Date by year, then month, then day
    override fun compareTo(other: Date): Int {
        return compareValuesBy(this, other, Date::year, Date::month, Date::day)
    }
}

fun Date.isLeapYear() = (this.year % 4 == 0 && this.year % 100 != 0) || (this.year % 400 == 0)

fun Date.isValid(): Boolean {
    return try {
        LocalDate.of(this.year, this.month, this.day)
        true
    } catch (e: Exception) {
        false
    }
}