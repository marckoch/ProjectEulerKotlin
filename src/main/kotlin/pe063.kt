package pe063

import java.math.BigInteger

// https://projecteuler.net/problem=63
fun main() {
    solve()
}

fun solve() {
    (1..30)
        .flatMap { countPowersWithDigits(it) }
        .count()
        .let { println(it) }
}

fun countPowersWithDigits(n: Int): List<BigInteger> {
    return (1L..9)
        .map { BigInteger.valueOf(it).pow(n) }
        .filter { it.toString().length == n }
        .also { println("$n $it") }
}

fun solve2() {
    var found = 0
    for (p in 1..30) {
        for (i in 1L..10L) {
            val x = BigInteger.valueOf(i).pow(p)
            if (x.toString().length == p) {
                found++
                println("$x = $i ^ $p")
            }
        }
    }
    println(found)
}