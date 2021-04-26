package pe033

import util.fraction.Fraction
import util.fraction.reduceFraction

// https://projecteuler.net/problem=33
fun main() {
    var top = 1
    var bottom = 1

    // case 1: we cancel the leading digit
    // 10*x + y   y
    // -------- = -
    // 10*x + z   z
    for (x in 1..9) {
        for (y in 1..9) {
            for (z in y+1..9) {  // z > y !
                if (z * (10 * x + y) == y * (10 * x + z)) {
                    println("match in case1:")
                    println("$x$y   $y")
                    println("-- = -")
                    println("$x$z   $z")

                    top *= y
                    bottom *= z
                }
            }
        }
    }

    // case 2: we cancel the last digit
    // 10*x + y   x
    // -------- = -
    // 10*z + y   z
    for (x in 1..9) {
        for (y in 1..9) {
            for (z in x+1..9) {  // z > y !
                if (z * (10 * x + y) == x * (10 * z + y)) {
                    println("match in case2:")
                    println("$x$y   $x")
                    println("-- = -")
                    println("$z$y   $z")

                    top *= x
                    bottom *= z
                }
            }
        }
    }

    // case 3: we cancel the top leading digit with the bottom right digit
    // 10*x + y   y
    // -------- = -
    // 10*z + x   z
    for (x in 1..9) {
        for (y in 1..9) {
            for (z in y+1..9) {  // z > y !
                if (z * (10 * x + y) == y * (10 * z + x)) {
                    println("match in case3:")
                    println("$x$y   $y")
                    println("-- = -")
                    println("$z$x   $z")

                    top *= y
                    bottom *= z
                }
            }
        }
    }

    // case 4: we cancel the top right digit with the bottom left digit
    // 10*x + y   x
    // -------- = -
    // 10*y + z   z
    for (x in 1..9) {
        for (y in 1..9) {
            for (z in x+1..9) {  // z > y !
                if (z * (10 * x + y) == x * (10 * y + z)) {
                    println("match in case4:")
                    println("$x$y   $x")
                    println("-- = -")
                    println("$y$z   $z")

                    top *= x
                    bottom *= z
                }
            }
        }
    }

    println(reduceFraction(Fraction(top.toLong(), bottom.toLong())).bottom)
}