package net.cozz.flexe;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FlexeTestTest {

    @Test
    public void testSimple() {
        int[] A = new int[1];
        A[0] = 2;
        int ans = FlexeTest.practice(A);

        assertTrue(ans == 1);
    }

    @Test
    public void testShuffle() {
        int[] A = new int[200];
        for (int i = 0; i < A.length; i++) {
            A[i] = i;
        }
        for (int i = 101; i < A.length; i++) {
            A[i] = i + 1;
        }
        Collections.shuffle(Arrays.asList(A));

        int ans = FlexeTest.practice(A);

        assertEquals(ans, 101);
    }

    @Test
    public void testRealTest1() {
        int N = 2;
        String test = "1A 2F 1C";

        int ans = FlexeTest.solution(N, test);

        assertEquals(4, ans);
    }

    @Test
    public void testRealTest2() {
        int N = 4;
        String test = "1A 2F 1C 4A 3D 4C 3C 2G 4K";

        int ans = FlexeTest.solution(N, test);

        assertEquals(7, ans);
    }

    @Test
    public void testRealTest3() {
        int N = 5;
        String test = "1A 2F 1C 4A 3D 4C 3C 2G 4K";

        int ans = FlexeTest.solution(N, test);

        assertEquals(10, ans);
    }

    @Test
    public void testRealTest4() {
        int N = 40;
        String test =  "1A 3C 2B 40G 5A";

        int ans = FlexeTest.solution(N, test);

        assertEquals(116, ans);
    }

    @Test
    public void testRealTest5() {
        int N = 5;
        String test = "";

        int ans = FlexeTest.solution(N, test);

        assertEquals(15, ans);
    }

    @Test
    public void testRealTest6() {
        int N = 2;
        String test =  "1A 2F 1C";

        int ans = FlexeTest.solution(N, test);

        assertEquals(4, ans);
    }
}