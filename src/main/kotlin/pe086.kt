package pe086

import kotlin.math.floor
import kotlin.math.sqrt

// https://projecteuler.net/problem=86
fun main() {
    solveBruteForce(2_000).let { println(it) }

    solveBruteForce(1_000_000).let { println(it) }
}

// stupid brute force approach, just count combinations, super slow but correct
// three loops --> bad!
fun solveBruteForce(target: Int): Int {
    var matches = 0
    // we assume x is longest side, y always < x and z < y (because we ignore rotation)
    var x = 0

    while (matches < target) {
        x++
        for (y in 1..x) {
            for (z in 1..y) {
                // shortest route is reached if we square the sum of the two lowest
                val sum = x * x + (y + z) * (y + z)

                val root = sqrt(sum.toDouble())
                if (root == floor(root)) {
                    //System.err.println("" + x + "^2 + (" + y + "+" + z + ")^2 == " + (int) root + "^2  == " + sum);
                    matches++
                }
            }
        }
    }
    return x
}