package pe074

import util.factorial.factorial

fun main() {
    val factorials = get10Factorials()

    solveProjectEuler(factorials)
}

private fun solveProjectEuler(factorials: List<Int>) {
    (10..999999)
        .map { findLoopLength(it, factorials) }
        .filter { it == 60 }
        .count()
        .let { println(it) }
}

fun findLoopLength(n: Int, factorials: List<Int>): Int {
    val steps: MutableList<Int> = ArrayList()
    steps.add(n)
    var t = n
    while (true) {
        t = getDigitFactorialSum(t, factorials)
        if (steps.contains(t)) {
            break
        }
        steps.add(t)
    }

    //System.err.println("$n > $steps (->$t) len=${steps.size}")
    return steps.size
}

private fun getDigitFactorialSum(i: Int, factorials: List<Int>): Int {
    if (i == 0) {
        return 1
    }

    
    var t = i
    var digitSum = 0
    while (t > 0) {
        val lastDigit = t % 10
        digitSum += factorials[lastDigit]
        t /= 10
    }
    //System.err.printf("$i -> $digitSum");
    return digitSum
}

fun get10Factorials(): List<Int> {
    return (0..9).map { factorial(it).toInt() }
}