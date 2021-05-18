package pe094

import kotlin.math.sqrt

// https://projecteuler.net/problem=94
fun main() {
    // println(solve(20))
    println(solve(1_000_000_000L))
}

fun solve(maxPerimeterSum: Long): Long {
    var perimeterSum: Long = 0

    val maxBase = maxPerimeterSum / 3

    // we create a triangle with certain base
    for (base in 2L until maxBase step 2) {
        val halfBase = base.toDouble() / 2

        // we try a short leg that is base - 1
        val shortLeg = (base - 1).toDouble()
        val perimeterShort = base + 2 * shortLeg
        if (perimeterShort + perimeterSum > maxPerimeterSum) return perimeterSum
        if (isAreaInteger(halfBase, shortLeg)) {
            perimeterSum += perimeterShort.toLong()
        }

        // we try a long leg that is base + 1
        val longLeg = (base + 1).toDouble()
        val perimeterLong = base + 2 * longLeg
        if (perimeterLong + perimeterSum > maxPerimeterSum) return perimeterSum
        if (isAreaInteger(halfBase, longLeg)) {
            perimeterSum += perimeterLong.toLong()
        }
    }
    return perimeterSum
}

// area is integer, when height is perfect square,
// because than halfBase (which is integer) and leg form a pythagorean triplet
private fun isAreaInteger(halfBase: Double, leg: Double): Boolean {
    val heightSq = leg * leg - halfBase * halfBase
    if (heightSq > 0 && isPerfectSquare(heightSq)) {
        debugPrint(halfBase, leg, heightSq)
        return true
    }
    return false
}

private fun debugPrint(halfBase: Double, leg: Double, heightSq: Double) {
    val perimeter = 2 * halfBase + 2 * leg
    val area = halfBase * sqrt(heightSq)
    println("match: base=${(2 * halfBase).toLong()} leg=${leg.toLong()} height=${sqrt(heightSq).toLong()} perimeter=${perimeter.toLong()} area=${area.toLong()}")
}

private fun isPerfectSquare(input: Double): Boolean {
    val closestRoot = sqrt(input).toLong()
    return input == (closestRoot * closestRoot).toDouble()
}