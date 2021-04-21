import java.math.BigInteger

// https://projecteuler.net/problem=28
fun main() {
    val n = 1001
    val k = (n - 1) / 2
    println(getDiagSum(k))
}

private fun getDiagSum(k: Int): Int {
    // https://www.mathblog.dk/project-euler-28-sum-diagonals-spiral/
    // SUM(k) = (16*k^3 + 30k^2 + 26k )/3  +1
    val K = BigInteger.valueOf(k.toLong())
    var res = BigInteger.valueOf(16).multiply(K.pow(3))
    res = res.add(BigInteger.valueOf(30).multiply(K.pow(2)))
    res = res.add(BigInteger.valueOf(26).multiply(K))
    res = res.divide(BigInteger.valueOf(3))
    res = res.add(BigInteger.ONE)
    return res.toInt()
}

