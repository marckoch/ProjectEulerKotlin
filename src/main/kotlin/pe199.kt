package pe199

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.sqrt

// https://projecteuler.net/problem=199
// https://en.wikipedia.org/wiki/Descartes%27_theorem
fun main() {
    println(solveProjectEuler(10))
}

var countCircles = 0

fun area(radius: Double): Double {
    return radius * radius * Math.PI
}

fun getK(k1: Double, k2: Double, k3: Double): Pair<Double, Double> {
    // k = k1+k2+k3 ± 2 * sqrt(k1*k2 + k2*k3 + k3*k1)
    val root = sqrt(k1 * k2 + k2 * k3 + k3 * k1)
    return Pair(k1 + k2 + k3 + 2 * root, k1 + k2 + k3 - 2 * root)
}

fun getArea(k1: Double, k2: Double, k3: Double, level: Int, outer: Boolean, numberOfInitialCircles: Int): Double {
    // k4.second would describe the outer circle which we already have considered in an previous step
    val k4 = getK(k1, k2, k3).first
    val area = area(1 / k4)
    if (outer) {
        countCircles += numberOfInitialCircles
    } else {
        countCircles++
    }

    return if (level == 1) {
        area
    } else area +
            getArea(k1, k2, k4, level - 1, outer, numberOfInitialCircles) +
            getArea(k2, k3, k4, level - 1, outer, numberOfInitialCircles) +
            getArea(k3, k1, k4, level - 1, outer, numberOfInitialCircles)

    // this will be three sub tasks, because insertion of
    // new forth circle between creates three new spaces
}

fun solveProjectEuler(level: Int): Double {
    countCircles = 0
//    System.err.println("level=$level")
    val numberOfInnerCircles = 3

    // three circles have radius 1
    val radiusInnerCircle = 1.0
    val kInnerCircle = 1 / radiusInnerCircle

    val kBigCircle = 3 - 2 * sqrt(3.0) // is negative!
    val radiusBigCircle = -1 / kBigCircle
//    System.err.println("radiusBigCircle=$radiusBigCircle")
    val areaInnerCircles = numberOfInnerCircles * area(radiusInnerCircle)
    countCircles += numberOfInnerCircles

    // outer v shaped areas
    val vShapedOuter = getArea(kBigCircle, kInnerCircle, kInnerCircle, level, true, 3)
//    System.err.println("vShapedOuter=$vShapedOuter")

    // inner area
    val inner = getArea(kInnerCircle, kInnerCircle, kInnerCircle, level, false, 3)
//    System.err.println("inner=$inner")

    val totalArea = areaInnerCircles + numberOfInnerCircles * vShapedOuter + inner
//    System.err.println("totalArea=$totalArea")
//    System.err.println("areaBigCircle=" + area(radiusBigCircle))

    val ratioCoveredArea = totalArea / area(radiusBigCircle)
//    System.err.println("ratio covered area = $ratioCoveredArea")

    val ratioFreeArea = 1 - ratioCoveredArea
//    System.err.println("ratio free = $ratioFreeArea")
//    System.err.println("circle count=$countCircles")

    return BigDecimal(ratioFreeArea).setScale(8, RoundingMode.HALF_UP).toDouble()
}

