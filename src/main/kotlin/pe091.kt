package pe091

import util.gcd.gcd
import kotlin.math.min

// https://projecteuler.net/problem=91
fun main() {
    println(solve(2))
    println(solve(3))
    println(solve(50))
}

fun solve(N: Int): Int {
    var result = 0

    // for every triangle with right angle at 0/0,
    // Q lies on x-axis (n possible values), P lies on y-axis (also N values)
    result += N * N

    // for every triangle with right angle on x-axis,
    // Q lies on x-axis (n possible values), P lies straight above Q (also N values)
    result += N * N

    // for every triangle with right angle on y-axis
    // P lies on y-axis (n possible values), Q lies straight right from P (also N values)
    result += N * N

    // now check P wandering around in area (not on either axis)
    for (x in 1..N) {
        for (y in 1..N) {
            val factor = gcd(x, y)
            result += 2 * min(y * factor / x, (N - x) * factor / y) // *2 because of symmetry along diagonal
            println("$x $y ${min(y * factor / x, (N - x) * factor / y)}")
        }
    }
    return result
}
