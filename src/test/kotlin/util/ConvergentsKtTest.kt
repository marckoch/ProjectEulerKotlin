package util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import util.convergents.getConvergenceNumberOfE
import util.convergents.getConvergenceNumberOfSqrt2
import util.convergents.getConvergentBI
import util.fraction.Fraction
import java.math.BigInteger

class ConvergentsKtTest {
    private val testDataForConvergentsOfE = listOf(
        1 to intFractionOf(2,1),
        2 to intFractionOf(3,1),
        3 to intFractionOf(8,3),
        4 to intFractionOf(11,4),
        5 to intFractionOf(19, 7),
        6 to intFractionOf(87, 32),
        7 to intFractionOf(106, 39),
        8 to intFractionOf(193, 71),
        9 to intFractionOf(1264, 465),
        10 to intFractionOf(1457, 536)
    )

    @TestFactory
    fun testConvergentsOfE() = testDataForConvergentsOfE
        .map { (input, expected) ->
            DynamicTest.dynamicTest("getConvergentBI of `e` for $input is $expected") {
                val actual = getConvergentBI(input, 2, ::getConvergenceNumberOfE)
                assertThat(actual).isEqualTo(expected)
            }
        }

    private val testDataForConvergentsOfSqrt2 = listOf(
        1 to intFractionOf(1,1),
        2 to intFractionOf(3,2),
        3 to intFractionOf(7,5),
        4 to intFractionOf(17,12),
        5 to intFractionOf(41, 29),
        6 to intFractionOf(99, 70),
        7 to intFractionOf(239, 169),
        8 to intFractionOf(577, 408),
        9 to intFractionOf(1393, 985),
        10 to intFractionOf(3363, 2378)
    )

    @TestFactory
    fun testConvergentsOfSqrt2() = testDataForConvergentsOfSqrt2
        .map { (input, expected) ->
            DynamicTest.dynamicTest("getConvergentBI of `sqrt(2)` for $input is $expected") {
                val actual = getConvergentBI(input,1, ::getConvergenceNumberOfSqrt2)
                assertThat(actual).isEqualTo(expected)
            }
        }

    private fun intFractionOf(top: Int, bottom: Int): Fraction<BigInteger> {
        return Fraction(BigInteger.valueOf(top.toLong()), BigInteger.valueOf(bottom.toLong()))
    }
}