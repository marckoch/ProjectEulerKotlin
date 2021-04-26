package util.fraction

import util.gcd.gcd

fun reduceFraction(fraction: Pair<Long, Long>): Pair<Long, Long> {
    val d = gcd(fraction.first, fraction.second)
    return Pair(fraction.first / d, fraction.second / d)
}

fun main() {
    println(reduceFraction(Pair(12, 30)))
    println(reduceFraction(Pair(13, 39)))
    println(reduceFraction(Pair(130, 1390)))
}