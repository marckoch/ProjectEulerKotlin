package util

import kotlin.math.floor
import kotlin.math.sqrt

val triangleNumbers = generateSequence(1) { it + 1 }.map { it * (it + 1) / 2 }

// https://en.wikipedia.org/wiki/Triangular_number#Triangular_roots_and_tests_for_triangular_numbers
fun isTriangular(n: Int): Boolean {
    return isSquare(8 * n + 1)
}

fun isSquare(n: Int): Boolean {
    val s = sqrt(n.toDouble())
    val fl = floor(s)
    return fl == s
}

fun main() {
    // [1, 3, 6, 10, 15, 21, 28, 36, 45, 55, 66, 78, 91, 105, 120, 136, 153, 171, 190, 210]
    triangleNumbers
        .take(20)
        .toList()
        .let { println(it) }

    (1..20)
        .withIndex()
        .map { indexedValue -> Pair(indexedValue.index + 1 , isTriangular(indexedValue.value)) }
        .let { println(it) }
}

