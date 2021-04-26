package pe009

// https://projecteuler.net/problem=9
fun main() {
    val sideSum = 1000

    // just brute force it
    for (a in 1..sideSum/2) {
        for (b in a..sideSum/2) {
            val c = sideSum - a - b
            if (isPythagorean(a, b, c)) {
                println("solution: ($a, $b, $c) -> ${a * b * c}")
            }
        }
    }
}

fun isPythagorean(a: Int, b: Int, c: Int): Boolean {
    return a * a + b * b == c * c
}