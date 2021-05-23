package util.minimalSpanningTree

import util.graph.*

fun findMinimalSpanningTree(matrix: Array<IntArray>) {
    // printMatrix(matrix, "\t")

    val edgesSorted: List<Edge> = getEdgesSorted(matrix)
    // println("edges: ${edgesSorted.size}")

    val dimensions = Pair(matrix.size, matrix[0].size)
    val optimizedMatrix = findMST(dimensions, edgesSorted)
    // printMatrix(optimizedMatrix, "\t")

    val weightBefore = getWeight(matrix)
    val weightAfter = getWeight(optimizedMatrix)

    println("weight before=$weightBefore, weight after=$weightAfter, saving=${weightBefore - weightAfter}")
}

// minimal spanning tree MST
private fun findMST(dimensions: Pair<Int, Int>, edges: List<Edge>): Array<IntArray> {
    val (rows, cols) = dimensions
    val matrix = Array(rows) { IntArray(cols) }

    edges.forEach {
        val (from, to, cost)  = it
        matrix[from][to] = cost
        matrix[to][from] = cost

        if (findAllCycles(matrix).isNotEmpty()) {
            // undo and forget last added edge
            matrix[from][to] = 0
            matrix[to][from] = 0
        } else {
            if (isMinimalSpanningTree(matrix)) {
                println("we found a MST with no cycles!")
            }
        }
    }
    return matrix
}

fun printMatrix(matrix: Array<IntArray>, delimiter: String) {
    println("-------")
    for (row in matrix) {
        var del = ""
        for (i in row) {
            print(del + i)
            del = delimiter
        }
        println()
    }
}

fun main() {
    val matrix1 = arrayOf(
        intArrayOf(0, 16, 12, 21),
        intArrayOf(16, 0, 0, 17),
        intArrayOf(12, 0, 0, 28),
        intArrayOf(21, 17, 28, 0)
    )
    val matrix2 = arrayOf(intArrayOf(0, 16, 12), intArrayOf(16, 0, 0), intArrayOf(12, 0, 0))
    val matrix3 = arrayOf(
        intArrayOf(0, 16, 12, 21, 0, 0, 0),
        intArrayOf(16, 0, 0, 17, 20, 0, 0),
        intArrayOf(12, 0, 0, 28, 0, 31, 0),
        intArrayOf(21, 17, 28, 0, 18, 19, 23),
        intArrayOf(0, 20, 0, 18, 0, 0, 11),
        intArrayOf(0, 0, 31, 19, 0, 0, 27),
        intArrayOf(0, 0, 0, 23, 11, 27, 0)
    )
    val matrix4 = arrayOf(
        intArrayOf(0, 16, 1, 1, 0, 0, 0),
        intArrayOf(16, 0, 0, 17, 20, 0, 0),
        intArrayOf(1, 0, 0, 28, 0, 31, 0),
        intArrayOf(1, 17, 28, 0, 18, 19, 23),
        intArrayOf(0, 20, 0, 18, 0, 0, 11),
        intArrayOf(0, 0, 31, 19, 0, 0, 27),
        intArrayOf(0, 0, 0, 23, 11, 27, 0)
    )
    findMinimalSpanningTree(matrix4)
}