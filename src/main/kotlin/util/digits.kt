package util.digits

import pe112.countDigits
import java.math.BigInteger

fun digitSum(n: Long) = digitSum(BigInteger.valueOf(n))

fun digitSum(n: BigInteger) = n.toString()
    .chars()
    .map { Character.getNumericValue(it) }
    .sum()

// we split a Long into its digits
// e.g. 3241L -> (3,2,4,1)
fun digits(number: Long): IntArray {
    var n = number
    val digitCount = countDigits(n)
    val result = IntArray(digitCount)
    var index = 0
    while (n > 0) {
        val lastDigit = n.toInt() % 10
        result[digitCount - 1 - index++] = lastDigit
        n /= 10
    }
    return result
}