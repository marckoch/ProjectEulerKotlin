package pe148

import kotlin.system.measureTimeMillis

// https://projecteuler.net/problem=148
// https://euler.stephan-brumme.com/148
// https://cjordan.github.io/2013/12/29/solving-project-euler-148/
// https://en.wikipedia.org/wiki/Lucas%27s_theorem
fun main() {
    val noOfRows = 1e9.toLong()

//    noOfRows.toString(7).let { println(it) }
//    convert(noOfRows.toString(7)).let { println(it) }

//    bruteForce(noOfRows)
    measureTimeMillis { bruteForce2(noOfRows) }.let { println("$it millisecs") }
}

// https://en.wikipedia.org/wiki/Lucas%27s_theorem
// take each digit, increase it by 1 and multiply all digits
fun convert(noInBase7: String): Long {
    return noInBase7
        .map { c -> Character.getNumericValue(c) + 1 }
        .reduce { acc, c -> acc * c }.toLong()
}

fun bruteForce2(noOfRows: Long) {
    // OutOfMemoryError when using map:
//    val s = (0L until noOfRows).map { l -> convert(l.toString(7)) }.sum()
//    println(s)

    var count = 0L
    for (row in 0L until noOfRows) {
        val x = row.toString(7)
        count += convert(x)
    }
    println(count)
}

fun bruteForce(noOfRows: Long) {
    var count = 0L
    for (row in 0L until noOfRows) {
        val r = (0..row).map { i -> binomMod7(row, i) }
        val c = r.count { it > 0 }

        count += c
        r.map { l -> if (l == 0L) ' ' else 'X' }.joinToString("").let { println("$row ${row.toString(7)} $c  $count  ${count.toString(7)}  $it") }

        // if (row % 1000L == 0L) print("*")
    }
    println(count)
}

fun binom(n: Long, k: Long): Long {
    if (k == 0L) return 1
    if (n == k) return 1
    return binom(n - 1, k - 1) + binom(n - 1, k)
}

// we use a cache because small values are calculated again and again
fun binomMod7(n: Long, k: Long): Long {
    if (k == 0L) return 1
    if (n == k) return 1

    val key = Pair(n, k)
    if (cache.containsKey(key)) return cache[key]!!

    val result = (binomMod7(n - 1, k - 1) % 7 + binomMod7(n - 1, k) % 7) % 7
    cache[Pair(n, k)] = result
    return result
}

val cache = mutableMapOf<Pair<Long, Long>, Long>()