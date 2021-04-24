import util.getPrimeFactorsOptimized

// https://projecteuler.net/problem=47
fun main() {
    // number of consecutive numbers and number of distinct prime factors
    val N = 4

    val numbers = generateSequence(1) { it + 1 }
    numbers
        .windowed(N, 1)
        .first { list -> list.all { getPrimeFactorsOptimized(it.toLong()).size == N } }
        .let { println(it) }
}