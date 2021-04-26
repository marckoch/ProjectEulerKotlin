package pe054

import java.util.*
import kotlin.math.pow

// https://projecteuler.net/problem=54
// TODO: based on my old java solution, but LOTS of refactoring to be done here!
//  maybe introduce data class for `Card`? with attribute `rank` and `color`?
fun main() {
    val fileContent = object {}.javaClass.getResource("pe054_poker.txt")!!.readText()
    fileContent
        .lines()
        .map { determineWinner(it) }
        .filter { it == 1 }
        .count()
        .let { println(it) }
}

fun determineWinner(line: String): Int {
    val cards = line.split(" ")
    val cards1 = cards.subList(0, 5).toTypedArray()
    val cards2 = cards.subList(5, 10).toTypedArray()

    val hand1 = determineHand(cards1)
    val hand2 = determineHand(cards2)

    return if (hand1 > hand2) 1 else 2
}

// we map each card to a hex value
// we just check card value, not suite
// ace of hearts is same as ace of spades, both are valued at 0xE
fun value(card: String): Int {
    return when (card[0]) {
        'A' -> 0xE
        'K' -> 0xD
        'Q' -> 0xC
        'J' -> 0xB
        'T' -> 0xA
        else -> Character.getNumericValue(card[0])
    }
}

// we convert each hand to a value
// the winning category is counted in millions, x is the individual value of the hand
// straight flush is   10_000_000 + x
// four of a kind is    9_000_000 + x
// full house is        8_000_000 + x
// flush is             7_000_000 + x
// straight is          6_000_000 + x
// three of a kind is   5_000_000 + x
// two pair is          4_000_000 + x
// one pair is          3_000_000 + x
// high card is                     x
private fun determineHand(cards: Array<String>): Int {
    orderCards(cards)
    val values = values(cards)
    val hexValue = hexValue(values)
    val isFlush = isFlush(cards)
    val highCardStraight = isStraight(values)
    val isStraight = highCardStraight > 0
    val freq = frequencies(values)

    if (isFlush && isStraight) {
        System.err.println(cards.contentToString() + " --> Straight flush, value " + highCardStraight)
        return 10_000_000 + highCardStraight
    }
    if (isFourOfAKind(freq)) {
        System.err.println(cards.contentToString() + " --> 4 of a kind, value " + Integer.toHexString(hexValue))
        return 9_000_000 + hexValue
    }
    val valueFullHouse = isFullHouse(freq)
    if (valueFullHouse > 0) {
        System.err.println(cards.contentToString() + " --> full house, value " + valueFullHouse)
        return 8_000_000 + valueFullHouse
    }
    if (isFlush) {
        System.err.println(cards.contentToString() + " --> flush")
        return 7_000_000
    }
    if (isStraight) {
        System.err.println(cards.contentToString() + " --> straight, high " + highCardStraight)
        return 6_000_000 + highCardStraight
    }
    val value3OfAKind = isThreeOfAKind(freq)
    if (value3OfAKind > 0) {
        System.err.println(cards.contentToString() + " --> 3 of a kind, value " + value3OfAKind)
        return 5_000_000 + value3OfAKind
    }

    val numberOfPairs = getNumberOfPairs(freq)
    if (numberOfPairs == 2) {
        return calcValueOfTwoPair(freq, cards, hexValue)
    } else if (numberOfPairs == 1) {
        return calcValueOfOnePair(freq, cards, hexValue)
    }
    System.err.println(cards.contentToString() + " --> high card, value " + Integer.toHexString(hexValue))
    return hexValue
}

fun calcValueOfTwoPair(freq: Map<Int, Int>, cards: Array<String>, hexValue: Int): Int {
    val valueOfPairs = TreeSet<Int>()
    var valueSingleCard = 0
    for ((key, value) in freq) {
        if (value == 1) {
            valueSingleCard = key
        } else if (value == 2) {
            valueOfPairs.add(key)
        }
    }
    var value = 4_000_000 + valueSingleCard
    var count = 1
    for (v in valueOfPairs) {
        value += (v * 16.0.pow(count++.toDouble())).toInt()
    }
    System.err.println(cards.contentToString() + " --> 2 pair, value " + Integer.toHexString(hexValue))
    return value
}

fun calcValueOfOnePair(freq: Map<Int, Int>, cards: Array<String>, hexValue: Int): Int {
    var valuePair = 0
    val valueUnmatchedCards = TreeSet<Int>()
    for ((key, value) in freq) {
        if (value == 2) {
            valuePair = key
        } else if (value == 1) {
            valueUnmatchedCards.add(key)
        }
    }
    var value = 3_000_000 + 16 * 16 * 16 * valuePair
    var count = 0
    for (v in valueUnmatchedCards) {
        value += (v * 16.0.pow(count++.toDouble())).toInt()
    }
    System.err.println(cards.contentToString() + " --> 1 pair, value " + Integer.toHexString(hexValue))
    return value
}

// do all cards have the same suit?
// e.g.
// [JD, TC, 9C, 7C, 6D] -> false
// [JD, TD, 9D, 7D, 6D] -> true
private fun isFlush(cards: Array<String>): Boolean {
    return cards.map { s -> s[1] }.distinct().count() == 1
}

// order cards descending by their value
private fun orderCards(cards: Array<String>) {
    Arrays.sort(cards) { card1: String, card2: String -> -(value(card1) - value(card2)) }
}

// map cards to IntArray based on their value
private fun values(cards: Array<String>): IntArray {
    return cards.map { value(it) }.toIntArray()
}

fun hexValue(values: IntArray): Int {
    var hexValue = 0
    for (value in values) {
        hexValue *= 16
        hexValue += value
    }
    return hexValue
}

private fun isStraight(values: IntArray): Int {
    System.err.println(values.contentToString())

    // check for ace low straight
    if (values.contentEquals(intArrayOf(value("A_"), 5, 4, 3, 2))) {
        return 5
    }

    val highCard = values[0]
    var lastValue = 0
    for (value in values) {
        if (lastValue > 0 && value != lastValue - 1) return 0
        lastValue = value
    }
    return highCard
}

// do we have any card 4 times?
private fun isFourOfAKind(freq: Map<Int, Int>): Boolean {
    return freq.any { entry -> entry.value == 4 }
}

// check if the map contains an entry with value 3, return its key if found, else 0
private fun isThreeOfAKind(freq: Map<Int, Int>): Int {
    val threeOfAkind = freq.filter { entry -> entry.value == 3 }.keys
    return if (threeOfAkind.size == 1) threeOfAkind.first() else 0
}

// how many pairs of cards do we have?
private fun getNumberOfPairs(freq: Map<Int, Int>): Int {
    return freq.count { entry -> entry.value == 2 }
}

private fun isFullHouse(freq: Map<Int, Int>): Int {
    var value = 0
    var has3OfAKind = false
    var hasPair = false
    for ((key, value1) in freq) {
        if (value1 == 3) {
            has3OfAKind = true
            value = key
        } else if (value1 == 2) hasPair = true
    }
    return if (hasPair && has3OfAKind) value else 0
}

// get frequencies of values
// e.g. [13, 8, 8, 7, 5] -> {13=1, 8=2, 7=1. 5=1}  (will be a pair)
private fun frequencies(values: IntArray): Map<Int, Int> {
    return values.toList().groupingBy { it }.eachCount()
}