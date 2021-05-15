package pe088

import kotlin.math.min

// https://projecteuler.net/problem=88
// https://github.com/nayuki/Project-Euler-solutions/blob/master/java/p088.java
fun main() {
    example()

    solve(12_000)
}

fun solve(n: Int) {
    val minSumProduct = IntArray(n) { Integer.MAX_VALUE }

    for (i in 2..n * 2) {
        factorize(i, i, i, 0, 0, minSumProduct)
    }

    minSumProduct
        .drop(2)  // 2 <= k, see problem statement
        .distinct()
        .sum()
        .let { println(it) }
}

/*
 * minSumProduct[k] is the smallest positive integer that can be written as both a sum and a product of the same collection of k positive integers.
 * For example, minSumProduct[3] = 6 because 6 == 1 + 2 + 3 == 1 * 2 * 3, and this is the minimum possible number for 3 terms.
 *
 * For all k >= 2:
 * - minSumProduct[k] > k because 1 + ... + 1 (with k terms) = k, which is the minimum sum of k positive integers,
 *   but the product is 1 which is unequal to k, so k is not a valid solution.
 * - minSumProduct[k] <= 2k because 1 + ... + 1 + 2 + k (with k terms in total) = (k - 2) + 2 + k = 2k. The product is 2k, which equals the sum.
 *   Since this is one achievable solution, the minimum solution must be no larger than this.
 * - Aside: minSumProduct[k] is not a prime number. Suppose minSumProduct[k] = p, where p is prime. Then p can only be factorized as p, p * 1, p * 1 * 1, etc.
 *   So whenever the factorization has more than one term, the sum exceeds p, which makes it unequal to the product.
 *
 * Therefore we need to consider all numbers from 2 to LIMIT*2 and factorize them in all possible ways to find all the relevant solutions.
 */


/*
 * Calculates all factorizations of the integer n >= 2 and updates smaller solutions into minSumProduct.
 */
private fun factorize(n: Int, remain: Int, maxFactor: Int, sum: Int, terms: Int, minSumProduct: IntArray) {
    if (remain == 1) {
        val newTerms = terms + n - sum
        if (newTerms < minSumProduct.size && n < minSumProduct[newTerms]) {
            minSumProduct[newTerms] = n
        }
    } else {
        // Note: maxFactor <= remain
        for (i in 2..maxFactor) {
            if (remain % i == 0) {
                factorize(n, remain / i, min(i, maxFactor), sum + i, terms + 1, minSumProduct)
            }
        }
    }
}


fun example() {
/*
 * For example, 12 can be factorized as follows - and duplicates are eliminated by finding only non-increasing sequences of factors:
 * - 12 = 12. (1 term)
 * - 12 = 6 * 2 * 1 * 1 * 1 * 1 = 6 + 2 + 1 + 1 + 1 + 1. (6 terms)
 * - 12 = 4 * 3 * 1 * 1 * 1 * 1 * 1 = 4 + 3 + 1 + 1 + 1 + 1 + 1. (7 terms)
 * - 12 = 3 * 2 * 2 * 1 * 1 * 1 * 1 * 1 = 3 + 2 + 2 + 1 + 1 + 1 + 1 + 1. (8 terms)
 */
    val array = IntArray(20) { Integer.MAX_VALUE } // we look for MINIMUM, so we init array with MAX_VALUE
    factorize(12, 12, 12, 0, 0, array)
    array.withIndex().filter { it.value < Integer.MAX_VALUE }.forEach { println(it) }
}