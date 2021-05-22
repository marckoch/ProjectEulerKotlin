package pe102

import kotlin.math.abs

// https://projecteuler.net/problem=102
fun main() {
    val fileContent = object {}.javaClass.getResource("pe102_triangles.txt")!!.readText()

    fileContent.lines()
        .count { line ->
            val points = extractPoints(line)
            doesContainPoint(points, Point(0, 0))
        }.let { println("lines matching: $it") }
}

// check if triangle that is formed by the three points in the Triple
// contains the given point `pointX`
// we do this by calculating the area of the given triangle and
// compare it with the sum of the areas of the three triangles that are formed
// by each two points and the `pointX`
// if they are equal, than the three points contain the `pointX`
fun doesContainPoint(points: Triple<Point, Point, Point>, pointX: Point): Boolean {
    val pointA = points.first
    val pointB = points.second
    val pointC = points.third

    val areaOfBigTriangle = calculateDoubleTriangleArea(pointA, pointB, pointC)

    val areaOfTriangle1 = calculateDoubleTriangleArea(pointA, pointB, pointX)
    val areaOfTriangle2 = calculateDoubleTriangleArea(pointB, pointC, pointX)
    val areaOfTriangle3 = calculateDoubleTriangleArea(pointC, pointA, pointX)

    return areaOfTriangle1 + areaOfTriangle2 + areaOfTriangle3 == areaOfBigTriangle
}

fun extractPoints(line: String): Triple<Point, Point, Point> {
    val numbers = line.split(",")

    return Triple(
        Point(numbers[0], numbers[1]),
        Point(numbers[2], numbers[3]),
        Point(numbers[4], numbers[5])
    )
}

// https://www.mathopenref.com/coordtrianglearea.html
// return 2 * area to keep integers and avoid floating point arithmetic
private fun calculateDoubleTriangleArea(pointA: Point, pointB: Point, pointC: Point): Int {
    return abs(
        pointA.x * (pointB.y - pointC.y)
                + pointB.x * (pointC.y - pointA.y)
                + pointC.x * (pointA.y - pointB.y)
    )
}

data class Point(val x: Int, val y: Int) {
    constructor(x: String, y: String) : this(x.toInt(), y.toInt())
}