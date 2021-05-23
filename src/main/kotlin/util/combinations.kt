package util.combinations

import java.util.*
import kotlin.math.min

// https://rosettacode.org/wiki/Combinations#Kotlin
// very general solution: build all combinations of size m out of elements of arr
// NO REPETITION!
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

inline fun <reified T> combinations(l: List<T>, m: Int) = combinations(l.toTypedArray(), m)

fun combinations(n: Int, m: Int) = combinations((1..n).toList().toTypedArray(), m)

fun combinations(a: IntArray, m: Int) = combinations(a.toList().toTypedArray(), m)

fun choose(n: Int, k: Int): Long {
            val k = min(k, n - k)

    var res = 1
    for (i in 1..k) {
        res *= n - k + i
        res /= i
    }

    return res.toLong()
}

fun main() {
    val n = 5
    val m = 3
    combinations((1..n).toList().toTypedArray(), m).forEach { println(it.joinToString(separator = " ")) }

    val combinations = combinations(arrayOf(1, 2, 3), 2)
    println(combinations.toList())

    println(combinations(arrayOf("A", "D", "X"), 2).toList())

    println(combinations(IntArray(5) {it + 1}, 3).toList())

    println(choose(10,4))
    println(choose(10,5))
    println(choose(5,2))
}