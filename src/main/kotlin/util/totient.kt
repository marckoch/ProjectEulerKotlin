package util.totient

// Computes and prints totient of all numbers smaller than or equal to n.
// https://www.geeksforgeeks.org/eulers-totient-function-for-all-numbers-smaller-than-or-equal-to-n/
fun computeTotient(N: Int): List<Int> {
    val phi = IntArray(N + 1) { it } // indicates not evaluated yet and initializes for product formula.

    // Compute other Phi values
    for (p in 2..N) {
        // If phi[p] is not computed already, then number p is prime
        if (phi[p] == p) {
            // Phi of a prime number p is always equal to p-1.
            phi[p] = p - 1

            // Update phi values of all multiples of p
            var i = 2 * p
            while (i <= N) {
                // Add contribution of p to its multiple i by multiplying with (1 - 1/p)
                phi[i] = phi[i] / p * (p - 1)
                i += p
            }
        }
    }
    return phi.toList()
}

fun main() {
    val totient = computeTotient(100)

    println(totient)

    // totient.forEachIndexed { index, t -> println("Totient of $index is $t") }

    computeTotient(87109).last().let { println(it) }
}