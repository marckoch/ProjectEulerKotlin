package pe085

import kotlin.math.abs
import kotlin.math.sqrt

// https://projecteuler.net/problem=85
// https://mathworld.wolfram.com/RectangleTiling.html
fun main() {
    println(solve(20))

    println(solve(2_000_000))
}

private fun generateTriangle(n: Int): Int {
    return n * (n + 1) / 2
}

fun solve(target: Int): Int {
    var closestNoOfRectangles = 0
    var areaOfMin = 0

    // in case of a rectangle of X * 1 the triangle will be about X^2,
    // so the other way round if the max no of rectangles is given
    // we can use sqrt(X) as a limit for x
    val limit = sqrt(target.toDouble()).toInt() + 1
    for (x in 1..limit) {
        var y = x
        var noOfRectangles: Int
        do {
            noOfRectangles = generateTriangle(x) * generateTriangle(y)
            if (abs(noOfRectangles - target) < abs(closestNoOfRectangles - target)) {
                closestNoOfRectangles = noOfRectangles
                areaOfMin = x * y
                // System.err.println("setting new optimum to ${abs(closestNoOfRectangles - target)} for $x $y noOfRectangles $noOfRectangles area $area");
            }

            y++
        } while (noOfRectangles < target)
        if (y == x + 1) break
    }
    return areaOfMin
}