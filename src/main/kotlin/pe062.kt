package pe062

import java.math.BigInteger

// https://projecteuler.net/problem=62
fun main() {
    val limit = 10_000L
    val permCount = 5

    (1L until limit)
        .map { cube(it) }
        //.also { println(it) }
        .groupBy { digitsSorted(it) }
        //.also { println(it) }
        .filterValues { it.size == permCount }
        //.also { println(it) }
        .minOf { it.value.minOrNull()!! }
        .let { println(it) }
}

val cube = { l: Long -> BigInteger.valueOf(l).pow(3) }

private fun digitsSorted(bi: BigInteger): String {
    return bi.toString().toCharArray().sorted().joinToString("")
}
