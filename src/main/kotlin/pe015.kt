import java.math.BigInteger

// https://projecteuler.net/problem=15
fun main() {
    // this is simple combinatorics
    // solution is
    // (20 + 20)!
    // ----------
    // 20! * 20!
    //
    // which can be reduced by canceling 20! on top and bottom:
    //
    // (40 * 30 * ... * 22 * 21)
    // -------------------------
    //       20!

    val top = (21..40)
        .map { BigInteger.valueOf(it.toLong()) }
        .reduce { acc, i -> acc * i }
    val bottom = (1..20)
        .map { BigInteger.valueOf(it.toLong()) }
        .reduce { acc, i -> acc * i }

    println(top / bottom)
}