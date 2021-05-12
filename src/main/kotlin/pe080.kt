package pe080

import util.triangular.isSquare
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

// https://projecteuler.net/problem=80
fun main() {
    // example 2 -> 475
    val sqrt2 = sqrt(BigDecimal.valueOf(2), 105)
    println(getDecimalDigitSum(sqrt2, 100))

    println(solve(100, 100))
}

fun solve(n: Int, numberOfDigits: Int): Int {
    return (1L..n)
        .filterNot { isSquare(it) } // skip squares, we only want irrational roots
        .sumOf {
            val s = sqrt(BigDecimal.valueOf(it), numberOfDigits + 5)
            getDecimalDigitSum(s, numberOfDigits)
        }
}

// https://stackoverflow.com/questions/13649703/square-root-of-bigdecimal-in-java
fun sqrt(x: BigDecimal, scale: Int): BigDecimal {
    var x0 = BigDecimal.ZERO
    val TWO = BigInteger.TWO.toBigDecimal()
    var x1 = BigDecimal(kotlin.math.sqrt(x.toDouble()))
    while (x0 != x1) {
        x0 = x1
        x1 = x.divide(x0, scale, RoundingMode.HALF_UP)
        x1 += x0
        x1 = x1.divide(TWO, scale, RoundingMode.HALF_UP)
    }
    return x1
}

private fun getDecimalDigitSum(i: BigDecimal, numberOfDigits: Int): Int {
    return i.toString()
        .toCharArray()
        .filter { c -> c != '.' } // count every digit before or after decimal point EXCEPT decimal point!
        .take(numberOfDigits)
        .sumOf { c -> c - '0' }
}