package pe144

import kotlin.math.abs
import kotlin.math.sqrt

// https://projecteuler.net/problem=144
fun main() {
    solve()
}

// https://www.mathblog.dk/project-euler-144-investigating-multiple-reflections-of-a-laser-beam/
fun solve() {
    var result = 0

    var A = Point(0.0, 10.1)
    var O = Point(1.4, -9.6)

    while (O.x > 0.01 || O.x < -0.01 || O.y < 0) {

        // Calculate the slope of A
        val slopeA = (O.y - A.y) / (O.x - A.x)

        // Calculate the slope of the ellipse tangent
        val slopeO = -4 * O.x / O.y

        // Calculate the slope of B
        val tanA = (slopeA - slopeO) / (1 + slopeA * slopeO)
        val slopeB = (slopeO - tanA) / (1 + tanA * slopeO)

        // calculate intercept of line B
        val interceptB = O.y - slopeB * O.x
        // println("  reflected beam: y = $slopeB * x + $interceptB")

        // solve the quadratic equation for finding
        // the intersection of B and the ellipse
        // a*x^2 + b*x + c = 0
        val a = 4 + slopeB * slopeB
        val b = 2 * slopeB * interceptB
        val c = interceptB * interceptB - 100
        val ans1 = (-b + sqrt(b * b - 4 * a * c)) / (2 * a)
        val ans2 = (-b - sqrt(b * b - 4 * a * c)) / (2 * a)
        A = Point(O.x, O.y)

        // Take the solution which is furthest from x0
        val newX = if (abs(ans1 - O.x) > abs(ans2 - O.x)) ans1 else ans2
        val newY = slopeB * newX + interceptB
        O = Point(newX, newY)
        println("next hit: $O")

        result++
    }

    println(result)
}

data class Point(val x: Double, val y: Double)