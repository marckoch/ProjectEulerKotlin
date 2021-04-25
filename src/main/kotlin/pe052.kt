// https://projecteuler.net/problem=52
fun main() {
    val numberOfMultiples = 6

    val naturalNumbers = generateSequence(1L) { it + 1 }
    naturalNumbers
        .map { createMultiples(it, numberOfMultiples) }
        .first { multiples ->
            multiples.all { m ->
                isPermutation(multiples[0], m)  // all multiples are permutation of each other
            }
        }
        .let { println(it.toList()) }
}

// create multiples of `n` from 2 * `n` up to `size` * `n`
fun createMultiples(n: Long, size: Int): List<Long> {
    return (1..size)
        .map { n * it }
}

fun isPermutation(p1: Long, p2: Long): Boolean {
    return p1.toString().toCharArray().sorted() == p2.toString().toCharArray().sorted()
}