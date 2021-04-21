// https://projecteuler.net/problem=26
// https://en.wikipedia.org/wiki/Repeating_decimal
fun main() {
    (1..1001)
        .map { cycleLength(it) }
        .withIndex()
        .maxByOrNull { it.value }
        .let {
            println(it?.index?.plus(1) ?: 0)
        }
}

fun cycleLength(n: Int): Int {
    if (n == 1) return 0
    if (n % 2 == 0) return 0
    if (n % 5 == 0) return 0

    var a = 1
    var count = 0
    do {
        a = a * 10 % n
        count++
    } while (a != 1)
    return count
}