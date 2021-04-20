package util

import java.math.BigInteger

fun factorial(n: Int) = factorial(1..n)

// range = (from..to) -> (to-from)!
fun factorial(range: IntRange): BigInteger = range
    .map { BigInteger.valueOf(it.toLong()) }
    .reduce { acc, i -> acc * i }