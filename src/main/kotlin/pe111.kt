package pe111

import com.github.shiguruikai.combinatoricskt.combinationsWithRepetition
import util.permutations.permute
import util.primes.checkIfPrime2
import kotlin.system.measureTimeMillis

// https://projecteuler.net/problem=111
fun main() {
    val duration = measureTimeMillis { solve() }
    println("$duration millisecs")
}

fun solve() {
//        solveNumberOfDigits(4) // example in project euler problem

//    solveNumberOfDigits(5)
//    solveNumberOfDigits(6)
//    solveNumberOfDigits(7)
//    solveNumberOfDigits(8)
     solveNumberOfDigits(9)

    // slow for 10 digits (~3 minutes) but gets the correct result :-)
//    solveNumberOfDigits(10)  // < project euler task
}

// variante 2: we generate numbers and check if they are prime
private fun solveNumberOfDigits(numberOfDigits: Int) {
    var totalSum = 0L
    for (d in 0..9) {
        for (repeat in numberOfDigits - 1 downTo 1) {
            val validprimes = solveForDigit(d, repeat, numberOfDigits)
            if (validprimes.isNotEmpty()) {
                println("$d $repeat ${validprimes.count()} \t ${validprimes.sum()} \t ${validprimes.sorted()}")
                totalSum += validprimes.sum()
                break
            }
        }
    }
    println(totalSum)
}

private fun solveForDigit(digit: Int, repeat: Int, numberOfDigits: Int): List<Long> {
    val countOccurenceOfOtherDigits = numberOfDigits - repeat

    val otherdigits = (0..9).filter { it != digit }

    // we need distinct combinations here, that means [1,3] is same as [3,1] and will only be given once as [1,3]
    // we need WITH REPETITION here !!
    // https://github.com/shiguruikai/combinatoricskt/tree/master/src/main/kotlin/com/github/shiguruikai/combinatoricskt
    val otherNumbers = otherdigits.combinationsWithRepetition(countOccurenceOfOtherDigits)

    return otherNumbers.map { o ->
        val allDigits = buildNumber(o, digit, numberOfDigits)
        val validPrimes = permute(allDigits)
            .asSequence()
                // some basic divisibility checks to avoid expensive prime check
            .filter { list -> list.first() != 0 }
            .filter { list -> list.last() % 2 != 0 }
            .filter { list -> list.sum() % 3 != 0 }
            .filter { list -> list.last() != 5 && list.last() != 0 }
            .distinct()
            .map { toLong(it) }
            .filter { checkIfPrime2(it) }
        validPrimes
    }.flatten().toList()
}

// we build a new number by adding `numberOfDigits` `digits` to `otherNumber`
// e.g. buildNumber((1,2,3), 7, 6) -> (1,2,3,7,7,7)
// we fill up (1,2,3) with 7s until the length is 6
private fun buildNumber(otherNumber: List<Int>, digit: Int, numberOfDigits: Int): List<Int> {
    val newList = mutableListOf<Int>()
    newList += otherNumber
    while (newList.size < numberOfDigits) {
        newList += digit
    }
    return newList
}

// we join the given `digits` to a Long
// e.g. (2,3,4,1) -> 2341L
private fun toLong(digits: List<Int>): Long {
    var result = 0L
    for (i in digits.indices) {
        result += digits[i]
        if (i < digits.size - 1) result *= 10
    }
    return result
}