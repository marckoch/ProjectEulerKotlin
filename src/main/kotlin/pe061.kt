package pe061

import util.permutations.permute
import java.util.*

// https://projecteuler.net/problem=61
fun main() {
    val bases = intArrayOf(3, 4, 5, 6, 7, 8)
    solve(bases)
}

private fun solve(bases: IntArray) {
    val numbers = buildNumbersMap()

    permute(bases.toList())
        .forEach { permutation ->
            val result = processPermutation(permutation, numbers)
            if (result > 0) {
                println(result)
                return
            }
        }
}

private fun lastTwoDigits(i: Int): Int {
    return i % 100
}

private fun firstTwoDigits(i: Int): Int {
    return i / 100
}

// produces the triangular, square, pentagonal, etc numbers
private fun getNumber(base: Int, n: Int): Int {
    return when (base) {
        3 -> n * (n + 1) / 2
        4 -> n * n
        5 -> n * (3 * n - 1) / 2
        6 -> n * (2 * n - 1)
        7 -> n * (5 * n - 3) / 2
        8 -> n * (3 * n - 2)
        else -> throw IllegalStateException("wrong base $base")
    }
}

// for all bases from 3 to 8 build all 4 digit numbers
fun buildNumbersMap(): Map<Int, List<Int>> {
    return (3..8)
        .associateWith { base: Int -> buildNumbersForBase(base) }
}

// build 4 digit numbers for given base
fun buildNumbersForBase(base: Int): List<Int> {
    return (1..1200)
        .map { i -> getNumber(base, i) }
        .takeWhile { it < 10_000 }  // we only want 4 digit numbers
        .filter { it > 999 } // we only want 4 digit numbers
        .filter { lastTwoDigits(it) > 10 } // ignore because this would create a 3 digit follow up number
        .also { println(it.count()) }
}

// entry function to trigger recursion
private fun processPermutation(permutation: List<Int>, numbers: Map<Int, List<Int>>): Int {
    println("checking $permutation")

    val chain = Stack<Int>() // holds the chain of numbers during recursion/backtracking
    val level = 0 // indicates the current level of recursion (depth), will be max 6

    return recurse(permutation, level, numbers, chain)
}

private fun recurse(permutation: List<Int>, level: Int, numbers: Map<Int, List<Int>>, chain: Stack<Int>): Int {
    // we processed all bases, the chain is finished, now check if chain is closed
    if (level == permutation.size) {
        if (firstTwoDigits(chain.firstElement()) == lastTwoDigits(chain.lastElement())) {
            println(chain) // we have a winner!
            return chain.sum()
        }
        return 0
    }

    val nextBase = permutation[level]
    val numbersOfNextBase: List<Int> = numbers[nextBase]!!
    val candidates = getCandidates(numbersOfNextBase, chain)

    // normal recursion (backtracking)
    for (i in candidates) {
        // append candidate to chain ...
        chain.push(i)

        // ... see if it works ...
        val result = recurse(permutation, level + 1, numbers, chain)
        if (result > 0) { // it works!
            return result
        }

        // ... candidate was not good, so we remove it and try next on in next iteration of loop
        chain.pop()
    }
    return 0
}

private fun getCandidates(numbersOfNextBase: List<Int>, chain: Stack<Int>): List<Int> {
    return if (chain.isEmpty()) {
        // chain is empty, all numbers are candidates, no restrictions!
        numbersOfNextBase
    } else {
        // chain is NOT empty, only numbers allowed whose first 2 digits match the last 2 digits of lastNumberInChain
        val lastNumberInChain = chain.lastElement()
        val lastDigits = lastTwoDigits(lastNumberInChain)
        numbersOfNextBase.filter { firstTwoDigits(it) == lastDigits }
    }
}



