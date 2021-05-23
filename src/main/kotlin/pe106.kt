package pe106

import util.combinations.choose

// https://projecteuler.net/problem=106
fun main() {
    // best explanation: https://www.mathblog.dk/project-euler-106-minimum-comparisons-special-sum-sets/
    var result = 0L
    val n = 12

    for (i in 2..n / 2) {
        result += choose(n, i) * choose(n - i, i) / 2
        result -= choose(n, 2 * i) * catalanNumber(i)
    }

    println(result)
}

private fun catalanNumber(n: Int): Long {
    return choose(2 * n, n) / (n + 1)
}