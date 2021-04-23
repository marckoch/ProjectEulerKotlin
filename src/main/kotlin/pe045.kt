import util.*

// https://projecteuler.net/problem=45
fun main() {
    var i = 143L
    while (true) {
        i++
        val h = hexagonal(i)
        if (isPentagonal(h) && isTriangular(h)) {
            println(h)
            triangleNumbers.withIndex().first { indexedValue -> indexedValue.value == h }.let { println("triangle:   $it") }
            pentagonalNumbers.withIndex().first { indexedValue -> indexedValue.value == h }.let { println("pentagonal: $it") }
            hexagonalNumbers.withIndex().first { indexedValue -> indexedValue.value == h }.let { println("hexagonal:  $it") }
            break
        }
    }
}