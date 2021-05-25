package pe118

import util.permutations.permute
import util.primes.checkIfPrime2
import java.util.*

// https://projecteuler.net/problem=118
fun main() {
    val n = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
//    val n = intArrayOf(1, 2, 3, 4, 5, 6)
//    val n = intArrayOf(1, 2, 3, 4)
//    val n = intArrayOf(2, 4, 5, 7)
//    val n = intArrayOf(1, 2, 3)

    val perms = permute(n.toList()).map { list -> list.toIntArray() }
    for (perm in perms) {
        checkNumber(perm, Stack())
    }

    // println(solutions)
    println(solutions.size)
}

private val solutions: MutableSet<TreeSet<Long>> = HashSet()

fun checkNumber(nArr: IntArray, stack: Stack<Long>): Boolean {
    if (nArr.isEmpty()) return false

    for (i in 0..nArr.size) {
        val (leftArr, rightArr) = split(nArr, i)

        val leftNr = toLong(leftArr)
        if (checkIfPrime2(leftNr)) {
            stack.push(leftNr)
            if (rightArr.isEmpty()) {
                solutions.add(TreeSet(stack))
            }
            checkNumber(rightArr, stack)
            stack.pop()
        }
    }
    return false
}

private fun toLong(digits: IntArray): Long {
    var result = 0L
    for (i in digits.indices) {
        result += digits[i]
        if (i < digits.size - 1) result *= 10
    }
    return result
}

fun split(numbers: IntArray, index: Int): Pair<IntArray, IntArray> {
    val left = numbers.copyOfRange(0, index)
    val right = numbers.copyOfRange(index, numbers.size)

    // println("${numbers.contentToString()} -> ${left.contentToString()} ${right.contentToString()}");

    return Pair(left, right)
}