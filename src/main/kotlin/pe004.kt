// https://projecteuler.net/problem=4
fun main() {
    println(buildPalindromeList().maxOrNull())
}

private fun buildPalindromeList(): List<Int> {
    val palindromes: MutableList<Int> = ArrayList()
    for (i in 101..999) {       // take only 3 digit numbers
        for (j in i..999) {     // j >= i because multiplication is commutative
            val product = i * j
            if (isPalindrome(product)) {
                palindromes.add(product)
            }
        }
    }
    return palindromes
}

private fun isPalindrome(i: Int): Boolean {
    val s = i.toString()
    return s == s.reversed()
}