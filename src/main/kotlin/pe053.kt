package pe053

import util.factorial.factorial
import java.math.BigInteger

// https://projecteuler.net/problem=53
fun main() {
    val limit = BigInteger.valueOf(1_000_000L)

    var sum = 0
    for (n in 1..100) {
        for (r in 0..n) {
            if (comb(n, r) >= limit)
                sum++
        }
    }
    println(sum)

    (1..100).flatMap { n ->
        (0..n).map { r ->
            comb(n, r)
        }
    }
        .filter { it > limit }
        .count()
        .let { println(it) }
}

fun comb(n: Int, r: Int): BigInteger {
    return factorial(n) / (factorial(r) * factorial(n - r))
}