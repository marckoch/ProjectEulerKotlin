package util.laggedFibonacci

fun laggedFibonacci(k: Int): Long {
//    For 1 ≤ k ≤ 55, sk = [100003 − 200003k + 300007k3] (modulo 1000000) − 500000.
//    For 56 ≤ k ≤ 4000000, sk = [sk−24 + sk−55 + 1000000] (modulo 1000000) − 500000.
    return when {
        k in 1..55 -> ((100_003L - 200_003L * k + 300_007L * k * k * k) % 1_000_000L) - 500_000L
        k >= 56 -> ((laggedFibonacci(k - 24) + laggedFibonacci(k - 55) + 1_000_000L) % 1_000_000L) - 500_000L
        else -> 0
    }
}

fun buildLaggedFibonacciArray(n: Int): LongArray {
    val a = LongArray(n)

    for (k in 1..n) {
        a[k-1] = when {
            k in 1..55 -> ((100_003L - 200_003L * k + 300_007L * k * k * k) % 1_000_000L) - 500_000L
            k >= 56 -> ((a[k - 24 -1 ] + a[k - 55 -1 ] + 1_000_000L) % 1_000_000L) - 500_000L
            else -> 0
        }
    }

    return a
}

fun main() {
    (1..200).map { laggedFibonacci(it) }.let { println(it) }
    laggedFibonacci(10).let { println(it) }
    laggedFibonacci(100).let { println(it) }

    buildLaggedFibonacciArray(2000*2000).let { println(it.contentToString()) }
    buildLaggedFibonacciArray(10)[9].let { println(it) }
    buildLaggedFibonacciArray(100)[99].let { println(it) }

    buildLaggedFibonacciArray(2000).sum().let { println(it) }
}