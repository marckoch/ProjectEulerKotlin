import util.cartesianProduct
import util.isPrime
import kotlin.math.abs

// https://projecteuler.net/problem=27
fun main() {
    // just brute force it
    cartesianProduct((-1000..1000).toList(), (-1000..1000).toList())
        .maxByOrNull { pair -> getMaxLength(pair.first, pair.second) }
        .let { pair ->
            if (pair != null) {
                println(pair)
                println(pair.first * pair.second)
            }
        }
}

private const val LIMIT = 100000 // found by trial and error
private val PRIME = isPrime(LIMIT)

fun calc(a: Int, b: Int, n: Int): Int {
    return (n + a) * n + b
}

fun getMaxLength(a: Int, b: Int): Int {
    var n = 0
    do {
        val p = calc(a, b, n)
        n++
    } while (PRIME[abs(p)])
    return n
}

// old way from my java solutions
//fun check() {
//    val n = 1000
//    var maxLength = 0
//    var maxA = 0
//    var maxB = 0
//    for (a in -n..n) {
//        for (b in -n..n) {
//            val len = getMaxLength(a, b)
//            if (len > maxLength) {
//                maxLength = len
//                maxA = a
//                maxB = b
//            }
//        }
//    }
//    println("a=$maxA, b=$maxB, length=$maxLength")
//    println("${maxA * maxB}")
//}