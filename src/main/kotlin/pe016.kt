package pe016

import util.digits.digitSum
import java.math.BigInteger

// https://projecteuler.net/problem=16
fun main() {
    val n = BigInteger.valueOf(2).pow(1000)
    println(n)

    println(digitSum(n))
}