package util.primefactorization

import util.primes.primes
import kotlin.collections.HashSet

// nice but too slow
fun getPrimeFactorsSlow(x: Long): Set<Long> {
    return primes()
        .takeWhile { it < x / 2 }
        .also { println(it.toList()) }
        .filter { x % it == 0L }
        .toSet()
}

// just iterate through primes and keep dividing the number
fun getPrimeFactorsOptimized(number: Long): Set<Long> {
    var n = number
    val primesIt = primes().iterator()
    val factors: MutableSet<Long> = HashSet()

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

    return factors.toSortedSet()
}

fun main() {
    println(getPrimeFactorsOptimized(100L))
    println(getPrimeFactorsOptimized(101L))
    println(getPrimeFactorsOptimized(121L))
    println(getPrimeFactorsOptimized(122L))
    println(getPrimeFactorsOptimized(101010010L))
}