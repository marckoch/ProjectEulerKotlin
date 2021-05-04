package pe071

import util.fraction.Fraction
import util.fraction.reduceFraction

// https://projecteuler.net/problem=71
fun main() {
    val target = Fraction(3L,7)

    // https://www.mathblog.dk/project-euler-71-proper-fractions-ascending-order/
    var optimum = Fraction(0L,1)
    val limit = 1_000_000

    for (q in limit downTo 3) {
        val p = (target.top * q - 1) / target.bottom

        //val f = Fraction(p,q)
        //System.err.println("$f  ${f.value()}")

        if (p * optimum.bottom > optimum.top * q) {
            optimum = Fraction(p, q.toLong())
        }
    }
    println("$optimum")
}

// naive approach, too slow for large n
fun solve(n: Long, target: Fraction<Long>): Fraction<Long> {
    return (1L..n).flatMap { d ->
        (1L until d).map { n -> reduceFraction(Fraction(n, d)) }
    }.filter { it.top * target.bottom < it.bottom * target.top }
        .toSortedSet(compareBy { fraction -> fraction.value() })
        .last()
}