package pe010

// https://projecteuler.net/problem=10
import util.primes.primes

fun main() {
    println(primes().takeWhile { it < 2_000_000 }.sum())
}