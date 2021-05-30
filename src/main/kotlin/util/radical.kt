package util.radical

import util.primefactorization.getPrimeFactorsOptimized

fun getRadical(n: Long): Long {
    return getPrimeFactorsOptimized(n).distinct().fold(1L) { a, b -> a * b }
}

fun main() {
    assert(getRadical(504).also { println(it) } == 42L)
}