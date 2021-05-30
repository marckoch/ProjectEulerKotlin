package pe128

import util.primes.isPrime
import java.util.*
import kotlin.math.abs

private val isPrime = isPrime(1_000_000)

// https://projecteuler.net/problem=128
fun main() {
    val numbersWith3Primes: MutableList<Long> = ArrayList()
    var n = 1L

    for (ring in 0..80_000) {
        // println("ring $ring (${ring * 6} numbers)");
        for (i in 0 until ring * 6) {
            n++
            if (i == 0) {
                run {
                    // number at the top (2, 8, 20, ...)
                    val number = n
                    val top = number + 6 * ring
                    val lowerRight = top - 1
                    val topRight = lowerRight + 6 * (ring + 1)
                    val below = number - 6 * (ring - 1)
                    val lowerLeft = number + 1
                    val topLeft = top + 1

                    val countPrimes = countPrimeNeighbors(number, top, topRight, lowerRight, below, lowerLeft, topLeft)
                    if (countPrimes == 3) {
                        // println("   $number: $top $topRight $lowerRight $below $lowerLeft $topLeft")
                        numbersWith3Primes.add(number)
                    }
                }
                run {
                    // number right below the top number (7, 19, 37, ...)
                    val number = n + ring * 6 - 1
                    val top = number + 6 * (ring + 1)
                    val topRight = top - 1
                    val lowerRight = number - 1
                    val below = number - 6 * ring
                    val lowerLeft = below + 1 - 6 * (ring - 1)
                    val topLeft = below + 1

                    val countPrimes = countPrimeNeighbors(number, top, topRight, lowerRight, below, lowerLeft, topLeft)
                    if (countPrimes == 3) {
                        // println("   $number: $top $topRight $lowerRight $below $lowerLeft $topLeft")
                        numbersWith3Primes.add(number)
                    }
                }
            }
        }
        if (numbersWith3Primes.size > 2000) break
    }
    println(numbersWith3Primes.size)
    println(numbersWith3Primes[9])
    println(numbersWith3Primes[1999])
}

private fun countPrimeNeighbors(
    number: Long,
    top: Long,
    topRight: Long,
    lowerRight: Long,
    below: Long,
    lowerLeft: Long,
    topLeft: Long
): Int {
    val diff2Top = abs(top - number).toInt()
    val diff2TopRight = abs(topRight - number).toInt()
    val diff2LowerRight = abs(lowerRight - number).toInt()
    val diff2Below = abs(below - number).toInt()
    val diff2LowerLeft = abs(lowerLeft - number).toInt()
    val diff2TopLeft = abs(topLeft - number).toInt()

    var countPrimes = 0
    if (isPrime[diff2Top]) countPrimes++
    if (isPrime[diff2TopRight]) countPrimes++
    if (isPrime[diff2LowerRight]) countPrimes++
    if (isPrime[diff2Below]) countPrimes++
    if (isPrime[diff2LowerLeft]) countPrimes++
    if (isPrime[diff2TopLeft]) countPrimes++

    // println("   $number: $diff2Top $diff2TopRight $diff2LowerRight $diff2Below $diff2LowerLeft $diff2TopLeft  primes:$countPrimes");
    return countPrimes
}

