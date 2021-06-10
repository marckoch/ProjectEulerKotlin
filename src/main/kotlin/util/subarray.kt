package util

// https://en.wikipedia.org/wiki/Maximum_subarray_problem
// Kadane's Algorithm
fun findMaximumSubsequenceSum(numbers: List<Long>): Long {
    var bestSum = 0L
    var curSum = 0L
    for (n in numbers) {
        curSum = maxOf(0, curSum + n)
        bestSum = maxOf(bestSum, curSum)
    }
    return bestSum
}

fun main() {
    val numbers = listOf(-2L, 1, -3, 4, -1, 2, 1, -5, 4)

    println(findMaximumSubsequenceSum(numbers))
}