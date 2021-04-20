import util.fibonacciAsBigIntegers

// https://projecteuler.net/problem=24
fun main() {
    fibonacciAsBigIntegers()
        .withIndex()
        .first { it.value.toString().length >= 1000 }
        .let { println(it.index) }
}