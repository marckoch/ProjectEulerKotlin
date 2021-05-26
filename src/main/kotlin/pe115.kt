package pe115

// https://projecteuler.net/problem=115
fun main() {
    val fieldLength = solve()
    println(fieldLength)

    println(fill(29, 3))
    println(fill(30, 3))
    println(fill(56, 10))
    println(fill(57, 10))
}

var cache: Array<LongArray> = arrayOf()

fun solve(): Int {
    val estimateMaxFieldLength = 1000
    cache = Array(estimateMaxFieldLength + 1) { LongArray(estimateMaxFieldLength + 1) }

    var fieldLength = 50
    while (true) {
        val n = fill(fieldLength, 50)
        if (n > 1_000_000) {
            return fieldLength
        }
        fieldLength++
    }
}

fun fill(fieldLength: Int, blockLength: Int): Long {
    var solution = 1L
    if (blockLength > fieldLength) {
        return solution
    }

    if (cache[fieldLength][blockLength] > 0) {
        return cache[fieldLength][blockLength]
    }

    for (start in 0..fieldLength - blockLength) {
        for (len in blockLength..fieldLength - start) {
            solution += fill(fieldLength - start - len - 1, blockLength)
        }
    }

    cache[fieldLength][blockLength] = solution

    return solution
}

