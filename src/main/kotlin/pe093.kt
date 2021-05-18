package pe093

import util.combinations.combinations
import util.permutations.permute
import java.util.*
import kotlin.math.roundToInt

// https://projecteuler.net/problem=93
fun main() {
    // testGetChainLength()

    solveProjectEuler()
}

fun solveProjectEuler() {
    val combinations = combinations(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9), 4).toList()
//    val combinations = combinations(listOf(1, 2, 5, 8), 4)
//    val combinations = combinations(listOf(1, 2, 3, 7), 4)
//    val combinations = combinations(listOf(1, 2, 3, 4), 4)

    combinations
        .map { combination -> Pair(combination, processCombination(combination)) }
        .maxByOrNull { (_, chainLength) -> chainLength }
        .let { print("the winner is ${it?.first} with a chain of ${it?.second}") }
}

private fun processCombination(combination: List<Int>): Int {
    val permutations = permute(combination)
    val allResults = TreeSet<Double>()

    permutations.forEach { list -> processNumber(toDoubleArray(list), allResults) }

    val validResults = allResults
        .asSequence()
        .filter { it > 0 }
        .filter { it == it.roundToInt().toDouble() }
        .map { it.toInt() }
        .toSortedSet()
        .toList()

    return getChainLength(validResults)
}

// check how many consecutive increasing by 1 numbers we have in this list
// (1,2,3,4,8) --> 4
private fun getChainLength(numbers: List<Int>): Int {
    return numbers
        .windowed(2)  // process each pair of consecutive numbers
        .indexOfFirst { it.last() - it.first() > 1 } + 1 // + 1 to account for first number in window that breaks the chain
}

private fun toDoubleArray(x: List<Int>): List<Double> {
    return x.map { it.toDouble() }
}

private fun processNumber(number: List<Double>, numbers: MutableSet<Double>) {
    for (op in OPERATION.values()) {
        // simulate brackets by first operating on first and second arg, then second and third, etc.
        for (x in 0 until number.lastIndex) {
            val y = x + 1
            val result = applyOperation(op, number, x, y)
            if (result.size == 1) { // we have reduced all numbers to a final result
                numbers.add(result[0])
            } else {
                processNumber(result, numbers)
            }
        }
    }
}

private fun applyOperation(op: OPERATION, number: List<Double>, x: Int, y: Int): List<Double> {
    val n1 = number[x]
    val n2 = number[y]
    val result = when (op) {
        OPERATION.PLUS -> n1 + n2
        OPERATION.MINUS -> n1 - n2
        OPERATION.MULT -> n1 * n2
        OPERATION.DIV -> n1 / n2
    }

    //val tab = "  ".repeat(4 - number.size)
    //System.out.printf("$tab apply $op on $number ($x, $y) = $result  \n")

    // create a new number based on number:
    // numbers in position x and y are placed with result:
    // [ <everything left of x>, number[x], number[y], <everything right of y> ]
    // [ <everything left of x>, result              , <everything right of y> ]
    // returns new array that is 1 shorter than input array 'number'
    // println("$number x=$x y=$y result=$result")
    return number.subList(0, x) + result + number.subList(y + 1, number.size)
}

internal enum class OPERATION(private val sign: String) {
    PLUS("+"), MINUS("-"), MULT("*"), DIV("/");

    override fun toString(): String {
        return sign
    }
}

private fun testGetChainLength() {
    val x = listOf(1, 2, 3, 4, 8)
    println("$x -> ${getChainLength(x)}")

    val x2 = listOf(1, 2, 3, 4, 5, 9)
    println("$x2 -> ${getChainLength(x2)}")
}