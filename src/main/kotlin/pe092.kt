package pe092

private val endingAt89: MutableSet<Int> = HashSet()
private val endingAt1: MutableSet<Int> = HashSet()

// https://projecteuler.net/problem=92
// just brute force it and use a cache
fun main() {
    println(solve(10_000_000))
}

fun solve(n: Int): Int {
    return (1 until n).filter { endsAt89(it) }.count()
}

fun endsAt89(n: Int): Boolean {
    if (endingAt89.contains(n)) return true
    if (endingAt1.contains(n)) return false

    val sum = getSumOfSquaredDigits(n)
    if (sum == 89) {
        endingAt89.add(n)
        return true
    } else if (sum == 1) {
        endingAt1.add(n)
        return false
    }

    val res = endsAt89(sum)
    if (res) {
        endingAt89.add(sum)
    } else {
        endingAt1.add(sum)
    }

    return res
}

private fun getSumOfSquaredDigits(n: Int): Int {
    return digits(n).sumOf { it * it }
}

private fun digits(n: Int): IntArray {
    return n.toString().toCharArray().map { it - '0' }.toIntArray()
}