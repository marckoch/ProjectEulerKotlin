package pe173

// https://projecteuler.net/problem=173
fun main() {
    val limit = 1_000_000

    solve(limit)
}

// to build a tiling for hole l and n layers around it you need
//   (l + 2 * n) ^2 - l^2
//  tiles
// solving this gives
//   tiles = 4 * n * (n + l)
// if number of tiles is given T, maximum l will be reached for n=1
// solving gives: l_Max = tiles / 4 - 1
// maxN for given tiles is reached when l = 1, solving gives nMax = approx. sqrt(tiles / 4)
fun solve(tiles: Int) {
    val lMax = (tiles / 4) - 1
    val nMax = sqrt(tiles.toLong() / 4)
    println("tiles=$tiles, lMax=$lMax, nMax=$nMax")

    var count = 0L
    for (n in 1..nMax) {
        count += (tiles / (4 * n)) - n
    }
    println(count)
}

fun sqrt(n: Long): Long {
    return kotlin.math.sqrt(n.toDouble()).toLong()
}