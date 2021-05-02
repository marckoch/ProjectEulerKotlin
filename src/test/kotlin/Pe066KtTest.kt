package pe066

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class Pe066KtTest {
    private val testData_xOfSmallestSolution = listOf(
        1 to 0,
        2 to 3,
        3 to 2,
        4 to 0,
        5 to 9,
        6 to 5,
        7 to 8,
        8 to 3,
        9 to 0,
        10 to 19,
        13 to 649,
        17 to 33,
        22 to 197
    )

    @TestFactory
    fun testSmallestSolution() = testData_xOfSmallestSolution
        .map { (input, expected) ->
            DynamicTest.dynamicTest("getXofSmallestSolutionFor for $input is $expected") {
                val actual = getSmallestSolutionFor(input).first
                Assertions.assertThat(actual).isEqualTo(expected)
            }
        }
}