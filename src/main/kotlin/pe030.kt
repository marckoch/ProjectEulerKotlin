import kotlin.math.pow

// https://projecteuler.net/problem=30
fun main() {
    val x = 5

    // upper bound can be found here:
    // https://www.mathblog.dk/project-euler-30-sum-numbers-that-can-be-written-as-the-sum-fifth-powers-digits/
    // https://www.xarg.org/puzzle/project-euler/problem-30/
    val limit = 7 * 9.0.pow(x.toDouble()).toInt()

    (2..limit)
        .filter { it == sumOfXthPowerOfDigits(it, x) }
        .sum()
        .let { println(it) }
}

// take the digits of n, power them to the x-th and sum it all up
fun sumOfXthPowerOfDigits(n: Int, x: Int) = n.toString()
    .chars()
    .map { Character.getNumericValue(it) }
    .map { it.toDouble().pow(x).toInt() }
    .sum()
