package pe064

import kotlin.math.floor
import kotlin.math.sqrt

// https://projecteuler.net/problem=64
fun main() {
    println(getPeriodLength(23))

    (1..10_000)
        .map { getPeriodLength(it) }
        .filter { odd(it) }
        .count()
        .let { println(it) }
}

val odd = { i: Int -> i % 2 == 1 }

// get period length of continued fraction of sqrt(n)
fun getPeriodLength(n: Int): Int {
    val m0 = 0
    val d0 = 1

    val a0 = sqrt(n.toDouble()).toInt()
    if (a0 * a0 == n) return 0 // n was a square

    var current = arrayOf(m0, d0, a0)
    val steps: MutableSet<String> = HashSet()
    while (true) {
        if (!steps.add(current.contentToString())) break
        current = next(n, current)
    }
    return steps.size - 1
}

// see wiki:
// https://en.wikipedia.org/wiki/Methods_of_computing_square_roots#Continued_fraction_expansion
fun next(n: Int, mda: Array<Int>): Array<Int> {
    val m = mda[0]
    val d = mda[1]
    val a = mda[2]

    val mN = d * a - m
    val dN = (n - mN * mN) / d
    val aN = floor((sqrt(n.toDouble()) + mN) / dN).toInt()

    val res = arrayOf(mN, dN, aN)
    //System.err.println(Arrays.toString(mda) + "->" + Arrays.toString(res))
    return res
}

