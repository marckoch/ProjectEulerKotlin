package pe068

import util.permutations.permute

// https://projecteuler.net/problem=68
fun main() {
    // val numbers = (1..6).toList() // 3-gon   see initial example at project euler
    val numbers = (1..10).toList()  // 5-gon

    solve(numbers)
}

fun solve(numbers: List<Int>) {
    // - we have 2*n numbers to fill a n-gon ring
    // - we permute all 2*n numbers
    // - we split this list in half, first half are the outer numbers, rest is inner numbers
    // - the smallest number of the outer numbers is starting point (satisfying: "numerically lowest external node")
    // - we fill the ring and filter all rings that match (e.g. all its lines have the same sum)
    // - we find the combination with maximum digits

    permute(numbers).asSequence()
        .map {
            split(it)
        }.filter {
            val outer = it.first
            // we only process when first element of outer is minimum of outer  ("numerically lowest external node" in problem)
            outer.first() == outer.minOrNull()
        }.map {
            buildRing(it.first, it.second)
        }.filter {
            getRingSum(it) > 0
        }.map {
            ringDigits(it)
        }.maxOrNull()
        .let { println(it) }
}

fun split(list: List<Int>): Pair<List<Int>, List<Int>> {
    val chunked = list.chunked(list.size / 2)
    val outer = chunked[0]
    val inner = chunked[1]
    return Pair(outer, inner)
}

// fill the lines of the ring
fun buildRing(outer: List<Int>, inner: List<Int>): List<List<Int>> {
    val ring = outer.indices.map { i ->
        listOf(outer[i], inner[i], inner[(i + 1) % outer.size])
    }.toList()
    // println("$outer  $inner  -> $ring  ${getRingSum(ring)}")
    return ring
}

// check if all lines of this ring have the same sum, if yes return this sum else 0
fun getRingSum(ring: List<List<Int>>): Int {
    if (ring.map { line -> line.sum() }.distinct().count() == 1) {
        return ring[0].sum()
    }
    return 0
}

fun ringDigits(ring: List<List<Int>>): String {
    return ring.joinToString("") { list ->
        list.joinToString("") { i -> i.toString() }
    }
}