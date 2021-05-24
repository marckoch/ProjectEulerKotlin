package pe108

import kotlin.system.measureTimeMillis

// https://projecteuler.net/problem=108
fun main() {
//    getNumberOfSolutions(100).let { println(it) }
//    getNumberOfSolutions(180180).let { println(it) }

    val time = measureTimeMillis { solve() }
    println("took $time millis")
}

// brute force but SLOW ! (2,5 min)
fun solve() {
    val naturalNumbers = generateSequence(1L) { it + 1 }
    naturalNumbers.first { getNumberOfSolutions(it) > 1000 }.let { println(it) }
}

fun getNumberOfSolutions(n: Long): Long {
    println("checking n=$n")
    var solutions = 0L
    for (a in 1..n) {
        if (n * n % a == 0L) {
//            val x = n + a
//            val b = n * n / a
//            val y = n + b
//            println("  $n a=$a b=$b x=$x y=$y  1/$x+1/$y=1/$n")
            solutions++
        }
    }
    //println("         n=$n has $solutions solutions")
    return solutions
}