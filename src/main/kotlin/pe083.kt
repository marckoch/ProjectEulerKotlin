package pe083

import java.util.*
import kotlin.math.min

// https://projecteuler.net/problem=83
fun main() {
    println(solveBFS(exampleMatrix()))

    println(solveBFS(readMatrix()))
}

// BFS solution, breadth first search
// similar to pe080, but:
// - we start at the top left cell
// - we end at the bottom right cell
// - we can step up, down, left and right
fun solveBFS(array: Array<Array<Int>>): Int {
    val n = array.size
    val visited = Array(n) { BooleanArray(n) }

    //printArray(array)

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

        // println("we are here $cell")

        // we are at the finish: bottom right cell
        if (cell.row == array.lastIndex && cell.col == array.lastIndex) {
            return cell.cost
        }

        // take steps and add new cells to queue (only if step is possible!)
        // ordering will be done by PriorityQueue (cheapest, lowest cost always in front)
        cell.stepLeft(array)?.let { queue.add(it) }
        cell.stepRight(array)?.let { queue.add(it) }
        cell.stepDown(array)?.let { queue.add(it) }
        cell.stepUp(array)?.let { queue.add(it) }
    }
    return 0
}

fun printArray(grid: Array<Array<Int>>) {
    grid.forEach { ints -> println(ints.contentToString()) }
}

fun readMatrix(): Array<Array<Int>> {
    val fileContent = object {}.javaClass.getResource("pe083_matrix.txt")!!.readText()
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

    // take step left (col decreases)
    fun stepLeft(array: Array<Array<Int>>): Cell? {
        if (col > 0) {
            return Cell(row, col - 1, cost + array[row][col - 1])
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

    // take step up (row decreases)
    fun stepUp(array: Array<Array<Int>>): Cell? {
        if (row > 0) {
            return Cell(row - 1, col, cost + array[row - 1][col])
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