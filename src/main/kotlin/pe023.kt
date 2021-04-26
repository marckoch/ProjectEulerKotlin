package pe023

import util.divisors.getProperDivisorsOf
import util.memoize.memoize

// https://projecteuler.net/problem=23
fun main() {
    // see problem statement: all numbers above 28123 can surely be written as sum of two abundant numbers
    val limit = 28123L

    (1L..limit)
        .filter { !isSumOfTwoAbundantNumbers(it) }
        .sum()
        .let { println(it) }
}

fun isSumOfTwoAbundantNumbers(n: Long): Boolean {
    return (1..n / 2)
        .any { isAbundantMemoized(it) && isAbundantMemoized(n - it) }
}

fun isAbundant(n: Long): Boolean {
    return getProperDivisorsOf(n).sum() > n
}

// isAbundant will be called again and again for small numbers, ideal candidate for memoization!
val isAbundantMemoized = { n: Long -> isAbundant(n) }.memoize()