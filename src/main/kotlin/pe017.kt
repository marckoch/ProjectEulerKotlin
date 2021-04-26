package pe017

// https://projecteuler.net/problem=17
fun main() {
    println(numberToBritishEnglish(813))
    println(numberToBritishEnglish(548))

    (1..1000)
        .map { numberToBritishEnglish(it) }
        .map { it.replace(" ", "") }
        .map { it.replace("-", "") }
        .sumBy { s -> s.length }
        .let { println(it) }
}

// https://euler.beerbaronbill.com/en/latest/solutions/17.html
fun numberToBritishEnglish(n: Int): String {
    return when (n) {
        in 1 until 20 -> numbers[n].toString()
        in 20..90 step 10 -> numbers[n].toString()  // exact multiple of 10 (meaning: ending with zero: 20, 30, ..., 90)
        in 20 until 100 -> numbers[setLastDigitZero(n)] + "-" + numbers[n % 10]
        in 100..900 step 100 -> numbers[n / 100] + " hundred"  // exact multiple of 100 (100, 200, ..., 900)
        in 100 until 1000 -> numbers[n / 100] + " hundred and " + numberToBritishEnglish(n % 100)
        1000 -> "one thousand"
        else -> throw IllegalArgumentException("cannot convert $n")
    }

}

// setLastDigitZero(37) -> 30
fun setLastDigitZero(n: Int): Int {
    return n / 10 * 10
}

val numbers = mapOf(
    1 to "one",
    2 to "two",
    3 to "three",
    4 to "four",
    5 to "five",
    6 to "six",
    7 to "seven",
    8 to "eight",
    9 to "nine",
    10 to "ten",
    11 to "eleven",
    12 to "twelve",
    13 to "thirteen",
    14 to "fourteen",
    15 to "fifteen",
    16 to "sixteen",
    17 to "seventeen",
    18 to "eighteen",
    19 to "nineteen",
    20 to "twenty",
    30 to "thirty",
    40 to "forty",
    50 to "fifty",
    60 to "sixty",
    70 to "seventy",
    80 to "eighty",
    90 to "ninety"
)