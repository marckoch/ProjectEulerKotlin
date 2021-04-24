import java.math.BigInteger

// https://projecteuler.net/problem=48
fun main() {
    (1..1000)
        .map { i -> selfExp(i) }
        .reduce { acc, i -> acc.add(i) }
        .toString()
        // .also { println(it.length) }   -> result has 3001 digits!
        .takeLast(10)
        .let { println(it) }
}

// calculate x ^ x
fun selfExp(x: Int): BigInteger {
    val bigI = BigInteger.valueOf(x.toLong())
    return bigI.pow(bigI.toInt())
}