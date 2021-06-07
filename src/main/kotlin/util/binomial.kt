package util.binomial

import java.math.BigInteger

// Returns value of Binomial Coefficient C(n, k)
fun binomial(n: Long, k: Long): BigInteger {
    var k = k
    var res = BigInteger.ONE
    val origK = k

    // Since C(n, k) = C(n, n-k)
    if (k > n - k) {
        k = n - k
    }

    // Calculate value of [n * (n-1) *---* (n-k+1)] / [k * (k-1) *----* 1]
    for (i in 0 until k) {
        res = res * BigInteger.valueOf(n - i) / BigInteger.valueOf(i + 1)
    }
    System.err.println("C($n, $origK)=$res")
    return res
}

fun binom(n: Long, k: Long): Long {
    if (k==0L) return 1
    if (n==k) return 1
    return binom(n-1, k-1) + binom(n-1, k)
}