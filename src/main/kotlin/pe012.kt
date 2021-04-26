package pe012

import util.divisors.getDivisorsOf
import util.triangular.triangularNumbers

// https://projecteuler.net/problem=12
fun main() {
    triangularNumbers
        .first { getDivisorsOf(it).size > 500 }
        .let { println(it) }
}