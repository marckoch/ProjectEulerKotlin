package pe193

import util.primes.primes
import kotlin.math.pow
import kotlin.math.sqrt

// TODO UNFINISHED
// https://projecteuler.net/problem=193
fun main() {
    val limit = 2.0.pow(50).toInt()
    println(limit)
    val check = BooleanArray(limit) {true}

    val primes = primes().takeWhile { it <= sqrt(limit.toDouble()).toInt() }.toList()
//    println(primes)

    for (p in primes) {
        val pSquared = p.toInt() * p.toInt()
        var k = 0
        do {
            check[pSquared * k] = false
            k++
        } while (pSquared * k < limit)
    }
//    println(check.contentToString())
    (0 until limit).count { check[it] }.let { println(it) }
//    (0 until limit).filter { check[it] }.let { println(it) }
}