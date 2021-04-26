package pe014

import util.collatz.collatzOf

// https://projecteuler.net/problem=14
fun main() {
    // brute force is enough for ProjectEuler.
    // to boost performance we could cache results,
    // because every collatz sequence will eventually
    // hit (small) numbers that were already seen before
    (1..1_000_000)
        .mapIndexed { index, i -> Pair(index + 1, collatzOf(i.toLong()).count()) }
        .maxByOrNull { pair -> pair.second }
        .let { println(it) }
}