package pe020

import util.digits.digitSum
import util.factorial.factorial

// https://projecteuler.net/problem=20
fun main() {
    println(digitSum(factorial(100)))
}