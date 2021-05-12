package pe081

import java.util.*
import kotlin.math.min

// https://projecteuler.net/problem=81
fun main() {
    println(solveDP(exampleMatrix()))

    println(solveBFS(exampleMatrix()))

    println(solveDP(readMatrix()))

    println(solveBFS(readMatrix()))
}

// simple dynamic programming solution
// just go from top left to bottom right and add to each cell the minimum of the cell above or left
fun solveDP(array: Array<Array<Int>>): Int {
    //printArray(array)
    for (row in array.indices) {
        for (col in array.indices) {

            // top left cell has no neighbor left and above
            if (row == 0 && col == 0) {
                continue
            }

            val left = if (col > 0) array[row][col - 1] else Int.MAX_VALUE
            val above = if (row > 0) array[row - 1][col] else Int.MAX_VALUE

            // add minimum of left or above field to field
            array[row][col] += min(left, above)
        }
    }

    //printArray(array)
    // value is in bottom right cell
    return array[array.lastIndex][array.lastIndex]
}

// BFS solution, breadth first search
// similar to pe081, but:
// - we start at the top left cell
// - we end at the bottom right cell
// - we can step down and right
fun solveBFS(array: Array<Array<Int>>): Int {
    val n = array.size
    val visited = Array(n) { BooleanArray(n) }

    //printArray(array);

    // PriorityQueue has smallest element in the front
    // (according to defined order, see Cell.compare(...)
    val queue = PriorityQueue<Cell>()
    // seed starting point: top left cell
    queue.add(Cell(0, 0, array[0][0]))

    while (!queue.isEmpty()) {
        val cell = queue.poll()

        if (visited[cell.row][cell.col]) {
            continue
        }
        visited[cell.row][cell.col] = true

        // println("we are here $cell");

        // we are at the finish: bottom right cell
        if (cell.row == array.lastIndex && cell.col == array.lastIndex) {
            return cell.cost
        }

        // take steps and add new cells to queue (only if step is possible!)
        // ordering will be done by PriorityQueue (cheapest, lowest cost always in front)
        cell.stepRight(array)?.let { queue.add(it) }
        cell.stepDown(array)?.let { queue.add(it) }
    }
    return 0
}

fun printArray(grid: Array<Array<Int>>) {
    grid.forEach { ints -> println(ints.contentToString()) }
}

fun readMatrix(): Array<Array<Int>> {
    val fileContent = object {}.javaClass.getResource("pe081_matrix.txt")!!.readText()
    val lineToIntArray = { line: String -> line.split(",").map { s -> s.toInt() }.toTypedArray() }
    return fileContent.lines().map { lineToIntArray(it) }.toTypedArray()
}

data class Cell(var row: Int, var col: Int, var cost: Int) : Comparable<Cell> {
    // take step right (col increases)
    fun stepRight(array: Array<Array<Int>>): Cell? {
        if (col + 1 < array.size) {
            return Cell(row, col + 1, cost + array[row][col + 1])
        }
        return null
    }

    // take step down (row increases)
    fun stepDown(array: Array<Array<Int>>): Cell? {
        if (row + 1 < array.size) {
            return Cell(row + 1, col, cost + array[row + 1][col])
        }
        return null
    }

    override operator fun compareTo(other: Cell): Int {
        return cost.compareTo(other.cost)
    }
}

fun exampleMatrix(): Array<Array<Int>> {
    return arrayOf(
        arrayOf(131, 673, 234, 103, 18),
        arrayOf(201, 96, 342, 965, 150),
        arrayOf(630, 803, 746, 422, 111),
        arrayOf(537, 699, 497, 121, 956),
        arrayOf(805, 732, 524, 37, 331)
    )
}