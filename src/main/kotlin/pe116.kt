package pe116

// https://projecteuler.net/problem=116
fun main() {
    // val fieldLength = 5 // example in project euler
    val fieldLength = 50
    cache = Array(fieldLength + 1) { LongArray(5) }

    val red = fill(fieldLength, 2)
    println(red)

    val green = fill(fieldLength, 3)
    println(green)

    val blue = fill(fieldLength, 4)
    println(blue)

    val sum = red + green + blue
    println(sum)
}

var cache: Array<LongArray> = arrayOf()

fun fill(fieldLength: Int, blockLength: Int): Long {
    if (blockLength > fieldLength) {
        return 0
    } else if (blockLength == fieldLength) {
        return 1
    }

    if (cache[fieldLength][blockLength] > 0) {
        return cache[fieldLength][blockLength]
    }

    var solution = 0L
    for (start in 0..fieldLength - blockLength) {
        solution++ // one block and nothing else

        // plus filling up the space to the right
        solution += fill(fieldLength - start - blockLength, blockLength)
    }

    cache[fieldLength][blockLength] = solution
    return solution
}

