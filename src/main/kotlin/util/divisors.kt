package util

fun getDivisorsOf(n: Int): Set<Int> {
    val divisors: MutableSet<Int> = sortedSetOf()
    var d = 1
    while (d * d <= n) {
        if (n % d == 0) {
            divisors.add(d)
            divisors.add(n / d)
        }
        d++
    }
    return divisors
}