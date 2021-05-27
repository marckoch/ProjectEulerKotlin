package pe120

// https://projecteuler.net/problem=120
fun main() {
    (3..1000).sumOf { a ->
        val max = 2 * a * ((a - 1) / 2)
        //println("a=$a max=$max")
        max
    }.let { println(it) }
}

