package util.hexagonal

import kotlin.math.floor
import kotlin.math.sqrt

val hexagonalNumbers = generateSequence(1L) { it + 1 }.map { hexagonal(it) }

val hexagonal = { i: Long ->  i * (2 * i - 1) }

fun isHexagonal(n: Long): Boolean {
    val r = (sqrt(8.0 * n + 1.0) + 1.0) / 4.0
    return r == floor(r)
}

fun main() {
    // [1, 6, 15, 28, 45, 66, 91, 120, 153, 190, 231, 276, 325, 378, 435, 496, 561, 630, 703, 780]
    hexagonalNumbers.take(20).toList().let { println(it) }

    println(isHexagonal(14))  // -> false
    println(isHexagonal(15))  // -> true
    println(isHexagonal(16))  // -> false
}