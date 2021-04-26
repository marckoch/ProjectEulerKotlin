package util.cartesianProduct

// build cartesian product of two collections
// e.g. (A, B, C) x (1, 2) -> (A1, A2, B1, B2, C1, C2)
fun <T, U> cartesianProductOf(left: Collection<T>, right: Collection<U>): List<Pair<T, U>> {
    return left.flatMap { leftElem -> right.map { rightElem -> leftElem to rightElem } }
}

fun main() {
    cartesianProductOf(setOf("A", "B", "C", "D"), listOf(1,2,3))
        .forEach { println(it) }
}