package pe059

// https://projecteuler.net/problem=59
fun main() {
    val fileContent = object {}.javaClass.getResource("pe059_cipher.txt")!!.readText()

    val encoded = fileContent
        .split(",")
        .map { s -> s.toInt() }
        .toIntArray()

    for (x in 'a'..'z') {
        for (y in 'a'..'z') {
            for (z in 'a'..'z') {
                val decodedMsg = decode(encoded, charArrayOf(x, y, z))
                if (isValid(decodedMsg)) {
                    println(decodedMsg)
                    println(decodedMsg.sumBy { it.toInt() })
                    return
                }
            }
        }
    }
}

fun decode(encoded: IntArray, password: CharArray): CharArray {
    val decodedMsg = CharArray(encoded.size)
    for (i in encoded.indices) {
        decodedMsg[i] = (encoded[i] xor password[i % 3].toInt()).toChar()
    }
    return decodedMsg
}

// just try some of the most frequent words and hope for the best ... :-)
private fun isValid(msg: CharArray): Boolean {
    val s = msg.joinToString("")
    return (s.contains("the") && s.contains("and") && s.contains("that"))
}