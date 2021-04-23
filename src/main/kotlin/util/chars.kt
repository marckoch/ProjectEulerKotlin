package util

fun sumByAlphabeticWeight(name: String): Int {
    return name
        .map { c -> Character.getNumericValue(c) - 9 }  // - 9, because Character.getNumericValue("A") == 10, etc.
        .sum()
}

fun main() {
    println(sumByAlphabeticWeight("A"))  // 1
    println(sumByAlphabeticWeight("B"))  // 2
    println(sumByAlphabeticWeight("Z"))  // 26
    println(sumByAlphabeticWeight("SKY"))  // 19 + 11 + 25 = 55
}