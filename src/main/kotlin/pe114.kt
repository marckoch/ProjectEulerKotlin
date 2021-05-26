package pe114

var cache: Array<LongArray> = arrayOf()

// https://projecteuler.net/problem=114
fun main() {
    val fieldLength = 50
    val minBlockLength = 3

    val count = count(fieldLength, minBlockLength)

    println(count)
}

fun count(fieldLength: Int, minBlockLength: Int): Long {
    cache = Array(fieldLength + 1) { LongArray(fieldLength + 1) }

    return fillField(fieldLength, minBlockLength) + 1  // +1 when field is left empty
}

fun fillField(fieldLength: Int, minBlockLength: Int): Long {
    return (minBlockLength..fieldLength).sumOf {
        fill(fieldLength, it, minBlockLength)
    }
}

fun fill(fieldLength: Int, blockLength: Int, minBlockLength: Int): Long {
    if (cache[fieldLength][blockLength] > 0) {
        return cache[fieldLength][blockLength]
    }
    // println("placing block of length=$blockLength on field of length=$fieldLength")
    var count = 0L

    // block of length 'blockLength' wanders from 'start' to the right
    for (start in 0..fieldLength - blockLength) {
        count++

        // see if we can place more blocks to the right of current block
        val remainingFieldLength = fieldLength - start - blockLength - 1 // one space buffer
        count += fillField(remainingFieldLength, minBlockLength)
    }

    cache[fieldLength][blockLength] = count

    // println("placing block of length=$blockLength on field of length=$fieldLength >> $count")
    return count
}