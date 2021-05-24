package pe109

import java.util.*

// https://projecteuler.net/problem=109
fun main() {
    val from = 1
    val to = 99 // project euler, set to 180 to see all 42336

    val darts = Stack<Field>()
    val finishes: MutableSet<String> = HashSet()
    for (i in from..to) {
        finish(i, darts, finishes)
    }
    println("there are ${finishes.size} ways to clear $to")
}

// recursive function
// `remaining` is the remaining score to clear
// `darts` are the darts thrown so far in this round
// `finishes`: here we collect this round if we finish it properly
private fun finish(remaining: Int, darts: Stack<Field>, finishes: MutableSet<String>) {
    if (remaining < 0) {
        //println("$darts bust!")
        return
    }
    if (darts.size == 3 && remaining > 0) { // 3 darts thrown but not finished
        //println("$darts did not finish, $remaining left")
        return
    }
    if (darts.size == 2 && remaining % 2 == 1) { // last throw must be double
        //println("$darts cannot finish odd $remaining")
        return
    }
    if (remaining == 0 && darts.lastElement().modifier != 2) {
        //println("$darts did not finish on double")
        return
    }

    // we did a proper finish
    if (remaining == 0) {
        val sum = darts.sumOf { it.value }
        println("$darts finished! ${darts.map { it.value }.joinToString("+")}=$sum")

        val finish = orderDarts(darts)
        finishes.add(finish)
        return
    }

    // we are not finished yet
    val limit = 25.coerceAtMost(remaining)
    for (dart in 1..limit) {
        if (dart in 21..24) { // there are no fields for 21 - 24
            continue
        }
        for (modifier in 1..3) { // single, double or treble value fields
            if (dart == 25 && modifier == 3) { // there is no T25 field!
                continue
            }
            val f = Field.of(modifier, dart)
            darts.push(f)
            finish(remaining - f.value, darts, finishes)
            darts.pop()
        }
    }
}

fun orderDarts(darts: List<Field>): String {
    // rearrange first two darts, to account for equal finishes (see problem description)
    if (darts.size == 3) {
        val lastDart = darts[2]
        val finish = darts.take(2).sorted() + lastDart
        return finish.toString()
    }

    val d = mutableListOf<Field>()
    d.addAll(darts)
    // add empty darts in front to compare with solution 2 (brumme), used during bug hunt
    while (d.size < 3) {
        d.add(0, Field.of(0, 0))
    }
    return d.toString()
}

class Field(val modifier: Int, private val nominalValue: Int) : Comparable<Field> {
    val value: Int
        get() = modifier * nominalValue

    override fun toString(): String {
        val name: String = when (modifier) {
            0 -> "0"
            1 -> "S"
            2 -> "D"
            3 -> "T"
            else -> throw IllegalStateException("illegal modifier: $modifier")
        }
        return (name + nominalValue)
    }

    override operator fun compareTo(other: Field): Int {
        val v = value - other.value
        if (v != 0) return v

        val m = modifier - other.modifier
        if (m != 0) return m

        return nominalValue - other.nominalValue
    }

    companion object {
        fun of(modifier: Int, nominalValue: Int): Field {
            return Field(modifier, nominalValue)
        }
    }
}