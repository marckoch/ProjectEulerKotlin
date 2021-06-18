package pe206

import util.digits.digits
import kotlin.math.roundToLong
import kotlin.math.sqrt

fun main() {
    solveProjectEuler()
}

// 1002290129674248100
// 1_2_3_4_5_6_7_8_9_0
fun solveProjectEuler() {
    for (n in 0 until 1_000_000_000L step 10) { // square ends with 0, so n can only be multiple of 10
//        println("$n: ${weave(n)}")
        val newNumber = weave(n)
        val root = sqrt(newNumber.toDouble()).roundToLong()
        if (root * root == newNumber) {
            println("winner=$root $newNumber root^2=${root * root}")
        }
    }
}

fun weave(input: Long): Long {
    val digits = digits(input)
//    println(digits.contentToString())
    val newNumber = intArrayOf(1, 0, 2, 0, 3, 0, 4, 0, 5, 0, 6, 0, 7, 0, 8, 0, 9, 0, 0)
//    println(newNumber.contentToString())
    var i = digits.size - 1
    var j = newNumber.size - 2
    while (i >= 0) {
        newNumber[j] = digits[i]
        i--
        j -= 2
    }
    return toLong(newNumber)
}

private fun toLong(digits: IntArray): Long {
    var result = 0L
    for (i in digits.indices) {
        result += digits[i]
        if (i < digits.size - 1) result *= 10
    }
    return result
}