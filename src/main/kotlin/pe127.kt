package pe127

import util.gcd.gcd
import util.radical.getRadical
import kotlin.system.measureTimeMillis

// https://projecteuler.net/problem=127
fun main() {
    // example from project euler
    measureTimeMillis { solveForLimit(1_000).let { println(it) } }.let { println("took $it") }

    measureTimeMillis { solveForLimit(120_000).let { println(it) } }.let { println("took $it") }
}

fun solveForLimit(limit: Int): Int {
    return getAbcHits(limit).sumOf { it.third }
}

fun getAbcHits(limit: Int): List<Triple<Int, Int, Int>> {
    // precalc all radicals to save method calls and calculate same thing again and again
    val radicals = LongArray(limit + 1) { i -> if (i == 0) 1 else getRadical(i.toLong()) }

    val hits = ArrayList<Triple<Int, Int, Int>>()
    for (a in 1 until limit / 2) {
        var b = a + 1
        while (a + b <= limit) {
            val c = a + b
            val radical = radicals[a] * radicals[b] * radicals[c]
            if (radical < c) {
                if (gcd(a, b) == 1) { // do this expensive calculation AFTER cheap array lookup of radicals!
                    // println("$a $b $c winner!");
                    hits.add(Triple(a, b, c))
                }
            }
            b++
        }
    }
    return hits
}