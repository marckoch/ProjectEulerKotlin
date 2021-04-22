package util

import java.math.BigInteger

fun factorial(n: Int): BigInteger = if (n == 0) BigInteger.ONE else factorial(1..n)


// range = (from..to) -> (to-from)!
fun factorial(range: IntRange): BigInteger = range
    .map { BigInteger.valueOf(it.toLong()) }
    .reduce { acc, i -> acc * i }