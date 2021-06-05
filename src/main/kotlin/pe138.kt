package pe138

import util.gcd.gcd
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

// https://projecteuler.net/problem=138
fun main() {
    val limit = 12
//    count(limit).let { println(it) }
    solve(limit)
}

fun solve(limit: Int) {
    var sum = 0L
    var x1 = 1L
    var x2 = 17L
    var x3: Long
    repeat(limit) {
        x3 = x2 * 18 - x1
        x1 = x2
        x2 = x3
        println("$x1 $x2 $x3")

        sum += x1
    }
    println(sum)
}

// brute force, too slow!!
private fun count(limit: Int): Long {
    // https://en.wikipedia.org/wiki/Tree_of_primitive_Pythagorean_triples#Alternative_methods_of_generating_the_tree
    var count = 0
    var sum = 0L
    var m = 1L
    while (count < limit) {
        for (n in 1L until m) {
            if (m % 2 == 1L && n % 2 == 1L) { // not both odd!
                continue
            }
            if (gcd(m, n) > 1) {
                continue
            }

            val a = m * m - n * n;
            val b = 2 * m * n;
            val l = m * m + n * n;

            val h = max(a, b)
            val halfBase = min(a, b)
            if (abs(h - 2 * halfBase) == 1L) {
                println("found $a^2 + $b^2 = $l^2 -> h=$h, base=${2 * halfBase}")
                count++
                sum += l
            }
        }
        m++
    }
    return sum
}