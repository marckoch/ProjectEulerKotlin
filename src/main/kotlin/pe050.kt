import util.primes

// https://projecteuler.net/problem=50
fun main() {
    val limit = 1_000_000
    val primesBelowLimit = primes().takeWhile { it < limit }.toList()
    println(primesBelowLimit.size)

    var maxPrime: Long = 0
    var noOfSummandsOfMaxPrime = 0
    for (i in primesBelowLimit.indices) {
        val p1 = primesBelowLimit[i]

        // no need to check the rest we cannot find a new maximum
        // because all following primes are larger than p and we would
        // need at least noOfSummandsOfMaxPrime of them
        if (p1 + noOfSummandsOfMaxPrime * p1 > limit) break

        var sum = p1
        var noOfSummands = 1
        for (j in i + 1 until primesBelowLimit.size) {
            val p2 = primesBelowLimit[j]
            sum += p2

            if (sum > limit) break

            noOfSummands++
            if (primesBelowLimit.contains(sum)) {
                if (noOfSummands > noOfSummandsOfMaxPrime) {
                    maxPrime = sum
                    noOfSummandsOfMaxPrime = noOfSummands
                    println("  found new max sum $maxPrime from $p1 to $p2 count=$noOfSummandsOfMaxPrime")
                }
            }
        }
    }
    println("$maxPrime $noOfSummandsOfMaxPrime")
}