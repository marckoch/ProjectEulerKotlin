package pe022

import util.chars.sumByAlphabeticWeight

// https://projecteuler.net/problem=22
fun main() {
    val fileContent = object {}.javaClass.getResource("pe022_names.txt")!!.readText()

    fileContent
        .split(",")
        .map { it.replace("\"", "") }
        .sorted()
        .mapIndexed { index, s -> (index + 1) * sumByAlphabeticWeight(s) }
        .sum()
        .let { println(it) }
}