package pe069

import util.primes.primes

// https://projecteuler.net/problem=69
// https://en.wikipedia.org/wiki/Euler's_totient_function#Euler's_product_formula
fun main() {
    var product = 1L
    // multiply all primes until product exceeds 1_000_000
    for (p in primes()) {
        if (product * p > 1_000_000) break
        product *= p
    }
    println(product)
}