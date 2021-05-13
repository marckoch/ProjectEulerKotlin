package pe082

import java.util.*
import kotlin.math.min

// https://projecteuler.net/problem=82
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

    val solutionArray = IntArray(array.size) { 0 }
    //initialise solution array
    for (i in array.indices) {
        solutionArray[i] = array[i][array.lastIndex]
    }

    // we move from right to left through the columns
    // in each col we move rows down and up again and fill solution array with the current minimal value
    for (col in array.lastIndex - 1 downTo 0) {
        // start with row[0]
        solutionArray[0] += array[0][col]

        // Traverse down, row > 1 (0 we already used in 2 lines above)
        for (row in 1..array.lastIndex) {
            solutionArray[row] = min(solutionArray[row - 1] + array[row][col], solutionArray[row] + array[row][col])
        }
        // System.err.println("sol 2 " + solutionArray.contentToString())

        //Traverse up again
        for (row in array.lastIndex - 1 downTo 0) {
            solutionArray[row] = min(solutionArray[row], solutionArray[row + 1] + array[row][col])
        }
        // System.err.println("sol 3 " + solutionArray.contentToString())
    }

    //printArray(array)
    return solutionArray.minOrNull()!!
}

// BFS solution, breadth first search
// similar to pe080, but:
// - we start anywhere in the left most column
// - we end anywhere at the right most column
// - we can step up, down and right
fun solveBFS(array: Array<Array<Int>>): Int {
    val n = array.size
    val visited = Array(n) { BooleanArray(n) }

    //printArray(array)

    // PriorityQueue has smallest element in the front
    // (according to defined order, see Cell.compare(...)
    val queue = PriorityQueue<Cell>()
    // seed starting points: every cell in left column
    for (i in array.indices) {
        queue.add(Cell(i, 0, array[i][0]))
    }

    while (!queue.isEmpty()) {
        val cell = queue.poll()

        if (visited[cell.row][cell.col]) {
            continue
        }
        visited[cell.row][cell.col] = true

        // println("we are here $cell")

        // we are at the finish: any cell in the right most column
        if (cell.col == array.lastIndex) {
            return cell.cost
        }

        // take steps and add new cells to queue (only if step is possible!)
        // ordering will be done by PriorityQueue (cheapest, lowest cost always in front)
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
    val fileContent = object {}.javaClass.getResource("pe082_matrix.txt")!!.readText()
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