package pe146

import util.primes.primes
import java.math.BigInteger
import kotlin.math.sqrt
import kotlin.system.measureTimeMillis

// https://projecteuler.net/problem=146
fun main() {
    val limit = 150_000_000L
    
    measureTimeMillis { solve(limit) }.let { println("$it millis") }
}

fun solve(limit: Long) {
    var sum = BigInteger.ZERO
    // step 10 because n^2 + 1, +3, +7, +9 must be prime
    for (n in 10L..limit step 10) {
        val n2 = n * n
        if (isPrime(n2 + 1) &&
            isPrime(n2 + 3) &&
            isPrime(n2 + 7) &&
            isPrime(n2 + 9) &&
            isPrime(n2 + 13) &&
            isPrime(n2 + 27)
        ) {
            if (
                !isPrime(n2 + 5) &&
                !isPrime(n2 + 11) &&
                !isPrime(n2 + 15) &&
                !isPrime(n2 + 17) &&
                !isPrime(n2 + 19) &&
                !isPrime(n2 + 21) &&
                !isPrime(n2 + 23) &&
                !isPrime(n2 + 25)
            ) {
                println("match: $n")
                sum += BigInteger.valueOf(n)
            }
        }
    }
    println(sum)
}

fun isPrime(x: Long): Boolean = BigInteger.valueOf(x).isProbablePrime(1)

// my first try: take 6 consecutive primes and check if they match
// sounds good, doesn't work ;)
// we can not produce primes fast and big enough with our primes sequence generator :-(
fun solve2() {
    var sum = 0L
    primes().take(1_000_000).windowed(6, 1).forEach { list ->
        // println(list)
        val p1 = list[0]
        val p2 = list[1]
        val p3 = list[2]
        val p4 = list[3]
        val p5 = list[4]
        val p6 = list[5]

        if (isSquare(p1 - 1L)) {
            if (listOf(p1 - 1, p2 - 3, p3 - 7, p4 - 9, p5 - 13, p6 - 27).distinct().size == 1) {
                val n = sqrt((p1 - 1).toDouble()).toLong()
                println("$n  $list")
                sum += n

            }
        }
    }
    println(sum)
}

fun isSquare(n: Long): Boolean {
    val s = sqrt(n.toDouble())
    return n == s.toLong() * s.toLong()
}