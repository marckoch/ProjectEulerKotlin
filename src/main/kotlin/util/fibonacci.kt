package util

import java.math.BigInteger

// get fibonacci numbers as sequence
// 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, ...
// https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/partition.html
fun fibonacci(): Sequence<Long> {
    return generateSequence(Pair(0L, 1L)) { Pair(it.second, it.first + it.second) }
        .map { it.first }
}

fun fibonacciAsBigIntegers(): Sequence<BigInteger> {
    return generateSequence(Pair(BigInteger.ZERO, BigInteger.ONE)) { Pair(it.second, it.first + it.second) }
        .map { it.first }
}

fun main() {
    fibonacci()
        .take(20)
        .toList()
        .let { println(it) }
}