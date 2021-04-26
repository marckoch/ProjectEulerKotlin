package pe032

import util.permutations.permute

// https://projecteuler.net/problem=32
fun main() {
    val digits = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    permute(digits)
        .map { checkIfPandigitalProduct(it) }
        .toSet()            // count only unique products!
        .also { println(it) }
        .sum()
        .let { println(it) }
}

// idea is:
// - we have a permutation of numbers from 1 to N in the list
// - every permutation is "cut" in three parts:
//   -- 0 .. limit1 is the first multiplicand n1
//   -- limit1 .. limit2 is the second multiplicand n2
//   -- limit2 .. digits.size is the resulting product p
// - we check if n1 * n2 == p
private fun checkIfPandigitalProduct(digits: List<Int>): Int {
    for (limit1 in 1 until digits.size - 2) {  // - 2 because we need at least 1 digit for n2 and 1 digit for p
        for (limit2 in limit1 + 1 until digits.size - 1) { // -1 because we need at least 1 digit for p
            val n1 = getNumber(digits, 0, limit1)
            val n2 = getNumber(digits, limit1, limit2)
            val product = getNumber(digits, limit2, digits.size)
            if (product == n1 * n2) {
                println("$n1 * $n2 = $product")
                return product
            }
        }
    }
    return 0
}

// from given list a number is created using elements "from" to "to" (excl)
// e.g. [1,2,3,4,5,6] from=2 and to=4 (excl) --> 34
fun getNumber(digits: List<Int>, from: Int, to: Int): Int {
    return digits
        .subList(from, to)
        .reduce { acc, x -> acc * 10 + x }
}