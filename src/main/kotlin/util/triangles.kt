package util

val triangleNumbers = generateSequence(1) { it + 1 }.map { it * (it + 1) / 2 }