package pe119

import util.digits.digitSum
import java.math.BigInteger
import java.util.*
import kotlin.system.measureTimeMillis

// https://projecteuler.net/problem=119
fun main() {
    val time = measureTimeMillis { findNumbers() }
    println("took $time")
}

fun findNumbers() {
    val winners: MutableMap<BigInteger, String> = TreeMap()
    t@for (base in 2L..100) {
        var number = BigInteger.valueOf(base)
        for (exp in 1..99) {
            number = number.multiply(BigInteger.valueOf(base))
            val digitSum = digitSum(number).toLong()
            if (digitSum == base) {
                winners[number] = "$digitSum^$exp"
            }
        }
    }

    winners.entries.withIndex().forEach { indexedValue ->
        println("${indexedValue.index + 1} ${indexedValue.value}")
    }

    winners.entries.drop(29).first().let { println(it) }
}