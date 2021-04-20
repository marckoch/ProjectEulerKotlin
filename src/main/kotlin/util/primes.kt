package util

import kotlin.math.floor
import kotlin.math.sqrt

// https://gist.github.com/domnikl/6ad30f0a6ca3228f44e8273c08dbd879
fun primes(): Sequence<Long> {
    var i = 2L
    return sequence {
        generateSequence { i++ }
            .filter { n ->
                when {
                    n == 1L -> false
                    n < 4 -> true
                    n.rem(2) == 0L -> false
                    n < 9 -> true
                    n.rem(3) == 0L -> false
                    else -> {
                        val r = floor(sqrt(n.toDouble()))
                        var f = 5
                        while (f <= r) {
                            if (n.rem(f) == 0L || n.rem(f + 2) == 0L) return@filter false
                            f += 6
                        }
                        true
                    }
                }
            }
            .forEach { yield(it) }
    }
}

fun main() {
    primes()
        .take(20)
        .toList()
        .let { println(it) }
}