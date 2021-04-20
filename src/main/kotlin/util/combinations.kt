package util

import java.util.LinkedList

// https://rosettacode.org/wiki/Combinations#Kotlin
// very general solution: build all combinations of size m out of elements of arr
inline fun <reified T> combinations(arr: Array<T>, m: Int) = sequence {
    val n = arr.size
    val result = Array(m) { arr[0] }
    val stack = LinkedList<Int>()
    stack.push(0)
    while (stack.isNotEmpty()) {
        var resIndex = stack.size - 1;
        var arrIndex = stack.pop()

        while (arrIndex < n) {
            result[resIndex++] = arr[arrIndex++]
            stack.push(arrIndex)

            if (resIndex == m) {
                yield(result.toList())
                break
            }
        }
    }
}

fun combinations(n: Int, m: Int) = combinations((1..n).toList().toTypedArray(), m)

fun main() {
    val n = 5
    val m = 3
    combinations((1..n).toList().toTypedArray(), m).forEach { println(it.joinToString(separator = " ")) }

    val combinations = combinations(arrayOf(1, 2, 3), 2)
    println(combinations.toList())

    println(combinations(arrayOf("A", "D", "X"), 2).toList())
}