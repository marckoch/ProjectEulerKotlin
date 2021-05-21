package util

import kotlin.math.pow

fun main() {
    val a = arrayOf(1, 2, 4, 5, 7)
    buildSubSets(a).sortedBy { it.size }.forEach { println(it.contentToString()) }

    val l = listOf("A", "B", "C")
    buildSubSets(l).sortedBy { it.size }.forEach { println(it) }
}

inline fun <reified T> buildSubSets(list: List<T>): List<List<T>> {
    val n = 2.0.pow(list.size.toDouble()).toInt()
    return (0 until n).map {
        buildSubSetForIndex(list, it)
    }.toList()
}

inline fun <reified T> buildSubSets(array: Array<T>): List<Array<T>> {
    return buildSubSets(array.toList()).map { list -> list.toTypedArray() }
}

// take input array and produce every combination of subsets
// each element can be in result or not >> 2 ^ n sets
// we do this by "overlaying" a bit representation over the list:
// '1' indicates element is in set, '0' indicates element is NOT in set
//
// e.g. number 13 == 1101 >> combined with input [x1, x2, x3, x4] -> x1, x2, x4 is in result
fun <T> buildSubSetForIndex(list: List<T>, i: Int): List<T> {
    var x = i
    val result = mutableListOf<T>()
    for (j in list.indices) {
        if (x and 1 == 1) {
            result.add(list[j])
        }
        x = x shr 1
    }
    return result
}