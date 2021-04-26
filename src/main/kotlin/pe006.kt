package pe006

// https://projecteuler.net/problem=6
fun main() {
    val numbers = (1..100)

    val squareOfSum = numbers.sum() * numbers.sum()
    val sumOfSquares = numbers.sumOf { it * it }

    println(squareOfSum - sumOfSquares)
}
