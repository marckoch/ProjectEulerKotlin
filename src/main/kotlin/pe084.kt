package pe084

import java.util.*

// https://projecteuler.net/problem=84
// monte carlo simulation, monopoly
fun main() {
    val diceSize = 4 // change to 6 for real monopoly
    solveProjectEuler(diceSize)
}

private val random = Random(System.currentTimeMillis())
// private val random = Random(1L) // use this for deterministic (non random) results

fun solveProjectEuler(diceSize: Int) {
    val game = Game()

    repeat(10_000_000) {
        val dice1 = rollDice(diceSize)
        val dice2 = rollDice(diceSize)

        game.move(dice1, dice2)
    }

    game.showTop5Fields()
    game.showResult()
}

private fun rollDice(diceSize: Int): Int {
    return random.nextInt(diceSize) + 1
}

// warning: we use ordinal, which seems OK here, because the board is fixed.
// no one will ever insert some random new field in the middle.
// the values of the enum automatically form an array, which is exactly what we need here.
class Game {
    enum class Field {
        GO, A1, CC1, A2, T1, R1, B1, CH1, B2, B3,
        JAIL, C1, U1, C2, C3, R2, D1, CC2, D2, D3,
        FP, E1, CH2, E2, E3, R3, F1, F2, U2, F3,
        GO2JAIL, G1, G2, CC3, G3, R4, CH3, H1, T2, H2
    }

    private val size = Field.values().size
    private var pos = 0
    private val frequencies = IntArray(size)

    fun move(dice1: Int, dice2: Int) {
        pos = (pos + dice1 + dice2) % size

        val newField = processSpecialFields(Field.values()[pos])
        pos = newField.ordinal

        frequencies[pos]++
    }

    // we handle special fields (like "go to jail", etc.) that might change the position
    private fun processSpecialFields(field: Field): Field {
        return when (field) {
            Field.CC1, Field.CC2, Field.CC3 -> drawCommunityCard(field)
            Field.CH1, Field.CH2, Field.CH3 -> drawChanceCard(field)
            Field.GO2JAIL -> Field.JAIL
            else -> field
        }
    }

    private fun drawCommunityCard(field: Field): Field {
        return when (drawCommunityCard()) {
            0 -> Field.GO
            1 -> Field.JAIL
            else -> field // other card, no change in pos
        }
    }

    private fun drawChanceCard(field: Field): Field {
        return when (drawChanceCard()) {
            0 -> Field.GO
            1 -> Field.JAIL
            2 -> Field.C1
            3 -> Field.E3
            4 -> Field.H2
            5 -> Field.R1
            6, 7 -> goToNextRailroad(field)
            8 -> goToNextUtility(field)
            9 ->  goBackThreeFields(field)
            else -> field
        }
    }

    private fun goToNextRailroad(field: Field): Field {
        return when (field) {
            Field.CH1 -> Field.R2
            Field.CH2 -> Field.R3
            Field.CH3 -> Field.R1
            else -> throw IllegalStateException("illegal position: $field is not a chance field!")
        }
    }

    private fun goToNextUtility(field: Field): Field {
        return when (field) {
            Field.CH1 -> Field.U1
            Field.CH2 -> Field.U2
            Field.CH3 -> Field.U1
            else -> throw IllegalStateException("illegal position: $field is not a chance field!")
        }
    }

    private fun goBackThreeFields(field: Field): Field {
        val position = field.ordinal
        val newPosition = position - 3 % size
        return Field.values()[newPosition]
    }

    private fun drawCommunityCard(): Int {
        return random.nextInt(16)
    }

    private fun drawChanceCard(): Int {
        return random.nextInt(16)
    }

    fun showTop5Fields() {
        val noOfRounds = frequencies.sum()
        frequencies.withIndex()
            .sortedBy { it.value }
            .reversed()
            .take(5)
            .map { "field ${it.index} count=${it.value} freq=${it.value.toDouble() * 100.0 / noOfRounds} %" }
            .forEach { println(it) }
    }

    // show result as requested in project euler problem statement
    fun showResult() {
        frequencies.withIndex()
            .sortedBy { it.value }
            .reversed()
            .take(3)
            .map { indexedValue -> indexedValue.index }
            .joinToString("")
            .let { println(it) }
    }
}