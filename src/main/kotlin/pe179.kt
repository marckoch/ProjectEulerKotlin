package pe179

import util.divisors.getDivisorsOf

// TODO UNFINISHED
// https://projecteuler.net/problem=179
fun main() {
//    getDivisorsOf(14).count().let { println(it) }
//    getDivisorsOf(15).count().let { println(it) }

    val divisors = (0..10e3.toInt()).map { i -> getDivisorsOf(i.toLong()) }
//    val divisors = IntArray(10e3.toInt()) { i -> getDivisorsOf(i.toLong()) }
//    println(divisors.contentToString())

//    divisors.toList().windowed(2, 1).filter { list -> list[0] == list[1] }.let { println(it) }

    divisors
        .withIndex()
        .windowed(2, 1)
        .filter { it[0].value.size == it[1].value.size }
        .filter { indexedValue -> indexedValue[0].value.contains(2) }
        .forEach { println(it) }
}