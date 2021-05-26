package pe117

import kotlin.math.min

// https://projecteuler.net/problem=117
fun main() {
    val fieldLength = 50

    cache = Array(fieldLength + 1) { LongArray(fieldLength + 1) }
    val solution = fill(fieldLength, 2)
    println(solution)
}

var cache: Array<LongArray> = arrayOf()
var count = 0

fun fill(fieldLength: Int, blockLength: Int): Long {
    var solution: Long = 1
    if (blockLength > fieldLength) {
        return solution
    }

    if (cache[fieldLength][blockLength] > 0) {
        return cache[fieldLength][blockLength]
    }

    for (start in 0..fieldLength - blockLength) {
        for (len in blockLength..min(4, fieldLength - start)) { // len can be max 4
            solution += fill(fieldLength - start - len, blockLength) // no -1 here! no more gap like in PE114!
        }
    }

    cache[fieldLength][blockLength] = solution
    return solution
}

