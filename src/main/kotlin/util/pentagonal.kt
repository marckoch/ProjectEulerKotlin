package util

import kotlin.math.floor
import kotlin.math.sqrt

val pentagonalNumbers = generateSequence(1L) { it + 1 }.map { it * (3 * it - 1) / 2 }

fun main() {
    // [1, 5, 12, 22, 35, 51, 70, 92, 117, 145]
    pentagonalNumbers.take(10).toList().let { println(it) }

    println(isPentagonal(11))
    println(isPentagonal(12))
    println(isPentagonal(13))
}

fun isPentagonal(l: Long): Boolean {
    val r = (1 + sqrt(24.0 * l + 1.0)) / 6.0
    return r - floor(r) == 0.0
}