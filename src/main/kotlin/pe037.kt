import util.isPrime

// https://projecteuler.net/problem=37
fun main() {
    val limit = 1_000_000
    val prime = isPrime(limit)

    (11..limit step 2)
        .filter { isRightTruncatablePrime(it, prime) && isLeftTruncatablePrime(it, prime) }
        .also { println(it) }
        .sum()
        .let { println(it) }
}

fun isRightTruncatablePrime(n: Int, prime: BooleanArray): Boolean {
    // check if right digits can be removed and leave a prime
    var lastDigitRemoved = n
    while (lastDigitRemoved > 0 && prime[lastDigitRemoved]) {
        lastDigitRemoved /= 10
    }

    // the whole number is consumed, that means all created numbers were primes
    return lastDigitRemoved == 0
}

fun isLeftTruncatablePrime(n: Int, prime: BooleanArray): Boolean {
    // check if left digits can be removed and leave a prime
    var firstDigitRemoved = n
    while (firstDigitRemoved > 0 && prime[firstDigitRemoved]) {
        firstDigitRemoved = dropFirstDigit(firstDigitRemoved)
    }

    // the whole number is consumed, that means all created numbers were primes
    return firstDigitRemoved == 0
}

fun dropFirstDigit(n: Int): Int {
    val str = n.toString()
    return if (str.length > 1) str.substring(1).toInt() else 0
}