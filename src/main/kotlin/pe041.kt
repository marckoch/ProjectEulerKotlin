import util.charFrequencyOf
import util.checkIfPrime
import util.permute
import util.primes

// https://projecteuler.net/problem=41
fun main() {
    // for each number of digits build all permutations and filter primes and find max
    for (maxDigit in 2 until 10) {
        permute((1..maxDigit).toList())
            .map { it.joinToString("").toInt() }
            .filter { checkIfPrime(it.toLong()) }
            // .also { println(it) }
            .maxOrNull()
            .let { println("$maxDigit digits -> $it") }
    }
}

// take all primes and filter all pandigitals and find max -->> too slow!!
fun tooSlow() {
    primes().takeWhile { it < 10_000_000_000L }  // only take primes with up to 10 digits
        .count()
        .let { println(it) }

    primes().takeWhile { it < 10_000_000_000L }  // only take primes with up to 10 digits
        .filter { isPandigital(it.toString()) }
        .maxOrNull()
        .let { println(it) }
}

// a number is pandigital if all its digits occur exactly once
fun isPandigital(n: Int) {
    charFrequencyOf(n.toString())
        .all { entry -> entry.value == 1 }
}