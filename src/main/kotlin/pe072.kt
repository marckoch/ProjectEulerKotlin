package pe072

import util.totient.computeTotient

// https://projecteuler.net/problem=72
fun main() {
    val N = 1000000
    val phi = computeTotient(N)
    val F = buildFareySequence(N, phi)

    println(F[N] + 1)
}

// https://en.wikipedia.org/wiki/Farey_sequence#Sequence_length_and_index_of_a_fraction
fun buildFareySequence(N: Int, phi: List<Int>): List<Long> {
    val F = LongArray(N + 1)
    F[1] = 2
    for (i in 2 until N) {
        F[i + 1] = F[i] + phi[i + 1]
    }
    return F.toList()
}