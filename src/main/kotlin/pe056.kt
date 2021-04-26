package pe056

import util.cartesianProduct.cartesianProductOf
import util.digits.digitSum
import java.math.BigInteger

// https://projecteuler.net/problem=56
fun main() {
    val a = (1L..100L).toList()
    val b = (1..100).toList()
    cartesianProductOf(a, b)
        .map { BigInteger.valueOf(it.first).pow(it.second) }
        .map { digitSum(it) }
        .maxOrNull()
        .let { println(it) }
}