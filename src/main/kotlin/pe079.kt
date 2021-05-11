package pe079

// https://projecteuler.net/problem=79
fun main() {
    val fileContent = object {}.javaClass.getResource("pe079_keylog.txt")!!.readText()

    solve(fileContent.lines())
        .let { println(it) }
}

private fun solve(logins: List<String>): String {
    val distinctDigits = logins
        .map { s -> s.toCharArray().toList() }
        .flatten()
        .distinct()

    val sortedDigits = distinctDigits.sortedWith(CompareDigits(logins))

    return sortedDigits.joinToString("")
}

// special comparator that compares the digits according to the given login strings
class CompareDigits(private val logins: List<String>) : Comparator<Char> {
    override fun compare(o1: Char, o2: Char): Int {
        for (login in logins) {
            val index1 = login.indexOf(o1)
            val index2 = login.indexOf(o2)
            if (index1 >= 0 && index2 >= 0) {
                // System.err.println("$o1 $o2 ${index1 - index2}")
                return index1 - index2
            }
        }
        // System.err.println("$o1 $o2 0")
        return 0
    }
}