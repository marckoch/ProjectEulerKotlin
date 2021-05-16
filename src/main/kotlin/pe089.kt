package pe089

// https://projecteuler.net/problem=89
fun main() {
    val fileContent = object {}.javaClass.getResource("pe089_roman.txt")!!.readText()
    fileContent.lines().forEach { System.err.println("$it = ${roman2int(it)}") }

    fileContent.lines()
        .sumOf {
            calculateSavings(it)
        }.let { println(it) }
}

// we take the (unoptimized) input numeral and calculate its value,
// than we convert this value back into a proper (optimized) roman numeral.
// the difference between both lengths is our savings
fun calculateSavings(romanNumeral: String): Int {
    val n = roman2int(romanNumeral)
    val optimizedString = int2roman(n)
    return romanNumeral.length - optimizedString.length
}

// the order is important! "IX" should match before "I"!!
val ROMAN_NUMERALS = arrayOf(
    Pair("M", 1000),
    Pair("CM", 900),
    Pair("D", 500),
    Pair("CD", 400),
    Pair("C", 100),
    Pair("XC", 90),
    Pair("L", 50),
    Pair("XL", 40),
    Pair("X", 10),
    Pair("IX", 9),
    Pair("V", 5),
    Pair("IV", 4),
    Pair("I", 1)
)

// we consume the roman numeral from the beginning,
// if we find a matching token we remove it and add
// its corresponding value to our sum
fun roman2int(roman: String): Int {
    var remaining = roman
    var sum = 0
    while (remaining.isNotEmpty()) {
        for ((romanNumeral, value) in ROMAN_NUMERALS) {
            if (remaining.startsWith(romanNumeral)) {
                sum += value
                remaining = remaining.substring(romanNumeral.length)
            }
        }
    }
    return sum
}

fun int2roman(number: Int): String {
    var remaining = number
    val sb = StringBuilder()
    outer@ while (remaining > 0) {
        for ((romanNumeral, value) in ROMAN_NUMERALS) {
            if (remaining >= value) {
                remaining -= value
                sb.append(romanNumeral)
                continue@outer
            }
        }
    }
    return sb.toString()
}