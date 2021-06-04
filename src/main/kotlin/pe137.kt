package pe137

import util.fibonacci.fibonacci

// https://projecteuler.net/problem=137
// https://oeis.org/A081018
fun main() {
    val fib = fibonacci().take(50).toList()

    for (n in 1..15) {
        val x = fib[2 * n] * fib[2 * n + 1]
        println(x)
    }
}