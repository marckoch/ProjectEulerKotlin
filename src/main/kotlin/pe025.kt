package pe025

import util.fibonacci.fibonacciAsBigIntegers

// https://projecteuler.net/problem=25
fun main() {
    fibonacciAsBigIntegers()
        .withIndex()
        .first { it.value.toString().length >= 1000 }
        .let { println(it.index) }
}