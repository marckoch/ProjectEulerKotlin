package pe035

import util.primes.isPrime
import util.rotations.leftRotationsOf

// https://projecteuler.net/problem=35
fun main() {
    // to speed things up we prebuild the primes array
    val isPrime = isPrime(1_000_000)

    (1..1_000_000)
        .filter { checkIfCircular(it, isPrime) }
        .also { println(it) }
        .count()
        .let { println(it) }
}

private fun checkIfCircular(n: Int, primes: BooleanArray): Boolean {
    if (n < 2) return false
    val nAsString = n.toString()

    if (nAsString.endsWith("0")) return false // can't be prime

    // a number with more than 1 digit containing one of these will have a circulation
    // where this digit is at the end and than the number is guaranteed to be NO prime
    if (nAsString.length > 1 &&
        (nAsString.contains("2") ||
        nAsString.contains("4") ||
        nAsString.contains("6") ||
        nAsString.contains("8") ||
        nAsString.contains("5"))) {
        return false
    }

    return leftRotationsOf(nAsString)
        .map { it.toInt() }
        .all { primes[it] }
}

