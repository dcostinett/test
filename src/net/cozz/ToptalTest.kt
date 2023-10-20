package net.cozz

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test

class ToptalTest {

    @Test
    fun `test case one`() {

        val expected = intArrayOf(1, 0, 0, 0, 0, 4)

        val actual = Toptal.getChange(5.0, 0.99)

        assertTrue(expected.contentEquals(actual))
    }

    @Test
    fun `test dp approach`() {
        val input = intArrayOf(1, 2, 3)

        assertThat(Toptal.getCountOfWaysToMakeChange(input, 4), CoreMatchers.equalTo(4))
    }

    @Test
    fun `test dp approach 2`() {
        val input = intArrayOf(2, 3, 5, 6)

        assertThat(Toptal.getCountOfWaysToMakeChange(input, 10), CoreMatchers.equalTo(5))
    }

    @Test
    fun `test dp approach2a`() {
        val input = intArrayOf(1, 2, 3)

        assertThat(Toptal.getCountOfWaysToMakeChange2(input, 4), CoreMatchers.equalTo(4))
    }

    @Test
    fun `test dp approach 2b`() {
        val input = intArrayOf(2, 3, 5, 6)

        assertThat(Toptal.getCountOfWaysToMakeChange2(input, 10), CoreMatchers.equalTo(5))
    }

    @Test
    fun `test dp approach 3`() {
        val input = intArrayOf(2, 3, 5, 6)

        val result = Toptal.printCoinCombos(input, 10)
        print(result)
    }
}
