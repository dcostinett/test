package net.cozz

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

}
