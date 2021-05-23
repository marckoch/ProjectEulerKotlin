package pe103

import util.sets.buildSubSets
import kotlin.math.pow

// https://projecteuler.net/problem=103
fun main() {
    val base5 = intArrayOf(6, 9, 10, 12, 14)
    solve(base5)

    val base6 = intArrayOf(11, 17, 20, 22, 23, 24)
    solve(base6)

    val base7 = intArrayOf(22, 31, 37, 40, 42, 43, 44)
    solve(base7)
}

fun solve(base: IntArray) {
    val length = base.size

    // we vary every number x in base from x-min..x..x+max
    val max = 2
    val min = -2
    // this will give us diff new numbers
    val diff = max - min + 1

    // this array represents the variations we apply to the base vector
    // e.g. one array might be (1, 0, -1, 2, 2)
    // these numbers will be added to base array giving a new array (x1+1, x2, x3-1, x4+2, x5+2)
    var perturbation = IntArray(base.size) { min }

    val noOfCombinations = diff.toDouble().pow(length.toDouble()).toInt()
    println("we have $noOfCombinations combinations ($diff ^ $length)")

    var minimumSum = Integer.MAX_VALUE

    for (i in 0..noOfCombinations) {
        val numbers = base.zip(perturbation) { a, b -> a + b }
        // println("${base.toList()} + ${perturbation.contentToString()} = $numbers")

        val sumOfSet = numbers.sum()

        // we have found a new minimal sum
        // now check if rule 1 and 2 are satisfied
        if (sumOfSet < minimumSum) {
            if (verifyRule1(numbers) && verifyRule2(numbers)) {
                minimumSum = sumOfSet
                println("*** ${numbers.joinToString("")} has sum $sumOfSet")
            }
        }

        perturbation = updatePerturbationVector(perturbation, min, max)
    }

    println(minimumSum)
}

// S(B) ≠ S(C); that is, sums of subsets cannot be equal
// we build all sub sets and each sum for each subset
// both list must have equal size
fun verifyRule1(numbers: List<Int>): Boolean {
    val listOfSubSets = buildSubSets(numbers)
    val distinctSums = listOfSubSets.map { it.sum() }.distinct()
    return listOfSubSets.size == distinctSums.size
}

// If B contains more elements than C then S(B) > S(C).
// in the sorted list the sum of the n+1 first numbers must be greater than
// the sum of the last n numbers
fun verifyRule2(numbers: List<Int>): Boolean {
    val sortedNumbers = numbers.sorted()
    for (n in 1..sortedNumbers.size / 2) {
        val leftSum = sortedNumbers.take(n + 1).sum()
        val rightSum = sortedNumbers.takeLast(n).sum()
        if (leftSum <= rightSum) return false
    }
    return true
}

private fun updatePerturbationVector(a: IntArray, min: Int, max: Int): IntArray {
    //println(" before ${a.contentToString()}");
    for (i in a.indices.reversed()) {
        if (a[i] != max) {
            for (j in i + 1 until a.size) {
                a[j] = min
            }
            a[i]++
            //println(" middle  " + a.contentToString());
            return a
        }
    }
    // println(" after  ${a.contentToString()}");
    return a
}