package pe024

// https://projecteuler.net/problem=24
// https://blogs.scientificamerican.com/roots-of-unity/its-factoradical/
// https://en.wikipedia.org/wiki/Factorial_number_system
fun main() {
    val seed = "0123456789"
    val n = 1_000_000

    val nFact = toFactoradical(n - 1, 10)
    println(buildString(seed, nFact))
}

// https://medium.com/@aiswaryamathur/find-the-n-th-permutation-of-an-ordered-string-using-factorial-number-system-9c81e34ab0c8
private fun toFactoradical(N: Int, base: Int): IntArray {
    var n = N
    val factorial = IntArray(base)
    var div = 1
    while (n > 0) {
        factorial[base - div] = n % div
        n /= div
        div++
    }
    return factorial
}

private fun buildString(seed: String, nFact: IntArray): String {
    // convert to List because in list remove is simpler
    val chars: MutableList<Char> = seed.toMutableList()
    return nFact
        .map { chars.removeAt(it) }
        .joinToString("")
}
