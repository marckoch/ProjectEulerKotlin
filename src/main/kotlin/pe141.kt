package pe141

import util.gcd.gcd
import java.math.BigInteger
import kotlin.math.sqrt
import kotlin.system.measureTimeMillis

// https://projecteuler.net/problem=141
fun main() {
    val limit = 1e12.toLong()
//    val limit = 10_000_000_000L

    measureTimeMillis { solveBI(limit) }.let { println("$it millisecs (BigInteger)") }
    measureTimeMillis { solve(limit) }.let { println("$it millisecs") }
    measureTimeMillis { slow(limit) }.let { println("$it millisecs") }
}

// BigInteger version, used to find bug in my Long version
fun solveBI(limit: Long) {
    val bigLimit = BigInteger.valueOf(limit)
    val progressiveSquares: MutableList<BigInteger> = ArrayList()

    for (a in 2L..9999) {
        for (b in 1L until a) {
            val bSquared = BigInteger.valueOf(b).pow(2)
            val x = BigInteger.valueOf(a).pow(3).multiply(bSquared) + bSquared
            if (x >= bigLimit) break
            if (gcd(a, b) > 1) continue
            var c = 1L
            while (true) {
                val bigA = BigInteger.valueOf(a)
                val bigB = BigInteger.valueOf(b)
                val bigC = BigInteger.valueOf(c)
                val nBig = bigA.pow(3) * bigB * bigC.pow(2) + bigC * bigB.pow(2)
                if (nBig >= bigLimit) break
                if (isSquare(nBig) && !progressiveSquares.contains(nBig)) {
                    progressiveSquares.add(nBig)
//                    println("a=$a b=$b c=$c n=$nBig  / ${bigA * bigB * bigC} = ${bigC * bigA * bigA} rem ${bigC * bigB * bigB}")
                }
                c++
            }
        }
    }

    progressiveSquares.reduce { acc, bigInteger -> acc + bigInteger }.let { println(it) }
}

// https://www.mathblog.dk/project-euler-141investigating-progressive-numbers-n-which-are-also-square/
fun solve(limit: Long) {
    val progressiveSquares: MutableList<Long> = ArrayList()

    for (a in 2L..9999) {
        for (b in 1L until a) {
            val bSquared = b * b
            if (a * a * a * bSquared + bSquared >= limit) break
            if (gcd(a, b) > 1) continue
            var c = 1L
            while (true) {
                val n = a * a * a * b * c * c + c * b * b
                if (n >= limit) break
                if (isSquare(n) && !progressiveSquares.contains(n)) {
                    progressiveSquares.add(n)
//                    println("a=$a b=$b c=$c n=$n  / ${a * b * c} = ${c * a * a} rem ${c * b * b}")
                }
                c++
            }
        }
    }

    progressiveSquares.sum().let { println(it) }
}

// too slow
fun slow(limit: Long) {
    var sum = 0L
    for (i in 1L until sqrt(limit.toDouble()).toLong()) {
        val n = i * i
        val sqrtN = sqrt(n.toDouble()).toInt()
        for (d in 1L until sqrtN) {
            val q = n / d
            val r = n % d
            // println("$n / $d = $q rem $r")
            if (q == 0L || r == 0L) continue
            if (q.toDouble() / d == d.toDouble() / r) {
                println("match: $i^2: $n / $d = $q rem $r")
                sum += n
            }
        }
    }
    println(sum)
}

fun isSquare(n: Long): Boolean {
    val s = sqrt(n.toDouble())
    return n == s.toLong() * s.toLong()
}

fun isSquare(n: BigInteger): Boolean {
    val nSqrt = n.sqrt()
    return nSqrt * nSqrt == n
}