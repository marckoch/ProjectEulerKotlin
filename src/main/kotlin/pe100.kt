package pe100

// https://projecteuler.net/problem=100
fun main() {
    solve(1_000_000_000_000L).let { println(it) }
}

fun solve(N: Long): Long {
    var b: Long = 15
    var n: Long = 21

    // we must solve the equation
    // b   b - 1   1
    // - * ----- = -
    // n   n - 1   2
    //
    // we can find iterative solutions here:
    // https://www.alpertron.com.ar/QUAD.HTM (enter a=2, b=0, c=-1, d=-2, e=1, f=0)
    //
    // x_n+1 = 3 x_n + 2 y_n - 2
    // y_n+1 = 4 x_n + 3 y_n - 3
    while (n < N) {
        val nextB = 3 * b + 2 * n - 2
        val nextN = 4 * b + 3 * n - 3
        println("blue=$nextB red=${nextN - nextB} total=$nextN")
        b = nextB
        n = nextN
    }
    return b
}