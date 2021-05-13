package pe084

import java.util.*

// https://projecteuler.net/problem=84
// monte carlo simulation, monopoly
fun main() {
    val diceSize = 4 // change to 6 for real monopoly
    solveProjectEuler(diceSize)
}

private const val DIM = 40
private const val ROUNDS = 10_000_000
private val random = Random(System.currentTimeMillis())

// our board
private const val POS_GO = 0
private const val POS_R1 = 5
private const val POS_R2 = POS_R1 + 10
private const val POS_R3 = POS_R2 + 10
private const val POS_R4 = POS_R3 + 10
private const val POS_JAIL = 10
private const val POS_FP = 20
private const val POS_C1 = POS_JAIL + 1
private const val POS_FREE_PARKING = 20
private const val POS_E3 = POS_FREE_PARKING + 4
private const val POS_H2 = 39
private const val POS_U1 = POS_JAIL + 2
private const val POS_U2 = POS_R3 + 3
private const val POS_GO2JAIL = 30
private const val POS_CH1 = POS_R1 + 2
private const val POS_CH2 = POS_FP + 2
private const val POS_CH3 = POS_R4 + 1
private const val POS_CC1 = 2
private const val POS_CC2 = POS_R2 + 2
private const val POS_CC3 = POS_GO2JAIL + 3

fun solveProjectEuler(diceSize: Int) {
    val board = IntArray(DIM)
    var pos = 0

    // println(board.contentToString())

    // we roll the dice
    repeat(ROUNDS) {
        val dice1 = rollDice(diceSize)
        val dice2 = rollDice(diceSize)

        pos = (pos + dice1 + dice2) % DIM

        pos = processSpecialFields(pos)

        board[pos]++
    }

    // println(board.contentToString())

    showTop5Fields(board)
    showResult(board)
}

// we handle special fields (like "go to jail", etc.) that might change the position
private fun processSpecialFields(pos: Int): Int {
    return when (pos) {
        POS_CC1, POS_CC2, POS_CC3 -> drawCommunityCard(pos)
        POS_CH1, POS_CH2, POS_CH3 -> drawChanceCard(pos)
        POS_GO2JAIL -> POS_JAIL
        else -> pos
    }
}

private fun drawCommunityCard(pos: Int): Int {
    return when (drawCommunityCard()) {
        0 -> POS_GO
        1 -> POS_JAIL
        else -> pos // other card, no change in pos
    }
}

private fun drawChanceCard(pos: Int): Int {
    return when (drawChanceCard()) {
        0 -> POS_GO
        1 -> POS_JAIL
        2 -> POS_C1
        3 -> POS_E3
        4 -> POS_H2
        5 -> POS_R1
        6, 7 -> goToNextRailroad(pos)
        8 -> goToNextUtility(pos)
        9 ->  goBackThreeFields(pos)
        else -> pos
    }
}

private fun goToNextRailroad(pos: Int): Int {
    return when (pos) {
        POS_CH1 -> POS_R2
        POS_CH2 -> POS_R3
        POS_CH3 -> POS_R1
        else -> throw IllegalStateException("illegal position: $pos is not a chance field!")
    }
}

private fun goToNextUtility(pos: Int): Int {
    return when (pos) {
        POS_CH1 -> POS_U1
        POS_CH2 -> POS_U2
        POS_CH3 -> POS_U1
        else -> throw IllegalStateException("illegal position: $pos is not a chance field!")
    }
}

private fun goBackThreeFields(pos: Int): Int {
    return (pos - 3) % DIM
}

private fun rollDice(diceSize: Int): Int {
    return random.nextInt(diceSize) + 1
}

private fun drawCommunityCard(): Int {
    return random.nextInt(16)
}

private fun drawChanceCard(): Int {
    return random.nextInt(16)
}

private fun showTop5Fields(board: IntArray) {
    board.withIndex()
        .sortedBy { it.value }
        .reversed()
        .take(5)
        .map { "field ${it.index} count=${it.value} freq=${it.value.toDouble() * 100.0 / ROUNDS} %" }
        .forEach { println(it) }
}

// show result as requested in project euler problem statement
private fun showResult(board: IntArray) {
    board.withIndex()
        .sortedBy { it.value }
        .reversed()
        .take(3)
        .map { indexedValue -> indexedValue.index }
        .joinToString("")
        .let { println(it) }
}