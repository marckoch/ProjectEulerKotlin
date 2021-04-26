import java.math.BigInteger

// https://projecteuler.net/problem=55
fun main() {
    val limit = 10_000L

    (1L..limit)
        .count { !isLychrelNumber(it) }
        .let { println(it) }
}

fun isLychrelNumber(n: Long): Boolean {
    var newNumber = BigInteger.valueOf(n)
    for (i in 1..50) {      // max number of iteration is 50 as stated in problem
        newNumber += getReverse(newNumber)
        if (isPalindrome(newNumber))
            return true
    }
    return false
}

fun getReverse(n: BigInteger): BigInteger {
    return n.toString().reversed().toBigInteger()
}

fun isPalindrome(n: BigInteger): Boolean {
    return n.toString() == n.toString().reversed()
}