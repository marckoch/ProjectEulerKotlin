package util.rotations

// note: the conversion to number has be done by caller, because he must handle the case were a "0" is the first digit

// (123456) -> [123456, 234561, 345612, 456123, 561234, 612345]
fun rightRotationsOf(n: String) = generateSequence(n) { if (rotateNumberRight(it) == n) null else rotateNumberRight(it) }

// (123456) -> [123456, 612345, 561234, 456123, 345612, 234561]
fun leftRotationsOf(n: String) = generateSequence(n) { if (rotateDigitsLeft(it) == n) null else rotateDigitsLeft(it) }

// push each digit one to its left, move first digit up to last
// e.g.
// 1234 -> 2341
// 2341 -> 3421
// note: the conversion to number has be done by caller, because he must handle the case were a "0" is the first digit
fun rotateDigitsLeft(n: String): String {
    return n.substring(1) + n.first()
}

// push each digit one to its right, move last digit up to front
// e.g.
// 1234 -> 4123
// 4123 -> 3412
// note: the conversion to number has be done by caller, because he must handle the case were a "0" is the first digit
fun rotateNumberRight(n: String): String {
    return n.last() + n.substring(0, n.length - 1)
}

fun main() {
    leftRotationsOf("123456").toList().let { println(it) }
    leftRotationsOf("11").toList().let { println(it) }
    leftRotationsOf("102").toList().let { println(it) }

    rightRotationsOf("123456").toList().let { println(it) }
}
