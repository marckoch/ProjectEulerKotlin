package util.continueFraction

import kotlin.math.floor
import kotlin.math.sqrt

fun main() {
    convertToContinuedFraction(7).let { println(it) }

    getPeriodLength(7).let { println(it) }
}

// sqrt(7) -> [2; 1, 1, 1, 4]
// continuedFraction is represented as Pair<Int, List<Int>>
// pair.first is the "2" above
// pair.second (List) is a list of (1, 1, 1, 4)
fun convertToContinuedFraction(n: Int): Pair<Int, List<Int>> {
    val m0 = 0
    val d0 = 1
    val a0 = sqrt(n.toDouble()).toInt()

    if (a0 * a0 == n) return Pair(0, emptyList()) // n was a square

    var current = Triple(m0, d0, a0)
    val steps: MutableList<Triple<Int, Int, Int>> = ArrayList()

    do {
        steps.add(current)
        current = next(n, current)
    } while (!steps.contains(current))

    steps.removeAt(0)

    return Pair(a0, steps.map { ints -> ints.third })
}

// get period length of continued fraction of sqrt(n)
fun getPeriodLength(n: Int): Int {
    return convertToContinuedFraction(n).second.size
}

// see wiki:
// https://en.wikipedia.org/wiki/Methods_of_computing_square_roots#Continued_fraction_expansion
// https://en.wikipedia.org/wiki/Periodic_continued_fraction#Canonical_form_and_repetend
fun next(n: Int, mda: Triple<Int, Int, Int>): Triple<Int, Int, Int> {
    val m = mda.first
    val d = mda.second
    val a = mda.third

    val mN = d * a - m
    val dN = (n - mN * mN) / d
    val aN = floor((sqrt(n.toDouble()) + mN) / dN).toInt()

    val mdaN = Triple(mN, dN, aN)
    // System.err.println("$mda -> $mdaN")
    return mdaN
}

