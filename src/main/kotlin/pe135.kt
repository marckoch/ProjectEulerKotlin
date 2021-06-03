package pe135

// https://projecteuler.net/problem=135
// x,y,z form arithmetic progression, that means z-y = d and y-x=d
fun main() {
    val n = 1_000_000
    val count = IntArray(n + 1)

    // substitute u = 3*d - z  v = d + z

    for (u in 1..n) {
        var v = 1
        while (u * v <= n) {
            if ((u + v) % 4 == 0 && // so d is an integer
                3 * v > u &&  // or z would be negative
                (3 * v - u) % 4 == 0  // so z is an integer
            ) {
//                val d = (u + v) / 4
//                val z = (3 * v - u) / 4
//
//                val x = z + 2 * d
//                val y = z + d
//                val n = u * v
//                println("$u $v > d=$d  $x^2 - $y^2 - $z^2 = $n")
                count[u * v]++
            }
            v++
        }
    }

    // println(count.contentToString())

    count.count { it == 10 }.let { println(it) }
}