package pe131

import util.primes.isPrime

// https://projecteuler.net/problem=131
// https://www.mathblog.dk/project-euler-131-primes-perfect-cube/
fun main() {
    val LIMIT = 1000
    var result = 0
    val isPrime: BooleanArray = isPrime(1_000_000)
    for (i in 0 until LIMIT) {
        val j = i + 1
        val p = j * j * j - i * i * i

        if (p > 1_000_000) break
        if (isPrime[p]) {
            println("$i $j $p")
            result++
        }
    }
    println(result)
}