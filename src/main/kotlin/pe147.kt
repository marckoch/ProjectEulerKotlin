package pe147

// https://projecteuler.net/problem=147
// TODO UNFINISHED
fun main() {
    val dim = Pair(2, 2)
    val countStraightRectangles = countStraightRectangles(dim)
    println("$dim -> $countStraightRectangles (straight)")

    val countTilted = countTiltedRectangles(dim)
    println("$dim -> $countTilted (tilted)")

    val referenceDiag = countDiagonalRectangles(dim.first, dim.second)
    println("$dim -> $referenceDiag (tilted, reference)")
}

// we choose top left corner and bottom right corner
// top left corner goes from (0,0) to (x-1, y-1)
// bottom right corner from topLeftCorners dimensions plus 1 to lower right corner of field (x,y)
// https://mathworld.wolfram.com/RectangleTiling.html
fun countStraightRectangles(dim: Pair<Int, Int>): Long {
    val (x, y) = dim
    return x.toLong() * (x + 1) * y * (y + 1) / 4

    // brute force:
//    var count = 0L
//
//    for (xTopLeft in (0 until x)) {
//        for (yTopLeft in (0 until y)) {
//            for (xBottomRight in (xTopLeft + 1)..x) {
//                for (yBottomRight in (yTopLeft + 1)..y) {
//                    println("topLeft=($xTopLeft,$yTopLeft) bottomRight=($xBottomRight,$yBottomRight)")
//                    count++
//                }
//            }
//        }
//    }
//    return count
}

fun countTiltedRectangles(dim: Pair<Int, Int>): Long {
    val (x, y) = dim
    var count = 0L

    val noOf1Squares = (x - 1) * y + (y - 1) * x
    println("noOf1Squares=$noOf1Squares")
    count += noOf1Squares

    val noOf2Squares = (x - 1) * (y - 1)
    println("noOf2Squares=$noOf2Squares")
    count += noOf2Squares

    return count
}

// https://blog.dreamshire.com/project-euler-147/
// to find bugs in my code
fun countDiagonalRectangles(m: Int, n: Int): Long {
    return n * ((2L * m - n) * (4 * n * n - 1) - 3) / 6
}