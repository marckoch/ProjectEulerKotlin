package pe139

import util.gcd.gcd
import kotlin.math.abs

// https://projecteuler.net/problem=139
fun main() {
    val maxPerimeter = 100_000_000
    count(maxPerimeter).let { println(it) }
}

private fun count(maxPerimeter: Int): Long {
    // https://en.wikipedia.org/wiki/Tree_of_primitive_Pythagorean_triples#Alternative_methods_of_generating_the_tree
    var count = 0L
    var m = 1
    while (2 * m * m <= maxPerimeter) {
        for (n in 1 until m) {
            if (m % 2 == 1 && n % 2 == 1) { // not both odd!
                continue
            }
            if (gcd(m, n) > 1) {
                continue
            }

            // a = m*m - n*n
            // b = 2*m*n
            // c = m*m + n*n
            // --> a+b+c = p = 2*m*(m+n)
            val p = 2 * m * (m + n)

            val a = m * m - n * n;
            val b = 2 * m * n;
            val c = m * m + n * n;

            if (c % abs(a - b) == 0) {
                count += maxPerimeter / p
            }
        }
        m++
    }
    return count
}