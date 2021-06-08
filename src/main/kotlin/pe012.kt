package pe012

import util.divisors.getDivisorsOf
import util.primefactorization.getPrimeFactors
import util.triangular.triangularNumbers

// https://projecteuler.net/problem=12
fun main() {
    getDivisorsOf(120).let { println(it) }
    getPrimeFactors(120).let { println(it) }
    triangularNumbers
        .first { getDivisorsOf(it).size > 500 }
        .let { println(it) }
}