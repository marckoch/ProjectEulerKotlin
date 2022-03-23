package pe149

import util.findMaximumSubsequenceSum
import util.laggedFibonacci.buildLaggedFibonacciArray

fun main() {
    findMax(buildExampleMatrix()).let { println(it) }

    findMax(buildLaggedMatrix(2000)).let { println(it) }
}

fun findMax(matrix: Array<Array<Long>>): Long {
    val matrixParts = MatrixDecomposer(matrix)

    val maxInRows = matrixParts.rows.maxOf { findMaximumSubsequenceSum(it) }
    val maxInCols = matrixParts.cols.maxOf { findMaximumSubsequenceSum(it) }
    val maxInAntiDiagonals = matrixParts.antiDiagonals.maxOf { findMaximumSubsequenceSum(it) }
    val maxInDiagonals = matrixParts.diagonals.maxOf { findMaximumSubsequenceSum(it) }

    return maxOf(maxInRows, maxInCols, maxInDiagonals, maxInAntiDiagonals)
}

fun buildExampleMatrix(): Array<Array<Long>> {
    val exampleMatrix = """
-2 5 3 2
9 -6 5 1
3 2 7 3
-1 8 -4 8
""".trimIndent()

    return exampleMatrix.lines()
        .map { line ->
            line.split(" ")
                .map(String::toLong)
                .toTypedArray()
        }
        .toTypedArray()
}

// build n * n matrix filled with the lagged Fibonacci numbers
fun buildLaggedMatrix(n: Int): Array<Array<Long>> {
    val numbers = buildLaggedFibonacciArray(n * n)
    val matrix = Array(n) { Array(n) { 0L } }
    for (k in 0 until n * n) {
        val row = k / n
        val col = k % n
        matrix[row][col] = numbers[k]
    }
    return matrix
}

// enter a (square) matrix and get rows, columns, diagonals and anti diagonals as List<List<Long>>
class MatrixDecomposer(matrix: Array<Array<Long>>) {
    var rows: List<List<Long>>
    var cols: List<List<Long>>
    var antiDiagonals: List<List<Long>>
    var diagonals: List<List<Long>>

    init {
        val rowMap = HashMap<Int, MutableList<Long>>()
        val colMap = HashMap<Int, MutableList<Long>>()
        val antiDiagonalsMap = HashMap<Int, MutableList<Long>>()
        val diagonalsMap = HashMap<Int, MutableList<Long>>()

        for (row in matrix.indices) {
            for (col in matrix[0].indices) {
                if (rowMap[row] == null) rowMap[row] = ArrayList()
                rowMap[row]?.add(matrix[row][col])

                if (colMap[col] == null) colMap[col] = ArrayList()
                colMap[col]?.add(matrix[row][col])

                val antiDiagIndex = row + col
                if (antiDiagonalsMap[antiDiagIndex] == null) antiDiagonalsMap[antiDiagIndex] = ArrayList()
                antiDiagonalsMap[antiDiagIndex]?.add(matrix[row][col])

                val diagIndex = matrix.size + row - col - 1
                if (diagonalsMap[diagIndex] == null) diagonalsMap[diagIndex] = ArrayList()
                diagonalsMap[diagIndex]?.add(matrix[row][col])
            }
        }
        rows = rowMap.values.toList()
        cols = colMap.values.toList()
        antiDiagonals = antiDiagonalsMap.values.toList()
        diagonals = diagonalsMap.values.toList()
    }
}

// wrong! I misinterpreted the problem statement.
// this code finds the maximum row, col or diagonal
// but problem asks for maximum SUB sequence (not complete row, col, etc.)
fun findMaxSum(matrix: Array<Array<Long>>): Long {
    val rowSum = LongArray(matrix.size)
    val colSum = LongArray(matrix.size)
    val diagSum1 = LongArray(2 * matrix.size - 1)
    val diagSum2 = LongArray(2 * matrix.size - 1)
    for (row in matrix.indices) {
        for (col in matrix[0].indices) {
            rowSum[row] += matrix[row][col]
            colSum[col] += matrix[row][col]
            diagSum1[row + col] += matrix[row][col]
            diagSum2[matrix.size + row - col - 1] += matrix[row][col]
        }
    }
    println(rowSum.contentToString())
    println(colSum.contentToString())
    println(diagSum1.contentToString())
    println(diagSum2.contentToString())

    println(rowSum.maxOrNull())
    println(colSum.maxOrNull())
    println(diagSum1.maxOrNull())
    println(diagSum2.maxOrNull())
    val max = maxOf(rowSum.maxOrNull()!!, colSum.maxOrNull()!!, diagSum1.maxOrNull()!!, diagSum2.maxOrNull()!!)

    if (rowSum.contains(max)) println("row : ${rowSum.indexOf(max)}")
    if (colSum.contains(max)) println("col : ${colSum.indexOf(max)}")
    if (diagSum1.contains(max)) println("diag1 : ${diagSum1.indexOf(max)}")
    if (diagSum2.contains(max)) println("diag2 : ${diagSum2.indexOf(max)}")

    return max
}

fun printArray(grid: Array<Array<Long>>) {
    grid.forEach { numbers -> println(numbers.contentToString()) }
}