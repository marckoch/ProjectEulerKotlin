package util.convergents

import util.fraction.Fraction
import java.math.BigInteger

fun main() {
    convergentOfE.take(10).forEach { println(it) }

    convergentOfSqrt2.take(10).forEach { println(it) }
}

val convergentOfE = generateSequence(1) { it + 1 }
    .map { i -> getConvergentBI(i, 2, ::getConvergenceNumberOfE) }

// get the i-th convergent of sqrt(2)
// sqrt(2) = 1 + 1 / (2 + 1 / (2 + ...)
// first is the first number (1 in convergent of 2)
// getConvergenceNumber is a function that returns the i-th conv number, for sqrt(2) this is always 2
val convergentOfSqrt2 = generateSequence(1) { it + 1 }
    .map { i -> getConvergentBI(i, 1, ::getConvergenceNumberOfSqrt2) }

fun getConvergentBI(n: Int, first: Int, getConvergenceNumber: (Int) -> Long): Fraction<BigInteger> {
    if (n == 1) return Fraction(BigInteger.valueOf(first.toLong()), BigInteger.ONE)

    var current = Fraction(BigInteger.ONE, BigInteger.valueOf(getConvergenceNumber(n)))
    // System.err.println("  $current")

    for (i in n - 1 downTo 2) {
        current = addWithCommonDenominator(getConvergenceNumber(i), current)
        current = current.invert()
        // System.err.println("$i $current")
    }
    current = addWithCommonDenominator(first.toLong(), current)
    // System.err.println("1 $current")
    return current
}

fun getConvergenceNumberOfE(n: Int): Long {
    return if (n % 3 == 0) {
        2L * n / 3
    } else 1L
}

fun getConvergenceNumberOfSqrt2(n: Int): Long {
    return 2L
}


//      4)   3 * 5   4   (3 * 5) + 4
// f(3, -) = ----- + - = -----------
//      5)     5     5        5
// n is expanded with denominator of fraction and added to fraction
fun addWithCommonDenominator(n: Long, fraction: Fraction<BigInteger>): Fraction<BigInteger> {
    val top = fraction.top
    val bottom = fraction.bottom
    val bigN = BigInteger.valueOf(n)
    return Fraction(bottom * bigN + top, bottom)
}