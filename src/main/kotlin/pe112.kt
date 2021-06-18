package pe112

import util.digits.digits
import kotlin.system.measureTimeMillis

// https://projecteuler.net/problem=112
fun main() {
    val time = measureTimeMillis { solveProjectEuler() }
    println(time)
}

fun solveProjectEuler() {
    val expectedRatio = 0.99
    var countInc = 0
    var countDec = 0
    var countDoubles = 0
    var countBouncy = 0
    var found = false
    var n = 1L
    while (!found) {
        val hasIncDigits = hasIncreasingDigits(n)
        if (hasIncDigits) {
            countInc++
        }

        val hasDecDigits = hasDecreasingDigits(n)
        if (hasDecDigits) {
            countDec++
        }

        // account for 6666 or 3333333, those will be counted twice
        if (hasDecDigits && hasIncDigits) {
            countDoubles++
        }

        if (!hasDecDigits && !hasIncDigits) {
            countBouncy++
        }

        if (countBouncy >= expectedRatio * n) {
            val ratioHitAt = n.toInt()
            println(String.format("ratio of %s hit at n=%s", expectedRatio, ratioHitAt))
            found = true
        } else {
            n++
        }
    }
    println("inc:    $countInc")
    println("dec:    $countDec")
    println("doubles $countDoubles")
    println("nonbouncy:    ${countInc + countDec - countDoubles}")
    println("bouncy: $countBouncy")
    println("n: $n")
    println("%: ${countBouncy.toDouble() / n}")
}

private fun hasIncreasingDigits(number: Long): Boolean {
    // too slow
//    return digits(number).toList().windowed(2, 1).all { list ->
//        list.first() <= list.last()
//    }

    val digits = digits(number)
    for (i in 0 until digits.size - 1) {
        if (digits[i] > digits[i + 1] ) return false
    }
    return true
}

private fun hasDecreasingDigits(number: Long): Boolean {
    // too slow
//    return digits(number).toList().windowed(2, 1).all { list ->
//        list.first() >= list.last()
//    }

    val digits = digits(number)
    for (i in 0 until digits.size - 1) {
        if (digits[i] < digits[i + 1]) return false
    }
    return true
}

fun countDigits(number: Long): Int {
    return number.toString().length
}