import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Pe054KtTest {

    private val valueTestData = listOf(
        "2_" to 2,
        "3_" to 3,
        "4_" to 4,
        "5_" to 5,
        "6_" to 6,
        "7_" to 7,
        "8_" to 8,
        "9_" to 9,
        "T_" to 10,
        "J_" to 11,
        "Q_" to 12,
        "K_" to 13,
        "A_" to 14
    )

    @TestFactory
    fun testValue() = valueTestData
        .map { (input, expected) ->
            dynamicTest("value of card $input is $expected") {
                assertThat(value(input)).isEqualTo(expected)
            }
        }

    @Test
    fun testHexValue() {
        assertThat(hexValue(intArrayOf(1,1,1,1,1))).isEqualTo(12)
    }
}
