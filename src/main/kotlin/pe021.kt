import util.getProperDivisorsOf

// https://projecteuler.net/problem=21
fun main() {
    (1..10_000)
        .flatMapTo(mutableSetOf()) {        // collect in Set to avoid counting a pair twice
            val n = getProperDivisorsOf(it).sum()
            if (n != it && getProperDivisorsOf(n).sum() == it) {  // "n != it" to exclude perfect numbers (e.g. 6)
                listOf(it, n)
            } else {
                emptyList()
            }
        }
        .also { println(it) }
        .sum()
        .let { println(it) }
}