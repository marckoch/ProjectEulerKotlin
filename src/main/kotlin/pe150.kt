package pe150

import kotlin.math.pow
import kotlin.system.measureTimeMillis

// https://projecteuler.net/problem=150
fun main() {
    buildExampleTriangle().forEach { println(it) }
    bruteForce(buildExampleTriangle())

    measureTimeMillis { bruteForce(buildTriangle(1000)) }.let { println("$it millisecs") }
}

fun bruteForce(triangle: List<List<Long>>) {
    var minSum = Long.MAX_VALUE
    var rowOfMin = 0
    var colOfMin = 0
    var lengthOfMin = 0

    for (row in triangle.indices) {
        for (col in triangle[row].indices) {
//            println("top of current triangle at $row/$col ${triangle[row][col]}")
            var sumOfTriangle = 0L
            for (length in 0 until triangle.size - row) {
                for (r in 0..length) {
                    sumOfTriangle += triangle[row + length][col + r]
                }
//                println(" length $length -> sum=$sumOfTriangle")
                if (sumOfTriangle < minSum) {
                    minSum = sumOfTriangle
                    rowOfMin = row
                    colOfMin = col
                    lengthOfMin = length
                }
            }
        }
    }

    println("minimum is $minSum to of triangle at $rowOfMin/$colOfMin length $lengthOfMin")
}

fun buildExampleTriangle(): List<List<Long>> {
    return listOf(
        listOf(15),
        listOf(-14, -7),
        listOf(20, -13, -5),
        listOf(-3, 8, 23, -26),
        listOf(1, -4, -5, -18, 5),
        listOf(-16, 31, 2, 9, 28, 3)
    )
}

// 1st row has 1 number, 2nd row 2, etc.
fun buildTriangle(noOfRows: Int): List<List<Long>> {
    val lcg = LinearCongruentialGenerator()
    return (0 until noOfRows).map { r ->
        (0..r).map { lcg.next() }
    }.toList()
}

class LinearCongruentialGenerator : Iterator<Long> {
    var t = 0L
    val m = 2.0.pow(20).toLong()
    val d = 2.0.pow(19).toLong()

    override fun next(): Long {
        t = (615_949L * t + 797_807L) % m
        return t - d
    }

    override fun hasNext(): Boolean {
        return true
    }
}