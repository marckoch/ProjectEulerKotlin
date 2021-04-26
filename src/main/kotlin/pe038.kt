package pe038

// https://projecteuler.net/problem=38
fun main() {
    // n > 1 means we have at least x followed by 2*x, but we must not exceed 9 digits
    // 1000020000 is already more than 9 digits
    val limit = 10_000

    for (i in 1..limit) {
        val n = buildNumber(i)
        if (n.length == 9) {
            if (isPandigital(n)) {
                println("$i -> $n")
            }
        }
    }
}

// build the number by appending multiples of n, until length is >= 9
fun buildNumber(n: Int): String {
    val multiplesOfN = generateSequence(n) { it + n }.iterator()

    val sb = StringBuilder()
    while (true) {
        sb.append(multiplesOfN.next().toString())
        if (sb.length >= 9) break
    }
    return sb.toString()
}

// true if str is permutation of "123456789"
fun isPandigital(str: String): Boolean {
    if (str.contains("0")) return false

    return str.toList().sorted() == ('1'..'9').toList().sorted()
}