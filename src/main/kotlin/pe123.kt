package pe123

import util.primes.primes
import java.math.BigInteger

// https://projecteuler.net/problem=123
fun main() {
    val n = 7037 // see problem statement, this has remainder > 10^9, so we don't need to check anything smaller
    val noOfPrimes = n + 15000

    val firstNPrimes: LongArray = primes().take(noOfPrimes).toList().toLongArray()

    val tenTen = BigInteger.TEN.pow(10)

    // slow brute force solution :-(
    var i = n
    while (i < firstNPrimes.size) {
        val nThPrime = getNthPrime(firstNPrimes, i)
        val mod = getMod(nThPrime, i)
        // println("$nThPrime $i $mod")
        if (mod > tenTen) {
            println(" >> $i  $nThPrime")
            break
        }
        i += 2
    }
}

fun getMod(nThPrime: Long, n: Int): BigInteger {
    val bigN = BigInteger.valueOf(nThPrime)
    val nMinus1 = BigInteger.valueOf(nThPrime - 1)
    val nPlus1 = BigInteger.valueOf(nThPrime + 1)
    val mod = (nMinus1.pow(n) + nPlus1.pow(n)).mod(bigN.pow(2))
    // println(mod)
    return mod
}

fun test() {
    val n = 7037
    val firstNPrimes: LongArray = primes().take(n + 15000).toList().toLongArray()
    println(getNthPrime(firstNPrimes, 1))
    println(getNthPrime(firstNPrimes, 2))
    println(getNthPrime(firstNPrimes, 3))
    println(getNthPrime(firstNPrimes, 7037))
    println(getNthPrime(firstNPrimes, 21031))
    println(getNthPrime(firstNPrimes, 21033))
    println(getNthPrime(firstNPrimes, 21035))
    getMod(firstNPrimes[7035], 7035)
    getMod(firstNPrimes[7037], 7037)
    getMod(firstNPrimes[21033], 21033)
    getMod(firstNPrimes[21035], 21035)
}

fun getNthPrime(primes: LongArray, n: Int): Long {
    return primes[n - 1]
}