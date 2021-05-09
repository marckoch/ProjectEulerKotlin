package pe077

import util.primes.primes

// https://projecteuler.net/problem=77
fun main() {
    val primes = primes().take(100).map { it.toInt() }.toList()

    getFrequencies(100, primes)
        .indexOfFirst { i -> i > 5000 }
        .let { println(it) }
}

private fun getFrequencies(n: Int, primes: List<Int>): IntArray {
    val frequencies = IntArray(n + 1)
    frequencies[0] = 1
    for (p in primes) {
        for (i in p..n) {
            frequencies[i] += frequencies[i - p]
        }
    }
    return frequencies
}