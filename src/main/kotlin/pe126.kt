package pe126

// https://projecteuler.net/problem=126
fun main() {
    println(cover(1, 1, 1, 1))
    println(cover(1, 1, 1, 2))

    println(cover(3, 2, 1, 1))
    println(cover(3, 2, 1, 2))
    println(cover(3, 2, 1, 3))
    println(cover(3, 2, 1, 4))

    println(cover(5, 1, 1, 1))
    println(cover(5, 3, 1, 1))
    println(cover(7, 2, 1, 1))
    println(cover(11, 1, 1, 1))
    println(cover(5, 1, 1, 2))

    val solutions: MutableMap<Int, Int> = HashMap()  // cubes to count
    val cubeLimit = 40000 // just a guess :-(

    // we brute force it, we just keep building cubes and count how often cubes are occurring
    var x = 1
    while (cover(x, x, x, 1) < cubeLimit) {
        var y = x
        while (cover(x, y, y, 1) < cubeLimit) {
            var z = y
            while (cover(x, y, z, 1) < cubeLimit) {
                var layer = 1
                while (cover(x, y, z, layer) < cubeLimit) {
                    val cubes = cover(x, y, z, layer)
                    // println("$x $y $z $layer >> $cubes")
                    solutions.putIfAbsent(cubes, 0)
                    solutions[cubes] = solutions[cubes]!! + 1
                    layer++
                }
                z++
            }
            y++
        }
        x++
    }
    // println(solutions)

    // there are multiple solutions that occur 1000 times, we need the one with minimal cubes
    solutions.entries
        .filter { it.value == 1000 }
        .minByOrNull { it.key }
        .let { println(it)}
}

fun cover(x: Int, y: Int, z: Int, layer: Int): Int {
    return 2 * (x * y + y * z + z * x) + 4 * (x + y + z + layer - 2) * (layer - 1)
}