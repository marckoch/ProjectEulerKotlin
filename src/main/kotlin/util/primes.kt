package util.primes

import java.util.*
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

// build boolean array indicating that x is prime if arr[x] == true
// useful when we want to check lots of numbers if they are prime or not
// https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
fun isPrime(max: Int): BooleanArray {
    val isPrime = BooleanArray(max + 1)
    Arrays.fill(isPrime, true)
    isPrime[0] = false
    isPrime[1] = false
    var p = 2
    while (p * p <= max) {
        // p is a prime?
        if (isPrime[p]) {
            // then all multiples of p have p as one prime factor and are NO prime
            var i = p * 2
            while (i <= max) {
                isPrime[i] = false
                i += p
            }
        }
        p++
    }
    return isPrime
}

// basic prime check
fun checkIfPrime(x: Long): Boolean {
    if (x % 2L == 0L) return false
    var i = 3
    while (i * i <= x) {
        if (x % i == 0L) return false
        i += 2
    }
    return true
}

fun main() {
    primes()
        .take(20)
        .toList()
        .let { println(it) }

    isPrime(50)
        .withIndex()
        .filter { indexedValue -> indexedValue.value }
        .map { indexedValue -> indexedValue.index }
        .toList()
        .let { println(it) }
}