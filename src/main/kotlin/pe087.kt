package pe087

import util.primes.primes
import java.math.BigInteger
import kotlin.math.ceil
import kotlin.math.sqrt

// https://projecteuler.net/problem=87
fun main() {
    // see example
    buildNumbersUpTo(50).size.let { println(it) }

    buildNumbersUpTo(50_000_000).size.let { println(it) }
}

// we construct numbers and store them in a set, because some numbers can be constructed
// in more than one way, but we want to count them only once
fun buildNumbersUpTo(N: Int): HashSet<BigInteger> {
    val numbers = HashSet<BigInteger>()
    val Nbi = BigInteger.valueOf(N.toLong())

    // rough estimate of the maximum prime we need
    val maxPrimeNeeded = ceil(sqrt(N.toDouble())).toInt()
    val primes: LongArray = primes().takeWhile { it < maxPrimeNeeded }.toList().toLongArray()

    for (prime1 in primes) {
        for (prime2 in primes) {
            for (prime3 in primes) {
                val bi = BigInteger.valueOf(prime1).pow(2) +
                        BigInteger.valueOf(prime2).pow(3) +
                        BigInteger.valueOf(prime3).pow(4)
                if (bi < Nbi) {
                    numbers.add(bi)
                } else {
                    break
                }
            }
        }
    }
    return numbers
}