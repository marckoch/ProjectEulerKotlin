import kotlin.math.pow

// https://projecteuler.net/problem=40
fun main() {
    val c = buildChampernowne(1_000_000)

    (1..7)
        .map { 10.0.pow(it - 1).toInt() } // 1, 10, 100, ..., 1_000_000
        .map { digit(c, it) }
        .also { println(it) }
        .reduce { acc, i -> acc * i }
        .let { println(it) }
}

fun digit(s: String, pos: Int): Int = s[pos - 1] - '0'

fun buildChampernowne(n: Int): String {
    val sb = StringBuilder()
    var i = 0
    while (true) {
        i++
        sb.append(i.toString())
        if (sb.length > n) break
    }
    return sb.toString().substring(0, n)
}