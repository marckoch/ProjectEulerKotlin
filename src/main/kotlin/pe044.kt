package pe044

import util.pentagonal.isPentagonal
import util.pentagonal.pentagonalNumbers
import kotlin.math.abs

// https://projecteuler.net/problem=44
// brute force, but it is surprisingly fast :-)
fun main() {
    pentagonalNumbers.forEach { p -> // go up through all pentagonal numbers
        pentagonalNumbers
            .takeWhile { i -> i < p }  // go through all smaller pentagonal numbers
            .forEach {
                val sum = p + it
                val diff = abs(p - it)

                if (isPentagonal(sum) && isPentagonal(diff)) { // until we find our match
                    println("$p $it diff=${abs(p - it)}")
                    return
                }
            }
    }
}