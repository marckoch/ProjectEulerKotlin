package pe174

// https://projecteuler.net/problem=174
fun main() {
    val limit = 1_000_000

    solve(limit)
}

fun solve(tiles: Int) {
    val count = IntArray(tiles+1) {0}
    val lMax = (tiles / 4) - 1
    val nMax = sqrt(tiles.toLong() / 4).toInt()
    println("tiles=$tiles, lMax=$lMax, nMax=$nMax")

    for (n in 1 until nMax) {
        for (l in 1..lMax) {
            val t = 4 * n * (n+l)
            if (t > tiles) continue
            count[t]++
        }
    }
//    count.toList()
//        .withIndex()
//        .filter { indexedValue -> indexedValue.value in 1..10 }
//        .forEach { println(it) }

    count.toList()
        .filter { it in 1..10 }
        .count()
        .let { println(it) }
}

fun sqrt(n: Long): Long {
    return kotlin.math.sqrt(n.toDouble()).toLong()
}