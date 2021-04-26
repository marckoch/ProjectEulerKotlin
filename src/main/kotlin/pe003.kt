package pe003

// https://projecteuler.net/problem=3
fun main() {
    println(getLargestPrimeFactor(600851475143))
}

private fun getLargestPrimeFactor(input: Long): Long {
    var n = input
    var max: Long = 0
    var i: Long = 2         // i = current divisor, we start with 2
    while (i * i <= n) {    // i can never exceed sqrt(n)

        while (n % i == 0L) {   // try to divide n by currentDivisor until it becomes impossible
            max = i
            n /= i
        }

        i++
    }
    return if (n > 1) n else max
}