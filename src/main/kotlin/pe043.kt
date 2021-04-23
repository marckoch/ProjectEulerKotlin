import util.permute
import util.primes

// https://projecteuler.net/problem=43
fun main() {
    val digits = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)

    permute(digits)
        .filter { isSubstringDivisible(it) }
        .also { println(it) }
        .sumOf { list -> list.joinToString("").toLong() }   // convert list of digits to Long
        .let { println(it) }
}

// 2, 3, 5, 7, 11, 13, 17
val primes = primes()
    .take(7)
    .toList()

fun isSubstringDivisible(digits: List<Int>): Boolean {
    return digits
        .subList(1, digits.size)        // first digit is ignored
        .windowed(3, 1)
        .map { list -> list.joinToString("").toLong() }  // convert list of digits to Long
        .withIndex()
        .all { it.value % primes[it.index] == 0L }
}