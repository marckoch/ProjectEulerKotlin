package pe047

import util.primefactorization.getPrimeFactors

// https://projecteuler.net/problem=47
fun main() {
    // number of consecutive numbers and number of distinct prime factors
    val N = 4

    val numbers = generateSequence(1) { it + 1 }
    numbers
        .windowed(N, 1)
        .first { list -> list.all { getPrimeFactors(it.toLong()).distinct().size == N } }
        .let { println(it) }
}