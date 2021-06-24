package pe185

// TODO UNFINISHED
// https://projecteuler.net/problem=185
fun main() {
    val exampleHints = parseExampleHints()
    solve(exampleHints, 100_000)
    getFrequencies(exampleHints)

    val realHints = parseRealHints()
//    solve(realHints, 1e17.toInt())
//    getFrequencies(realHints)

//    val digits = listOf(Pair(9, 2), Pair(7, 0), Pair(3, 2), Pair(3, 1), Pair(5, 2), Pair(1, 1))
//    getFrequency(digits, 5)
    // 5846859647228557
//    check(digits("5846859647228557".toLong()), realHints).let { println(it) }
}

fun parseExampleHints(): List<Pair<IntArray, Int>> {
    val hints =
        """
        90342 ;2 correct
        70794 ;0 correct
        39458 ;2 correct
        34109 ;1 correct
        51545 ;2 correct
        12531 ;1 correct
    """.trimIndent()

    return parseHints(hints)
}

fun parseRealHints(): List<Pair<IntArray, Int>> {
    val hints =
        """
        5616185650518293 ;2 correct
        3847439647293047 ;1 correct
        5855462940810587 ;3 correct
        9742855507068353 ;3 correct
        4296849643607543 ;3 correct
        3174248439465858 ;1 correct
        4513559094146117 ;2 correct
        7890971548908067 ;3 correct
        8157356344118483 ;1 correct
        2615250744386899 ;2 correct
        8690095851526254 ;3 correct
        6375711915077050 ;1 correct
        6913859173121360 ;1 correct
        6442889055042768 ;2 correct
        2321386104303845 ;0 correct
        2326509471271448 ;2 correct
        5251583379644322 ;2 correct
        1748270476758276 ;3 correct
        4895722652190306 ;1 correct
        3041631117224635 ;3 correct
        1841236454324589 ;3 correct
        2659862637316867 ;2 correct
    """.trimIndent()
    return parseHints(hints)
}

fun parseHints(s: String): List<Pair<IntArray, Int>> {
    return s.lines().map { parseHint(it) }
}

fun parseHint(line: String): Pair<IntArray, Int> {
    val parts = line
        .replace("correct", "")
        .split(";")
    return Pair(digits(parts[0].trim().toLong()), parts[1].trim().toInt())
}

fun getFrequencies(hints: List<Pair<IntArray, Int>>) {
    for (i in hints[0].first.indices) {
        val digits = hints.map { pair -> Pair(pair.first[i], pair.second) }
        println("digit: $i")
        getFrequency(digits, hints[0].first.size)
    }
}

fun getFrequency(digits: List<Pair<Int, Int>>, length: Int) {
    val digitsWithZero = digits.filter { pair -> pair.second == 0 }.map { pair -> pair.first }
    println("digitsWithZero = $digitsWithZero")

    digits
        .asSequence()
        .filter { pair -> !digitsWithZero.contains(pair.first) }
        .map { Pair(it.first, it.second.toDouble() / length) }
        .groupBy { pair -> pair.first }
        .map { entry -> Pair(entry.key, entry.value.sumOf { pair -> pair.second }) }
        .sortedByDescending { pair -> pair.second }
        .toList()
        .forEach { println(it) }
}

fun solve(hints: List<Pair<IntArray, Int>>, limit: Int) {
    (1L until limit).asSequence()
        .map { digits(it) }
        .first { check(it, hints) }
        .toList()
        .let { println(it) }
}

// return true if guess meets all given hints, else false
fun check(guess: IntArray, hints: List<Pair<IntArray, Int>>): Boolean {
    return hints.all { countMatches(guess, it.first) == it.second }
}

// count how many digits match in given guess with given hint
// diff( [3,9,5,4,2] , [9,0,3,4,2] ) -> 2 because last 2 digits (4 and 2) match in both
fun countMatches(guess: IntArray, hint: IntArray): Int {
    return guess.zip(hint)
        .map { it.first - it.second }
        .count { it == 0 }
}

private fun digits(n: Long): IntArray {
    return n.toString().toCharArray().map { it - '0' }.toIntArray()
}