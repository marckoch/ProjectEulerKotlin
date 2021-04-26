package pe002

import util.fibonacci.fibonacci

// https://projecteuler.net/problem=2
fun main() {
    println(sumOfEvenFibonaccisUpTo(4_000_000))

    // much better solution: setting limit, filtering and summing is all separated
    fibonacci()
        .takeWhile { it < 4_000_000 }
        .filter(isEven)
        .sum()
        .let { println(it) }
}

val isEven = { x: Long -> x % 2L == 0L }

// old school:
// disadvantage: setting the limit, filtering (only even) and operation (summing them up) is all intertwined
fun sumOfEvenFibonaccisUpTo(limit: Long): Long {
    var f1: Long = 1
    var f0: Long = 0
    var fn = f1 + f0
    var sum: Long = 0
    while (fn < limit) {
        if (fn % 2 == 0L) { // only count even fibonacci numbers in the sum!
            sum += fn
        }
        f0 = f1
        f1 = fn
        fn = f1 + f0
    }
    return sum
}
