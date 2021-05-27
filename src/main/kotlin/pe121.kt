package pe121

// https://projecteuler.net/problem=121
fun main() {
    // val limit = 4  example from problem statement -> 11/120
    val limit = 15

    solveMathblogDk(limit)

    solve(limit)
}

// this will build the outcome pyramid as a array of array
// only the last line interests us
fun solve(limit: Int) {
    val outcomes = Array(limit + 1) { LongArray(limit + 1) }
    for (row in 0..limit) {
        outcomes[row][0] = 1
        for (col in 1..row) {
            outcomes[row][col] = row * outcomes[row - 1][col - 1] + outcomes[row - 1][col]
        }
    }
    outcomes.forEach { println(it.contentToString()) }

    var positive = 0L
    for (i in 0 until (limit + 1) / 2) {
        positive += outcomes[limit][i]
    }
    println("$positive ${outcomes[limit].sum()}")

    println(outcomes[limit].sum() / positive)
}

// this calculates the last row by overwriting it again and again
// https://www.mathblog.dk/project-euler-121-coloured-discs/
fun solveMathblogDk(limit: Int) {
    val outcomes = LongArray(limit + 1)
    outcomes[limit] = 1
    outcomes[limit - 1] = 1

    for (i in 2..limit) {
        for (j in 0 until outcomes.size - 1) {
            outcomes[j] = outcomes[j + 1]
        }
        outcomes[limit] = 0
        for (j in outcomes.size - 1 downTo 1) {
            outcomes[j] += outcomes[j - 1] * i
        }
        // println(outcomes.contentToString())
    }
    println(outcomes.contentToString())

    var positive = 0L
    for (i in 0 until (limit + 1) / 2) {
        positive += outcomes[i]
    }
    println("$positive ${outcomes.sum()}")

    println(outcomes.sum() / positive)
}