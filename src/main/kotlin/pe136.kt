package pe136

import kotlin.system.measureTimeMillis

// https://projecteuler.net/problem=136
fun main() {
    measureTimeMillis { solve() }.let { println("took $it") }
}

// 2544559
fun solve() {
    val maxN = 50_000_000

    val solutions = IntArray(maxN + 1) { 0 }
    for (y in 1..maxN) {
        for (d in y/4 + 1 until y) {
            val n = y * (4 * d - y)
            if (n in 1..maxN) {
                solutions[n]++
            }
            if (n <= 0) {
                break
            }
            if (n > maxN) {
                continue
            }
        }
    }

    var count = 0
    for (i in solutions) {
        if (i == 1) {
            count++
        }
    }
    println(count)
}

// used to find bug in my code
// https://github.com/iandioch/solutions/blob/master/project_euler/136/Solution.java
internal object Solution {
    @JvmStatic
    fun main(args: Array<String>) {
        val n = IntArray(50000000)
        for (z in 1 until n.size) {
            for (d in z / 3 + 1 until n.size) {
                val m = 2 * d * z + 3 * d * d - z * z
                if (m < 0) {
                    //System.out.println(z + " " + d);
                    break
                }
                if (m >= n.size) {
                    continue
                }
                n[m] += 1
            }
        }
        var ans = 0
        for (i in n) {
            if (i == 1) {
                ans++
            }
        }
        println(ans)
    }
}