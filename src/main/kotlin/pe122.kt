package pe122

// https://projecteuler.net/problem=122
// https://en.wikipedia.org/wiki/Addition-chain_exponentiation
// https://oeis.org/A003313
fun main() {
    bruteforce()
}

var limit = 200
var cost: IntArray = IntArray(limit + 1) { Int.MAX_VALUE }
var path: IntArray = IntArray(limit + 1)

// https://www.mathblog.dk/project-euler-122-efficient-exponentiation/
fun bruteforce() {
    backtrack(1, 0)

    println(cost.contentToString())

    cost[0] = 0 // still has Integer.MAX_VALUE in it
    println("the sum of m is ${cost.sum()}")
}

fun backtrack(power: Int, depth: Int) {
    if (power > limit || depth > cost[power])
        return

    cost[power] = depth
    path[depth] = power

    for (i in depth downTo 0)
        backtrack(power + path[i], depth + 1)
}