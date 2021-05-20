package pe098

// https://projecteuler.net/problem=98
fun main() {
    val fileContent = object {}.javaClass.getResource("pe098_words.txt")!!.readText()
    val words = fileContent.split(",")
        .map { s -> s.substring(1, s.length - 1) } // remove " from beginning and end
    // println(words)

    solveProjectEuler(words)
}

fun solveProjectEuler(words: List<String>) {
    val maxLength = words.map { it.length }.maxOrNull() ?: 0
    println("longest word is $maxLength chars long")

    val anagrams = words
        .groupBy { fingerPrint(it) }
        .filter { (_, value) -> value.size > 1 }

//    anagrams.entries
//        .sortedByDescending { it.value[0].length }
//        .forEach { println(it) }

    //words.sortedByDescending { s -> s.length }
    //    .forEach {w -> println("${w.length} $w ${w.chars().distinct().count()}") }

    squaresByLength.putAll(
        generateSequence(0L) { n -> n + 1 }
            .map { i -> i * i }
            .map { l -> l.toString() }
            .takeWhile { s: String -> s.length < 10 }
            .groupBy { it.length }
    )

//    squaresByLength.forEach { (integer, strings) ->
//        println(integer.toString() + " " + strings.size + " " + strings)
//    }

    anagrams.forEach { (_, strings) ->
        processWord(strings)
    }

    squares.maxByOrNull { it }?.let { println(it) }
}

fun processWord(anagrams: List<String>) {
    anagrams.forEach { _ ->
        val word = anagrams[0]
        val partnerWord = anagrams[1]
        val mappings = findMappings(word)
        for (mapping in mappings) {
            val partnerNumber = applyMapping(partnerWord, mapping)
            if (squaresByLength[word.length]!!.contains(partnerNumber.toString())) {
                //println("$partnerNumber is square: " + sqrt(partnerNumber.toDouble()).roundToInt() + "^2")
                squares.add(partnerNumber)

                val number = applyMapping(word, mapping)
                //println(number.toString() + " is square: " + sqrt(number.toDouble()).roundToInt() + "^2")
                squares.add(number)
            }
        }
    }
}

// try to match string to given number and find a mapping
// if no mapping exists null is returned
fun getMapping(str: String, number: String): CharArray? {
    val mapping = CharArray(10)
    for (i in number.indices) {
        val digit = Character.getNumericValue(number[i])
        val char1 = str[i]
        if (mapping[digit] == NULL_CHAR) {
            mapping[digit] = char1
        } else {
            //System.err.println(String.format("str=%s, number=%s: no mapping possible!", str, number));
            return null
        }
    }

    //System.out.println(String.format("str=%s, square=%s (=%s^2): mapping=%s", str, number, Math.round(Math.sqrt(Integer.parseInt(number))), String.valueOf(mapping)));
    return mapping
}

// for given string find all mappings that produce a square number of same length as the string
// e.g. CARE -> 1296 with mapping A=2, C=1, R=9, E=6, mapping is given as char[] [,C,A,,,,E,,,R]
// index of char in char[] defines the number char is mapped to!
fun findMappings(str: String): List<CharArray> {
    val squares = squaresByLength[str.length]!!
    val mappings: MutableList<CharArray> = ArrayList()
    for (square in squares) {
        val mapping = getMapping(str, square)
        if (mapping != null) {
            mappings.add(mapping)
        }
    }
    return mappings
}

// apply given mapping to string and convert it to a number
fun applyMapping(str: String, mapping: CharArray): Int {
    var result = str
    for (i in mapping.indices) {
        if (mapping[i] != NULL_CHAR) {
            result = result.replace(mapping[i], Character.forDigit(i, 10))
        }
    }
    //System.out.println(String.format("applyMapping: str=%s with mapping=%s becomes %s", str, String.valueOf(mapping), result));
    return result.toInt()
}

// return the chars sorted
fun fingerPrint(input: String): String {
    return input.toCharArray().sorted().joinToString("")
}

private const val NULL_CHAR = '\u0000'

private val squaresByLength: MutableMap<Int, List<String>> = HashMap()

private val squares: MutableList<Int> = ArrayList()