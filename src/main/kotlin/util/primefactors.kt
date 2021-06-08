package util.primefactorization

import util.primes.primes

// nice but too slow
fun getPrimeFactorsSlow(x: Long): Set<Long> {
    return primes()
        .takeWhile { it < x / 2 }
        .also { println(it.toList()) }
        .filter { x % it == 0L }
        .toSet()
}

// just iterate through primes and keep dividing the number
fun getPrimeFactors(number: Long): List<Long> {
    var n = number
    val primesIt = primes().iterator()
    val factors: MutableList<Long> = ArrayList()

    do {
        val prime: Long = primesIt.next()
        while (n % prime == 0L) {
            factors.add(prime)
            n /= prime
        }
    } while (prime <= n / prime)

    if (n > 1) {
        factors.add(n)
    }

    return factors
}

fun main() {
    println(getPrimeFactors(100L))
    println(getPrimeFactors(101L))
    println(getPrimeFactors(121L))
    println(getPrimeFactors(122L))
    println(getPrimeFactors(101010010L))
}