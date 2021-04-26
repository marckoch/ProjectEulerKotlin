package pe007

// https://projecteuler.net/problem=7
import util.primes.primes

fun main() {
    println(primes().take(10_001).last())
}