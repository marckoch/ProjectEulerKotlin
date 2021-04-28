import util.combinations.combinations
import util.memoize.memoize
import util.primes.checkIfPrime
import util.primes.primes

// https://projecteuler.net/problem=60
// TODO: too slow for 5 primes, work in progress
fun main() {
    println(checkPrimePair(Pair(3, 7)))
    println(checkPrimePair(Pair(3, 109)))

    println(checkIfAllPairsFormNewPrimes(listOf(3, 7, 109)))
    println(checkIfAllPairsFormNewPrimes(listOf(3, 7, 109, 673)))

    // idea: generate a list of primes, build combinations of `groupSize` primes out of it.
    // for each group of primes build all pairs of two and check each pair of p1 and p2.
    // if both combinations of p1p2 and p2p1 are primes
    val groupSize = 3

    val primes = primes().take(200).toList()

    combinations(primes, groupSize)
        .filter { checkIfAllPairsFormNewPrimes(it) }
        .forEach { println("$it sum=${it.sum()}") }
//        .minByOrNull { list -> list.sum() }
//        ?.let { println(it) }

}

fun checkIfAllPairsFormNewPrimes(p: List<Long>): Boolean {
    return combinations(p, 2)
        .map { list -> Pair(list[0], list[1]) }
        .all { checkPrimePairMem(it) }
}

// check if both primes of the `Pair` form two new primes by concatenating: p1p2 and p2p1
fun checkPrimePair(p: Pair<Long, Long>): Boolean {
    val p1 = p.first
    val p2 = p.second

//    val p1p2 = p1.toString() + p2.toString()
//    val p2p1 = p2.toString() + p1.toString()
    val p1p2 = concat(p1, p2)
    val p2p1 = concat(p2, p1)

    return checkPrimeMem(p1p2) && checkPrimeMem(p2p1)
}

// concat two numbers
// concat(12, 34) => 1234
fun concat(a: Long, b: Long): Long {
    var shift = 10
    while (shift <= b) {
        shift *= 10
    }
    return a * shift + b
}

val checkPrimePairMem = { p: Pair<Long, Long> -> checkPrimePair(p) }.memoize()

val checkPrimeMem = { l: Long -> checkIfPrime(l) }.memoize()