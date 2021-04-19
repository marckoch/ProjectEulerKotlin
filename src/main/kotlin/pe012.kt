import util.getDivisorsOf
import util.triangleNumbers

// https://projecteuler.net/problem=12
fun main() {
    triangleNumbers
        .first { getDivisorsOf(it).size > 500 }
        .let { println(it) }
}