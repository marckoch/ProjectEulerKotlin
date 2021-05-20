package pe097

import java.math.BigInteger

// https://projecteuler.net/problem=97
fun main() {
    // 28433 × 2^7830457 + 1
    val x = BigInteger.valueOf(28433) * BigInteger.TWO.pow(7830457) + BigInteger.ONE

    println(x.toString().takeLast(10))
}