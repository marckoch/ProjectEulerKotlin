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

// get fibonacci numbers as sequence
// 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, ...
// https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/partition.html
fun fibonacci(): Sequence<Int> {
    return generateSequence(Pair(0, 1)) { Pair(it.second, it.first + it.second) }
        .map { it.first }
}

val isEven = { x: Int -> x % 2 == 0 }

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
