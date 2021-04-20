import util.getProperDivisorsOf

// https://projecteuler.net/problem=23
fun main() {
    // see problem statement: all numbers above 28123 can surely be written as sum of two abundant numbers
    val limit = 28123

    (1..limit)
        .filter { !isSumOfTwoAbundantNumbers(it) }
        .sum()
        .let { println(it) }
}

fun isSumOfTwoAbundantNumbers(n: Int): Boolean {
    return (1..n / 2)
        .any { isAbundantMemoized(it) && isAbundantMemoized(n - it) }
}

fun isAbundant(n: Int): Boolean {
    return getProperDivisorsOf(n).sum() > n
}

// isAbundant will be called again and again for small numbers, ideal candidate for memoization!
val isAbundantMemoized = { n: Int -> isAbundant(n) }.memoize()

// https://jorgecastillo.dev/kotlin-purity-and-function-memoization
class Memoize1<in T, out R>(val f: (T) -> R) : (T) -> R {
    private val values = mutableMapOf<T, R>()
    override fun invoke(x: T): R {
        return values.getOrPut(x) { f(x) }
    }
}

fun <T, R> ((T) -> R).memoize(): (T) -> R = Memoize1(this)