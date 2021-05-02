package pe066

import java.math.BigInteger
import kotlin.math.sqrt

// https://projecteuler.net/problem=66
fun main() {
    findMax(1000).let { println(it) }
}

fun findMax(max: Int): Int {
    var dForMaxX = 0
    var maxX = BigInteger.ZERO

    for (D in 2..max) {
        val x = getSmallestSolutionFor(D).first
        // System.err.println("$x $D")

        if (x > maxX) {
            maxX = x
            dForMaxX = D
            // System.err.println("found new max $maxX for D=$dForMaxX")
        }
    }

    return dForMaxX
}

// get the smallest solution for x^2 -D * y^2 = 1 for given D
// e.g. getSmallestSolutionFor(7) -> (8,3)
// because 8^2 - 7 * 3^2 = 1
//
// see: https://en.wikipedia.org/wiki/Pell%27s_equation#The_smallest_solution_of_Pell_equations
// https://en.wikipedia.org/wiki/Pell%27s_equation#Fundamental_solution_via_continued_fractions
fun getSmallestSolutionFor(D: Int): Pair<BigInteger, BigInteger> {
    val limit = BigInteger.valueOf(sqrt(D.toDouble()).toLong())
    val bigD = BigInteger.valueOf(D.toLong())

    if (limit.multiply(limit) == bigD) { // D is square -> no smallest solution
        return Pair(BigInteger.ZERO, BigInteger.ZERO)
    }

    var m = BigInteger.ZERO
    var d = BigInteger.ONE
    var a = limit
    var x1 = BigInteger.ONE
    var x = a
    var y1 = BigInteger.ZERO
    var y = BigInteger.ONE

    // System.err.println("$m $d $a")
    while (x * x - (y * y * bigD) != BigInteger.ONE) {
        m = d * a - m
        d = (bigD - (m * m)) / d
        a = (limit + m) / d

        val x2 = x1
        x1 = x
        val y2 = y1
        y1 = y

        x = a * x1 + x2
        y = a * y1 + y2
        // System.err.println("$m $d $a")
    }
    // System.err.println("$x^2 - $D * $y^2 = 1")
    return Pair(x, y)
}