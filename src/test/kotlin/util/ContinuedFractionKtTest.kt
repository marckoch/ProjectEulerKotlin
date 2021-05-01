package util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import util.continueFraction.convertToContinuedFraction

class ContinuedFractionKtTest {

    private val testData = listOf(
        2 to Pair(1, listOf(2)),
        3 to Pair(1, listOf(1, 2)),
        5 to Pair(2, listOf(4)),
        6 to Pair(2, listOf(2, 4)),
        7 to Pair(2, listOf(1, 1, 1, 4)),
        8 to Pair(2, listOf(1, 4)),
        10 to Pair(3, listOf(6)),
        11 to Pair(3, listOf(3, 6)),
        12 to Pair(3, listOf(2, 6)),
        13 to Pair(3, listOf(1, 1, 1, 1, 6)),
    )

    @TestFactory
    fun testConvertToContinuedFraction() = testData
        .map { (input, expected) ->
            dynamicTest("convertToContinuedFraction for $input is $expected") {
                val actual = convertToContinuedFraction(input)
                assertThat(actual).isEqualTo(expected)
            }
        }
}