package pe205

import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

// https://euler.stephan-brumme.com/205/
// https://projecteuler.net/problem=205
fun main() {
    solve()

//    measureTimeMillis { monteCarlo(100_000_000) }.let { println(it) }
}

fun solve() {
    // how often does cube throw each total sum?
    val cubeCount = getFrequencies(6, 6)
    println(cubeCount.contentToString())
    println(cubeCount.sum())

    // how often does pyramid throw each total sum?
    val pyramidCount = getFrequencies(9, 4)
    println(pyramidCount.contentToString())
    println(pyramidCount.sum())

    var pyramidWins = 0L
    var cubeWins = 0L
    var draw = 0L
    var total = 0L
    // now compare each combination of pyramid and cube wins
    for (i in pyramidCount.indices) {
        val p = pyramidCount[i]
        if (p > 0) {
            for (j in cubeCount.indices) {
                val c = cubeCount[j]
                if (c > 0) {
                    when {
                        i > j -> {
                            pyramidWins += p * c
                        }
                        i < j -> {
                            cubeWins += p * c
                        }
                        else -> {
                            draw += p * c
                        }
                    }
                    total += p * c
                }
            }
        }
    }
    val percentagePyramidWins = pyramidWins.toDouble() / total
    val percentageCubeWins = cubeWins.toDouble() / total
    val percentageDraws = draw.toDouble() / total
    println("pyramidWins=$pyramidWins, cubeWins=$cubeWins, draw=$draw, total=$total")
    println("pyramidWins=$percentagePyramidWins, cubeWins=$percentageCubeWins, draw=$percentageDraws, total=$total")

    BigDecimal.valueOf(percentagePyramidWins).setScale(7, RoundingMode.HALF_UP).let { println(it) }
}

fun getFrequencies(dices: Int, sides: Int): IntArray {
    val count = IntArray(dices * sides + 1)
    roll(dices, sides, count, 0)
    return count
}

private val random = Random(System.currentTimeMillis())
// private val random = Random(1L) // use this for deterministic (non random) results

// does not really produce the exact result
fun monteCarlo(games: Int) {
    var petesWins = 0
    var colinWins = 0
    var draw = 0
    repeat(games) {
        val pete = IntArray(9) { rollDice(4) }
        val colin = IntArray(6) { rollDice(6) }

        val peteSum = pete.sum()
        val colinSum = colin.sum()

        when {
            peteSum > colinSum -> {
                petesWins++
            }
            peteSum < colinSum -> {
                colinWins++
            }
            else -> draw++
        }
    }

    println("pete wins ${petesWins.toDouble() / games}, colin wins ${colinWins.toDouble() / games}, draw ${draw.toDouble() / games}")
}

private fun rollDice(diceSize: Int): Int {
    return random.nextInt(diceSize) + 1
}

// roll dices, store at count[x] how often the sum of all dices was exactly x
fun roll(dices: Int, sides: Int, count: IntArray, sum: Int = 0) {
    // rolled all dices, increment the sum's counter
    if (dices == 0) {
        count[sum]++
        return
    }

    // all combinations of a dice ...
    for (i in 1..sides) {
        roll(dices - 1, sides, count, sum + i)
    }
}