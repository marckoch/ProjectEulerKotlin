// https://projecteuler.net/problem=22
fun main() {
    val fileContent = object {}.javaClass.getResource("pe022_names.txt")!!.readText()

    fileContent
        .split(",")
        .map { it.replace("\"", "") }
        .sorted()
        .mapIndexed { index, s -> (index + 1) * score(s) }
        .sum()
        .let { println(it) }
}

fun score(name: String): Int {
    return name
        .map { c -> (Character.getNumericValue(c) - 9) }  // - 9, because Character.getNumericValue("A") == 10, etc.
        .sum()
}