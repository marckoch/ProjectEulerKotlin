package util.fraction

import util.gcd.gcd
import java.math.BigInteger

data class Fraction<out T>(val top: T, val bottom: T) {
    fun invert() = Fraction(bottom, top)

    override fun toString(): String {
        return "$top/$bottom"
    }
}

fun reduceFraction(fraction: Fraction<Long>): Fraction<Long> {
    val d = gcd(fraction.top, fraction.bottom)
    return Fraction(fraction.top / d, fraction.bottom / d)
}

fun main() {
    println(reduceFraction(Fraction(12, 30)))
    println(reduceFraction(Fraction(13, 39)))
    println(reduceFraction(Fraction(130, 1390)))

    val r1 = Fraction(3,2)
    println(r1)
    println(r1.top)
    println(r1.bottom)
    println(r1.invert())

    val r2 = Fraction(BigInteger.ONE, BigInteger.TWO)
    println(r2)
    println(r2.top)
    println(r2.bottom)
    println(r2.invert())
}