package pe124

import util.radical.getRadical

// https://projecteuler.net/problem=124
fun main() {
    val collect = (1L..100_000)
        .map { NumberWithRadical(it, getRadical(it)) }
        .sortedWith(compareBy<NumberWithRadical> { it.second }.thenBy { it.first })

    // collect.forEach { pair -> println(pair) }

    println("E(10_000) = ${collect[10000 - 1]}") // 0 is 1, etc... ;-)
}

typealias NumberWithRadical = Pair<Long, Long>