package pe065

import util.convergents.convergentOfE
import util.digits.digitSum

// https://projecteuler.net/problem=65
fun main() {
    digitSum(convergentOfE
        .take(100)
        .last()
        .top)
        .also { println(it) }
}
