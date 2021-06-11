package pe145

import kotlin.system.measureTimeMillis

// https://projecteuler.net/problem=145
fun main() {
//    val limit = 1_000L // example from problem statement
    val limit = 1_000_000_000L

    measureTimeMillis { solveProjectEuler(limit) }.let { println("$it millisecs") }
}

// brute force
// hint: we could store n and reverse(n), every pair is found twice
fun solveProjectEuler(limit: Long) {
    var count = 0
    for (i in 1 until limit) {
        // leading zero not allowed in the reversed number, so i must not end with 0
        if (i % 10 == 0L) {
            continue
        }
        val reverse = reverse(i)
        val sum = i + reverse
        val hasOnlyOddDigits = hasOnlyOddDigits(sum)
        if (hasOnlyOddDigits) {
            // println("$i + $reverse = $sum -> hasOnlyOddDigits");
            count++
        }
    }
    println(count)
}

private fun hasOnlyOddDigits(number: Long): Boolean {
    var n = number
    while (n > 0) {
        val lastDigit = n.toInt() % 10
        if (lastDigit % 2 == 0) {
            return false
        }
        n /= 10
    }
    return true
}

private fun reverse(number: Long): Long {
    var n = number
    var result: Long = 0
    while (n > 0) {
        val lastDigit = n.toInt() % 10
        n /= 10
        result *= 10
        result += lastDigit.toLong()
    }
    return result
}

