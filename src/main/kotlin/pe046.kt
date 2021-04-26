package pe046

import util.primes.checkIfPrime

// https://projecteuler.net/problem=46
fun main() {
    val oddNumbers = generateSequence(1L) { it + 2 }
    oddNumbers
        .filter { !checkIfPrime(it) }   // composite number means NO primes!
        .first { !isComposite(it) }
        .let { println(it) }
}

fun isComposite(n: Long): Boolean {
    var i = 0L
    while (2 * i * i < n) {  // rest should be positive
        val rest = n - 2 * i * i
        if (checkIfPrime(rest)) {
            return true
        }
        i++
    }
    return false
}