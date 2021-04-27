package pe058

import util.primes.checkIfPrime

// https://projecteuler.net/problem=58
fun main() {
    val targetPercentage = 10

    val limit = 100000L

    var countPrimes = 0L
    var countTotal = 1L  // account for n=1

    for (layer in 1..limit) {
        countPrimes += countPrimesByLayer(layer)
        countTotal += 4

        // val ratio = countPrimes.toDouble() / countTotal
        // System.err.println("layer=$layer sidelength=${getSideLength(layer)} countPrimes=$countPrimes countTotal=$countTotal ratio=$ratio");

        if (countPrimes * 100 < targetPercentage * countTotal) {
            println(getSideLength(layer))
            break
        }
    }
}

fun getSideLength(layer: Long): Long {
    return 2 * layer + 1
}

fun countPrimesByLayer(layer: Long): Int {
    val n = getSideLength(layer)  // n == length of the square

    val lowerRight = n * n
    val lowerLeft = lowerRight - n + 1
    val upperLeft = lowerLeft - n + 1
    val upperRight = upperLeft - n + 1

//    System.err.println("n=$n $lowerRight $lowerLeft $upperLeft $upperRight");

    // lowerRight is not in list because it is always n*n, so NEVER a prime
    return listOf(lowerLeft, upperLeft, upperRight)
        .filter { checkIfPrime(it) }
        .count()
}