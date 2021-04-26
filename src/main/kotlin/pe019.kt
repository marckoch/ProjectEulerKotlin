package pe019

import java.time.DayOfWeek
import java.time.LocalDate

// https://projecteuler.net/problem=19
fun main() {
    val from = LocalDate.of(1901, 1, 1)
    val to = LocalDate.of(2000, 12, 31)

    generateSequence(from) { it.plusDays(1) }
        .takeWhile { it.isBefore(to) }
        .filter { it.dayOfMonth == 1 && it.dayOfWeek == DayOfWeek.SUNDAY }
        .count()
        .let { println(it) }
}