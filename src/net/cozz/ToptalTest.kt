package net.cozz

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import kotlin.test.assertTrue

class ToptalTest {

    @Test
    fun `test case one`() {

        val expected = intArrayOf(1, 0, 0, 0, 0, 4)

        val top = Toptal()
        val actual = top.getChange(5.0, 0.99)

        assertTrue { expected contentEquals actual }
    }

    @Test
    fun `test dp approach`() {
        val input = intArrayOf(1, 2, 3)

        assertThat(Toptal().getCountOfWaysToMakeChange(input, 4), CoreMatchers.equalTo(4))
    }

    @Test
    fun `test dp approach 2`() {
        val input = intArrayOf(2, 3, 5, 6)

        assertThat(Toptal().getCountOfWaysToMakeChange(input, 10), CoreMatchers.equalTo(5))
    }

    @Test
    fun `test dp approach 3`() {
        val input = intArrayOf(2, 3, 5, 6)

        Toptal().printCoinCombos(input, 10)
    }
}
