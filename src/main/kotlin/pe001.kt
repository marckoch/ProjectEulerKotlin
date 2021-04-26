package pe001

// https://projecteuler.net/problem=1
fun main() {
    (0 until 1000)
        .filter { it % 3 == 0 || it % 5 ==0 }
        .sum()
        .let { println(it) }
}