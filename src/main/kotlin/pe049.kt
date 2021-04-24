import util.primes

// https://projecteuler.net/problem=49
fun main() {
    primesWith4digits
        .filter { isFirstOfTriplet(it) }
}

fun isFirstOfTriplet(first: Long): Boolean {
    val limit = (10_000 - first) / 2   // because first + diff + diff must not exceed 10_000

    // 2 because primes have always a difference of at least 2
    for (diff in 2..limit step 2) {
        val second = first + diff
        val third = second + diff
        if (primesWith4digits.contains(second) && primesWith4digits.contains(third) &&
            isPandigitalPair(first, second) && isPandigitalPair(second, third)
        ) {
            println("$first$second$third  (diff=$diff)")
            return true
        }
    }
    return false
}

fun isPandigitalPair(p1: Long, p2: Long): Boolean {
    return p1.toString().toCharArray().sorted() == p2.toString().toCharArray().sorted()
}

val primesWith4digits =
    primes()
        .dropWhile { it < 1_000 }
        .takeWhile { it < 10_000 }
        .toList()