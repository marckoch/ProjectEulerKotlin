package pe075

import util.gcd.gcd

// https://projecteuler.net/problem=75
fun main() {
    buildPerimeterFrequency(1_500_000)
        .count { i -> i == 1 }
        .let { println(it) }
}

// we create pythagorean triplets according to this algorithm:
// https://en.wikipedia.org/wiki/Tree_of_primitive_Pythagorean_triples#Alternative_methods_of_generating_the_tree
// than we check how often each perimeter can be created
fun buildPerimeterFrequency(l: Int): IntArray {
    val freq = IntArray(l+1)
    var m = 1
    while (2 * m * m <= l) {
        for (n in 1 until m) {
            if (m % 2 == 1 && n % 2 == 1) // not both odd!
            {
                continue
            }
            if (gcd(m, n) > 1) { // m and n are coprime
                continue
            }

            // a = m*m - n*n
            // b = 2*m*n
            // c = m*m + n*n
            // --> a+b+c = perimeter = 2*m*(m+n)
            val perimeter = 2 * m * (m + n)

            // when a^2+b^2 == c^2  --> has perimeter = a + b + c
            // then (k*a)^2 + (k*b)^2 = (k*c)^2 --> perimeter is k * (a + b + c) = k * p
            var k = 1
            while (k * perimeter <= l) {
                freq[k * perimeter]++
                k++
            }
        }
        m++
    }
    return freq
}
