package pe078

import java.math.BigInteger
import kotlin.math.pow
import kotlin.system.measureTimeMillis

// https://projecteuler.net/problem=78
// https://en.wikipedia.org/wiki/Partition_(number_theory)
// https://en.wikipedia.org/wiki/Pentagonal_number_theorem#Relation_with_partitions
fun main() {
    val s1 = measureTimeMillis { SolutionWithBigInteger().solveProjectEuler().let { println(it) } }
    val s2 = measureTimeMillis { SolutionWithLongAndModulo().solveProjectEuler().let { println(it) } }
    println("$s1 $s2")
}

// because we what to find a solution that is divisible by 10^6 we calculate everything mod 10^6
// and find the first number that is 0
class SolutionWithLongAndModulo {
    private val MOD = 10.0.pow(6).toInt()

    fun solveProjectEuler(): Int {
        val partitions = buildPartitionArray(60_000)
        //partitions.forEachIndexed { index, bigInteger -> println("$index $bigInteger") }
        return partitions.indexOfFirst { l -> l == 0L }
    }

    private fun buildPartitionArray(limit: Int): LongArray {
        val partition = LongArray(limit)
        partition[0] = 1L // base case
        for (n in 1 until limit) {
            partition[n] = getPartition(n, partition)
        }
        //System.err.println(partition.contentToString())
        return partition
    }

    private fun getPartition(n: Int, partition: LongArray): Long {
        var sum = 0L

        // recurrence:
        // p(n) = SUM k  (-1) ^ (k-1) * p(n - pentagonal(k))
        for (i in partition.indices) {
            // generate alternating k: 0, +1, -1, +2, -2, ...
            var k = 1 + i / 2
            if (i % 2 == 1) {
                k = -k
            }
            val pent_k: Int = pentagonal(k)
            //System.err.printf("n=$n  i=$i k=$k pentagonal($k)=$pent_k\n")

            // pentagonal(n - pent_k) would get negative index
            if (pent_k > n) {
                break
            }

            // (-1) ^ (k-1) --> +1 for odd k, -1 for even k
            if (k % 2 == 0) {
                sum -= partition[n - pent_k] // p(n - pentagonal(k))
            } else {
                sum += partition[n - pent_k] // p(n - pentagonal(k))
            }

            // due to sum -= ... for even k sum could get negative
            if (sum < 0) sum += MOD.toLong()
            sum %= MOD
        }
        return sum
    }
}

// this solutions shows how big these numbers are, but it is very slow (factor 10 slower)
class SolutionWithBigInteger {
    fun solveProjectEuler(): Int {
        val partitions: Array<BigInteger> = buildPartitionArray(60_000)
        //partitions.forEachIndexed { index, bigInteger -> println("$index $bigInteger") }
        return partitions.indexOfFirst { l -> l.mod(10.0.pow(6).toBigDecimal().toBigInteger()) == BigInteger.ZERO }
    }

    private fun buildPartitionArray(limit: Int): Array<BigInteger> {
        val partition = Array<BigInteger>(limit) { BigInteger.ZERO }
        partition[0] = BigInteger.ONE // base case
        for (n in 1 until partition.size) {
            partition[n] = getPartition(n, partition)
        }
        //System.err.println(partition.contentToString())
        return partition
    }

    private fun getPartition(n: Int, partition: Array<BigInteger>): BigInteger {
        var sum: BigInteger = BigInteger.ZERO

        // recurrence:
        // p(n) = SUM k  (-1) ^ (k-1) * p(n - pentagonal(k))
        for (i in partition.indices) {
            // generate alternating k: 0, +1, -1, +2, -2, ...
            var k = 1 + i / 2
            if (i % 2 == 1) {
                k = -k
            }
            val pent_k: Int = pentagonal(k)
            //System.err.printf("n=$n  i=$i k=$k pentagonal($k)=$pent_k\n")

            // pentagonal(n - pent_k) would get negative index
            if (pent_k > n) {
                break
            }

            // (-1) ^ (k-1) --> +1 for odd k, -1 for even k
            if (k % 2 == 0) {
                sum -= partition[n - pent_k] // p(n - pentagonal(k))
            } else {
                sum += partition[n - pent_k] // p(n - pentagonal(k))
            }
        }
        return sum
    }
}

// get k-th pentagonal number
fun pentagonal(k: Int): Int {
    return k * (3 * k - 1) / 2
}