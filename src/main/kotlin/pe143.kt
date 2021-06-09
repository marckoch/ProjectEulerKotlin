package pe143

import kotlin.math.sqrt
import kotlin.system.measureTimeMillis

// https://projecteuler.net/problem=143
// https://en.wikipedia.org/wiki/Fermat_point
// http://www.geocities.ws/fredlb37/node9.html
// https://www.wikiwand.com/en/Triangle_center#/1st_isogonic_center
fun main() {
    measureTimeMillis { solve() }.let { println(it) }
}

// simple brute force
fun solve() {
    val sums = HashSet<Long>()

    val limit = 120_000L

    // a^2 = q^2 + r^2 + q*r
    // b^2 = p^2 + q^2 + p*q
    // c^2 = p^2 + r^2 + p*r
    for (p in 1L..limit) {
        for (r in 1L..p) {
            if (p + r > limit) break

            val cSquared = p * p + r * r + p * r
            if (isSquare(cSquared)) {
                for (q in 1L..r) {
                    if (p + q + r > limit) break
                    val aSquared = q * q + r * r + q * r
                    val bSquared = p * p + q * q + p * q

                    if (isSquare(aSquared) && isSquare(bSquared)) {
//                        println("a^2=$aSquared b^2=$bSquared c^2=$cSquared")
                        println("a=${sqrt(aSquared)} b=${sqrt(bSquared)} c=${sqrt(cSquared)}")
                        println("  p=$p q=$q r=$r,  p + q + r = ${p + q + r}")
                        sums.add(p + q + r)
                    }
                }
            }
        }
    }
    println(sums.sorted())
    println(sums.sum())
}

fun sqrt(n: Long): Long {
    return sqrt(n.toDouble()).toLong()
}

fun isSquare(n: Long): Boolean {
    val s = sqrt(n.toDouble())
    return n == s.toLong() * s.toLong()
}