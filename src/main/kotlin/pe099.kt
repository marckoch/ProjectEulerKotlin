package pe099

import kotlin.math.ln

// https://projecteuler.net/problem=99
fun main() {
    val fileContent = object {}.javaClass.getResource("pe099_base_exp.txt")!!.readText()

    fileContent.lines()
        .withIndex()
        .map { (index, value) -> Pair(index + 1, getValueOfLine(value)) }  // + 1 because linenumber starts at 1
        .maxByOrNull { (_, value) -> value }
        .let { println(it?.first) }
}

/*
 * to compare two numbers a^b < x^y -> log (a^b) < log (x^y) -> b * log a < y * log x
 */
fun getValueOfLine(line: String): Double {
    val (base, exp) = line.split(",")
    return exp.toInt() * ln(base.toDouble())
}
