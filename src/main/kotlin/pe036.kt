// https://projecteuler.net/problem=36
fun main() {
    (1..1_000_000)
        .filter { isPalindrome(it, 10) && isPalindrome(it, 2) }
        .sum()
        .let { println(it) }
}

private fun isPalindrome(n: Int, radix: Int): Boolean {
    val s = n.toString(radix)
    return s == s.reversed()
}