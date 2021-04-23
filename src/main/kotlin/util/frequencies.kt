package util

fun charFrequencyOf(s: String) = s.groupingBy { it }.eachCount()

fun main() {
    println(charFrequencyOf("1123444432AA"))
}