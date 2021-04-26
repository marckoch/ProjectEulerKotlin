package util.gcd

// greatest common divisor
// https://en.wikipedia.org/wiki/Greatest_common_divisor
// https://en.wikipedia.org/wiki/Euclidean_algorithm

fun gcd(a: Long, b: Long): Long {
    return if (b == 0L) a else gcd(b, a % b)
}

fun gcd(a: Int, b: Int): Int {
    return if (b == 0) a else gcd(b, a % b)
}

fun main() {
    println(gcd(12, 40))
}