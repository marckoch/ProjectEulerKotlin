import java.math.BigInteger

// https://projecteuler.net/problem=16
fun main() {
    val n = BigInteger.valueOf(2).pow(1000)
    println(n)

    val digitSum = n.toString()
        .chars()
        .map { Character.getNumericValue(it) }
        .sum()
    println(digitSum)
}