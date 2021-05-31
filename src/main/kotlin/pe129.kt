package pe129

import util.gcd.gcd

// https://projecteuler.net/problem=129
fun main() {
    println(A(7)) // 6
    println(A(41)) // 5  examples from problem statement

    (1..100 step 2).map { println("$it ${A(it)}") }

    // n can't be even, because 2 will never divide a repunit evenly, we will always have odd remainder
    val naturalNumbers = generateSequence(1_000_001) { it + 2 }
    naturalNumbers.first { A(it) > 1_000_000 }.let { println(it) }

}

private fun A(n: Int): Int {
    if (gcd(n, 10) != 1) return 0
    var x = 1
    var k = 1
    while (x != 0) {
        x = (x * 10 + 1) % n
        k++
    }
    return k
}