package util

import java.math.BigInteger

fun digitSum(n: Long) = digitSum(BigInteger.valueOf(n))

fun digitSum(n: BigInteger) = n.toString()
    .chars()
    .map { Character.getNumericValue(it) }
    .sum()