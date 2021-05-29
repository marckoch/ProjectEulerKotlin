package pe125

import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt

// https://projecteuler.net/problem=125
fun main() {
    // val MAX_NUMBER = 10.0.pow(3.0)  // example from problem statement
    val MAX_NUMBER = 10.0.pow(8.0)

    val limit = sqrt(MAX_NUMBER).toInt() // edge case: only one number squared is MAX_NUMBER
    val palindromes: MutableSet<Long> = TreeSet() // some sums occur more than once!
    for (start in 1L..limit) {
        var sum = (start * start)
        for (x in start + 1..limit) {
            sum += (x * x)
            if (sum >= MAX_NUMBER) {
                break
            }
            if (isPalindrome(sum)) {
                palindromes.add(sum)
            }
        }
    }
    // println(palindromes)
    println(palindromes.sum())
}

fun isPalindrome(i: Long): Boolean {
    val s = i.toString()
    return s == s.reversed()
}