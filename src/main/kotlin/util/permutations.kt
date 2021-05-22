package util.permutations

import java.math.BigInteger

// https://rosettacode.org/wiki/Permutations#Kotlin
fun <T> permute(input: List<T>): List<List<T>> {
    if (input.size == 1) return listOf(input)

    val perms = mutableListOf<List<T>>()
    val head = input[0]
    val tail = input.drop(1)
    for (perm in permute(tail)) {
        for (i in 0..perm.size) {
            val newPerm = perm.toMutableList()
            newPerm.add(i, head)
            perms.add(newPerm)
        }
    }
    return perms
}

fun main() {
    val input = listOf('a', 'b', 'c', 'd')
    val perms = permute(input)
    println("There are ${perms.size} permutations of $input, namely:\n")

    perms.forEach { println(it) }


    permute(listOf("A", "B", "C")). forEach { println(it) }

    permute(listOf(BigInteger.ZERO, BigInteger.ONE, BigInteger.TWO, BigInteger.TEN)). forEach { println(it) }
}