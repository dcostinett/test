package net.cozz.flexe

import net.cozz.flexe.FlexeTest.practice
import net.cozz.flexe.FlexeTest.solution
import org.junit.Assert
import org.junit.Test
import java.util.*

class FlexeTestTestKt {

    @Test
    fun testSimple() {
        val A = IntArray(1)
        A[0] = 2
        val ans = practice(A)
        Assert.assertTrue(ans == 1)
    }

    @Test
    fun testShuffle() {
        val A = IntArray(200)
        for (i in A.indices) {
            A[i] = i
        }
        for (i in 101 until A.size) {
            A[i] = i + 1
        }
        Collections.shuffle((A.toList()))
        val ans = practice(A)
        Assert.assertEquals(ans.toLong(), 101)
    }

    @Test
    fun testRealTest1() {
        val N = 2
        val test = "1A 2F 1C"
        val ans = solution(N, test)
        Assert.assertEquals(4, ans.toLong())
    }

    @Test
    fun testRealTest2() {
        val N = 4
        val test = "1A 2F 1C 4A 3D 4C 3C 2G 4K"
        val ans = solution(N, test)
        Assert.assertEquals(7, ans.toLong())
    }

    @Test
    fun testRealTest3() {
        val N = 5
        val test = "1A 2F 1C 4A 3D 4C 3C 2G 4K"
        val ans = solution(N, test)
        Assert.assertEquals(10, ans.toLong())
    }

    @Test
    fun testRealTest4() {
        val N = 40
        val test = "1A 3C 2B 40G 5A"
        val ans = solution(N, test)
        Assert.assertEquals(116, ans.toLong())
    }

    @Test
    fun testRealTest5() {
        val N = 5
        val test = ""
        val ans = solution(N, test)
        Assert.assertEquals(15, ans.toLong())
    }

    @Test
    fun testRealTest6() {
        val N = 2
        val test = "1A 2F 1C"
        val ans = solution(N, test)
        Assert.assertEquals(4, ans.toLong())
    }

}