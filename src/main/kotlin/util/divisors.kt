package util

import kotlin.math.sqrt

// much nicer, pure functional solution, but might be slow for big n
private fun getDiv(n: Long): Set<Long> {
    return (1..n)
        .filter { n % it == 0L }
        .toSet()
}

// so we try this one
// https://try.kotlinlang.org/#/UserProjects/ea1bspqcfu6vht1abgs61gp916/kgfioeg1m9h2369glao3trcc97
fun getDivisorsOf(n: Long): Set<Long> {
    // Factors comes in pairs, you only need to iterate to the
    // square root of n and get the paired factor using n / it.
    val limit = sqrt(n.toDouble()).toLong()
    return (1L..limit)
        .filter { n % it == 0L }
        .flatMap {
            val squaredIsN = it * it == n
            if (squaredIsN) listOf(it) else listOf(it, n / it)
        }
        .toSortedSet()
}

// n itself is excluded
// e.g. getProperDivisorsOf(220) = [1, 2, 4, 5, 10, 11, 20, 22, 44, 55, 110]
fun getProperDivisorsOf(n: Long): Set<Long> {
    return getDivisorsOf(n)
        .filter { it != n }
        .toSortedSet()
}

fun main() {
    val n = 220L
    println(getDivisorsOf(n))
    println(getProperDivisorsOf(n))
    println(getDiv(n))
}

// old school: we build the list ourselves
//private fun getDivisorsOf(n: Int): Set<Int> {
//    val divisors: MutableSet<Int> = sortedSetOf()
//
//    var d = 1
//    while (d * d <= n) {
//        if (n % d == 0) {
//            divisors.add(d)
//            divisors.add(n / d)
//        }
//        d++
//    }
//    return divisors
//}