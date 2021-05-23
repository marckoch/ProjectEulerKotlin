package pe107

import util.minimalSpanningTree.findMinimalSpanningTree

// https://projecteuler.net/problem=107
fun main() {
    val fileContent = object {}.javaClass.getResource("pe107_network.txt")!!.readText()
    val matrix = fileContent.lines().map { toInts(it) }.toTypedArray()

    findMinimalSpanningTree(matrix)
}

fun toInts(line: String): IntArray {
    return line.split(",").map { s -> if (s == "-") 0 else s.toInt() }.toIntArray()
}
