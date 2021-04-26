package pe045

import util.hexagonal.hexagonal
import util.hexagonal.hexagonalNumbers
import util.pentagonal.isPentagonal
import util.pentagonal.pentagonalNumbers
import util.triangular.isTriangular
import util.triangular.triangularNumbers

// https://projecteuler.net/problem=45
fun main() {
    var i = 143L
    while (true) {
        i++
        val h = hexagonal(i)
        if (isPentagonal(h) && isTriangular(h)) {
            println(h)
            triangularNumbers
                .withIndex()
                .first { indexedValue -> indexedValue.value == h }
                .let { println("triangle:   $it") }
            pentagonalNumbers
                .withIndex()
                .first { indexedValue -> indexedValue.value == h }
                .let { println("pentagonal: $it") }
            hexagonalNumbers
                .withIndex()
                .first { indexedValue -> indexedValue.value == h }
                .let { println("hexagonal:  $it") }
            break
        }
    }
}