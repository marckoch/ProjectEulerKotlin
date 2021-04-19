package util

// https://en.wikipedia.org/wiki/Collatz_conjecture
fun collatzOf(n: Long): Sequence<Long> {
    require(n > 0)
    return generateSequence(n) {
        when {
            it == 1L -> null  // end sequence once we reach 1
            it % 2 == 0L -> it / 2
            else -> it * 3 + 1
        }
    }
}

fun main() {
    println(collatzOf(871).toList())
}