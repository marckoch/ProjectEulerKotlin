package pe057

import util.fraction.Fraction
import java.math.BigInteger

// https://projecteuler.net/problem=57
fun main() {
    // we can see the formula for generating the expansions is:
    // x        x + 2 * y
    // -  -->   ---------
    // y         x + y
    val firstExpansion = Fraction(BigInteger.valueOf(3L), BigInteger.TWO)
    val expansions = generateSequence(firstExpansion) {
        Fraction(it.top + it.bottom * BigInteger.TWO, it.top + it.bottom)
    }

    expansions.take(1000)
        .count { countDigits(it.top) > countDigits(it.bottom) }
        .let { println(it) }
}

fun countDigits(n: BigInteger) = n.toString().length