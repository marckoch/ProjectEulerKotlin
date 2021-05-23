package pe104

import util.frequency.charFrequencyOf
import java.math.BigInteger
import kotlin.system.measureTimeMillis

// https://projecteuler.net/problem=104
fun main() {
    val time = measureTimeMillis { buildFib() }
    println(time)
}

// brute force it
fun buildFib() {
    var fN2 = BigInteger.ZERO
    var fN1 = BigInteger.ONE
    val tailCutOff = BigInteger.TEN.pow(9)
    var k = 2
    while (true) {
        val fN = fN1 + fN2

        if (fN > tailCutOff) {
            //System.err.println(strFN)

            // f_n mod 10^9 will give the last 9 digits
            val last9digits = fN.mod(tailCutOff).toString()
            val last9arePanDigits = isPandigital(last9digits)
            if (last9arePanDigits) {
                val strFN = fN.toString()
                val first9digits = strFN.substring(0, 9)
                val first9arePanDigits = isPandigital(first9digits)

                if (first9arePanDigits && last9arePanDigits) {
                    println("the winner is $k: ${strFN.take(9)}...${strFN.takeLast(9)}")
                    break
                }
            }
        }

        fN2 = fN1
        fN1 = fN

        k++
    }
}

// a number is pandigital if all its digits occur exactly once (0 excluded)
fun isPandigital(n: String): Boolean {
    if (n.contains("0")) return false

    return charFrequencyOf(n)
        .all { it.value == 1 }
}