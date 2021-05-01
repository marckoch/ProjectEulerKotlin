package pe064

import util.continueFraction.getPeriodLength

// https://projecteuler.net/problem=64
fun main() {
    (1..10_000)
        .map { getPeriodLength(it) }
        .filter { odd(it) }
        .count()
        .let { println(it) }
}

val odd = { i: Int -> i % 2 == 1 }
