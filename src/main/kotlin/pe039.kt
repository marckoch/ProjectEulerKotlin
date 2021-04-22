// https://projecteuler.net/problem=39
fun main() {
    val perimeter = 1000
    val cMax = perimeter / 2 // when a == c == cMax, b == 0  -> a + 0 + c = 2c = p  ->  p / 2
    pythagoreanTriples(cMax)
        .groupBy { triple: Triple<Int, Int, Int> -> triple.first + triple.second + triple.third }
        // .also { println(it) }
        .maxByOrNull { entry -> entry.value.size }
        .let { println(it) }
}

// create pythagoreanTriplet up to a maximum length of c
fun pythagoreanTriples(cMax: Int) =
    (1..cMax).flatMap { a ->
        (a..cMax).flatMap { b ->
            (b..cMax).filter { c ->
                a * a + b * b == c * c
            }.map { Triple(a, b, it) }
        }
    }

