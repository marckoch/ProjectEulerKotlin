package pe155

import util.fraction.Fraction
import util.fraction.reduceFraction
import kotlin.system.measureTimeMillis

// https://oeis.org/A153588
// https://euler.stephan-brumme.com/155/
fun main() {
    val limit = 19
    measureTimeMillis { solve(limit) }.let { println(it) }
}

// pretty slow (2min) but gets the job done :-)
// basic idea: any circuit can be divided in two sub circuit, each sub circuit can be combined serial or parallel
fun solve(limit: Int) {
    val capacities: Array<MutableSet<Fraction<Long>>> = Array(limit + 1) { HashSet() }

    // it all starts with one little capacitor
    capacities[1].add(Fraction(1,1))

    for (totalSize in 2..limit) {
        for (sizeA in 1..totalSize/2) {
            val sizeB = totalSize - sizeA

            for(capA in capacities[sizeA]) {
                for (capB in capacities[sizeB]) {
                    val parallel = add(capA, capB)
                    capacities[totalSize].add(parallel)

                    val serial = add(capA.invert(), capB.invert()).invert()
                    capacities[totalSize].add(serial)
                }
            }
        }
    }

    val dist = IntArray(capacities.size)
    for (i in capacities.indices) {
        val distinctCapas = HashSet<Fraction<Long>>()
        capacities.take(i).forEach { distinctCapas.addAll(it) }
        dist[i] = distinctCapas.count()
    }
    println(dist.contentToString())
    println(dist[limit])
}

fun add(a: Fraction<Long>, b: Fraction <Long>): Fraction<Long> {
    val top = a.top * b.bottom + b.top * a.bottom
    val bottom = a.bottom * b.bottom
    return reduceFraction(Fraction(top , bottom))
}