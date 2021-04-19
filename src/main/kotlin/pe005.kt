// https://projecteuler.net/problem=5
fun main() {
    // idea: get primes below 20 and produce max multiples of each prime that is below 20
    // e.g. 2 --> 16 (== 2^4), next multiple 2^5 == 32 would exceed 20
    // we can see that this only applies to 2 and 3, as 5^2 is already > 20
    val primesBelow20 = intArrayOf(2, 3, 5, 7, 11, 13, 17, 19)

    primesBelow20
        .map { i -> maxMultipleBelow20(i) }
        .also { println(it) }
        .reduce { x, y -> x * y }
        .let { println(it) }
}

fun maxMultipleBelow20(x: Int): Int {
    var i = x
    while (i * x < 20) {
        i *= x
    }
    return i
}