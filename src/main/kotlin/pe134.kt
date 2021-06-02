package pe134

import util.primes.primes
import kotlin.math.pow
import kotlin.system.measureTimeMillis

// https://projecteuler.net/problem=134
fun main() {
    val limit = 1_000_000

    measureTimeMillis { solve(limit).let { println(it) } }.let { println("took $it") }
}

// slow brute force solution (~3 minutes)
fun solve(limit: Int): Long {
    return primes()
        .drop(2) // p1 >= 5
        .windowed(2, 1)
        .takeWhile { it.first() < limit } // p1 <= limit
        .map { list ->
            val p1 = list.first()
            val p2 = list.last()

            val n = findN(p1, p2)
            // println("$p1 $p2 $n")
            n
        }
        .sum()
}

// just construct numbers n by adding p1 to 10^digitOfP1 and check if this has mod 0 when dividing by p2
fun findN(p1: Long, p2: Long): Long {
    var prefix = 1
    val lengthP1 = countDigits(p1)
    val factor = 10.0.pow(lengthP1).toLong()
    while (true) {
        val n = prefix * factor + p1
        if (n % p2 == 0L) {
            return n
        }
        prefix++
    }
}

fun countDigits(number: Long): Int {
    return number.toString().length
}