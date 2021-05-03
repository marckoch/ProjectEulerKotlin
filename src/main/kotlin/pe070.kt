package pe070

import util.totient.computeTotient

// https://projecteuler.net/problem=70
fun main() {
    computeTotient(10_000_000)
        .drop(2) // because n > 1
        .withIndex()
        .filter { indexedValue -> sortDigits(indexedValue.index) == sortDigits(indexedValue.value) }
        .minByOrNull { indexedValue -> indexedValue.index.toDouble() / indexedValue.value }
        .let { println(it) }
}

fun sortDigits(i: Int): String {
    return String(i.toString().toCharArray().sorted().toCharArray())
}