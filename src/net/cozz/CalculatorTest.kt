package net.cozz

import org.junit.Assert
import org.junit.Test

class CalculatorTest {

    private val calculator = Calculator()

    @Test
    fun testWhenGivenDelimiterInInputShouldReturnTotal() {
        // input://;\n12;13;10\n1;2;3 -. 41
        Assert.assertEquals(41, calculator.add("//;\n12;13;10\n1;2;3"))
    }

    @Test
    fun testWhenGivenCommaAndNewlineDelimitersShouldReturnTotal() {
        Assert.assertEquals(41, calculator.add("12,13,10\n1,2,3"))
    }

    @Test
    fun testWhenGivenThreeCommaDelimitedNumbersShouldReturnTotal() {
        Assert.assertEquals(31, calculator.add("12,13,3,3"))
    }

    @Test
    fun testWhenGivenTwoCommaDelimitedNumbersShouldReturnTotal() {
        Assert.assertEquals(25, calculator.add("12,13"))
    }

    @Test
    fun testWhenGivenSingleNumberShouldReturnNumber() {
        Assert.assertEquals(123, calculator.add("123"))
    }

    @Test
    fun testWhenGivenEmptyStringShouldReturnZero() {
        Assert.assertEquals(0, calculator.add(""))
    }

    @Test
    fun testShouldExerciseTestRunner() {
        Assert.assertEquals(0, 0)
    }

}