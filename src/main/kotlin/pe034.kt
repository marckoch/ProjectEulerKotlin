package pe034

import util.factorial.factorial

// https://projecteuler.net/problem=34
fun main() {
    // for upper limit see:
    // https://www.xarg.org/puzzle/project-euler/problem-34/
    // https://www.mathblog.dk/project-euler-34-factorial-digits/
    (3..1_000_000)
        .filter { it == sumOfFactorialOfDigits(it) }
        .also { println(it) }
        .sum()
        .let { println(it) }
}

fun sumOfFactorialOfDigits(n: Int): Int {
    return n.toString()
        .chars()
        .map { Character.getNumericValue(it) }
        .map { factorial(it).toInt() }
        .sum()
}