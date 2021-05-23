package pe105

import pe103.verifyRule1
import pe103.verifyRule2

// https://projecteuler.net/problem=105
fun main() {
    val fileContent = object {}.javaClass.getResource("pe105_sets.txt")!!.readText()

    fileContent.lines().map { toInts(it) }
        .filter { list -> verifyRule1(list) && verifyRule2(list) }
        .sumOf { list -> list.sum() }
        .let { println(it) }
}

fun toInts(line: String): List<Int> {
    return line.split(",").map { it.toInt() }.toList()
}
