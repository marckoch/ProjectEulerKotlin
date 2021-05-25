package pe113

import util.binomial.binomial
import java.math.BigInteger

// https://projecteuler.net/problem=113
fun main() {
    // good explanation: https://github.com/nayuki/Project-Euler-solutions/blob/master/python/p113.py
    val n = 100L

    val increasing = binomial(n + 9, 9) - BigInteger.ONE
    val decreasing = binomial(n + 10, 10) - BigInteger.valueOf(n + 1)
    val flat = BigInteger.valueOf(9 * n)

    val result = increasing + decreasing - flat

    println(result)
}

