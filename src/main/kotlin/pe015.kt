package pe015

import util.factorial.factorial

// https://projecteuler.net/problem=15
fun main() {
    // this is simple combinatorics
    // solution is
    // (20 + 20)!
    // ----------
    // 20! * 20!
    //
    // which can be reduced by canceling 20! on top and bottom:
    //
    // (40 * 30 * ... * 22 * 21)
    // -------------------------
    //       20!

    println(factorial(21..40) / factorial(20))
}