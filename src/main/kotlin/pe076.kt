package pe076

// https://projecteuler.net/problem=76
fun main() {
    println(getFrequencies(100).last())
}

private fun getFrequencies(n: Int): IntArray {
    val frequencies = IntArray(n + 1)
    frequencies[0] = 1
    for (i in 1 until n) {
        for (j in i..n) {
            frequencies[j] += frequencies[j - i]
        }
    }
    return frequencies
}