package pe042

import util.chars.sumByAlphabeticWeight
import util.triangular.isTriangular

// https://projecteuler.net/problem=42
fun main() {
    val fileContent = object {}.javaClass.getResource("pe042_words.txt")!!.readText()

    fileContent
        .split(",")
        .map { it.replace("\"", "") }
        .map { sumByAlphabeticWeight(it) }
        .filter { isTriangular(it.toLong()) }
        .count()
        .let { println(it) }
}

