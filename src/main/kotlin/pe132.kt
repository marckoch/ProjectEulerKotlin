package pe132

import util.primes.primes
import java.math.BigInteger

// https://projecteuler.net/problem=132
fun main() {
    val n = BigInteger.TEN.pow(9)

    var count = 0
    var sum = 0L
    val pIt = primes().iterator()
    while (count < 40) {
        val p = pIt.next()
        if (BigInteger.TEN.modPow(n, BigInteger.valueOf(9 * p)) == BigInteger.ONE) {
            count++
            sum += p
        }
    }
    println(sum)
}