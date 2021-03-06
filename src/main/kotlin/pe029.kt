package pe029

import util.cartesianProduct.cartesianProductOf
import java.math.BigInteger

// https://projecteuler.net/problem=29
fun main() {
    // just brute force it
    cartesianProductOf((2..100).toList(), (2..100).toList())
        .map { pair -> BigInteger.valueOf(pair.first.toLong()).pow(pair.second) }
        .sorted()
        .also { println(it) }
        .distinct()
        .count()
        .let { println(it) }
}