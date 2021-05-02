package pe067

import kotlin.math.max

// https://projecteuler.net/problem=67
// see https://en.wikipedia.org/wiki/Dynamic_programming
// https://www.geeksforgeeks.org/maximum-path-sum-triangle/
fun main() {
    val fileContent = object {}.javaClass.getResource("pe067_triangle.txt")!!.readText()

    val numbers = fileContent
        .lines()
        .map { convertRow(it) }

    println(maxPathSum(numbers))
}

// read numbers from row as MutableList because we want to change it in `sum` function
fun convertRow(row: String): MutableList<Int> {
    return row.split(" ")
        .map(String::toInt)
        .toMutableList()
}

fun maxPathSum(numbers: List<MutableList<Int>>): Int {
    // we work our way from bottom row back up and increase each number by the maximum of its 2 lower neighbors
    for (row in numbers.size - 2 downTo 0) {
        for (col in 0..row) {
            numbers[row][col] += max(numbers[row + 1][col], numbers[row + 1][col + 1])
        }
    }

    // numbers.forEach { System.err.println(it) }

    return numbers[0][0]
}