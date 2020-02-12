package net.cozz;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import static net.cozz.Main.longestPalSubstr;
import static net.cozz.Main.longestPalindromeSubst;
import static net.cozz.Main.longestPalindromeSubstr;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class MainTest {
    private static final Logger LOGGER = Logger.getLogger(MainTest.class.getSimpleName());

    Main main;

    @Before
    public void before() {
        main = new Main();
    }


    @org.junit.Test
    public void testRemoveDups() throws Exception {
        String uniqueChars = main.removeDups("aaabcdeffgh");
        assertEquals("abcdefgh", uniqueChars);
    }


    @Test
    public void testRemoveDups1() throws Exception {
        String result = main.removeDups("aaabcdeffgh".toCharArray());
        assertEquals("abcdefgh", result);

    }


    @org.junit.Test
    public void testTraverseTreeInOrder() throws Exception {
        Main.Node node = new Main.Node(5);
        node.left = new Main.Node(3);
        node.left.left = new Main.Node(1);
        node.right = new Main.Node(7);

        main.traverseTreeInOrder(node);
    }


    @Test
    public void testBreadthFirstTraversal() throws Exception {
        Main.Node node = new Main.Node(5);
        node.left = new Main.Node(3);
        node.left.left = new Main.Node(1);
        node.right = new Main.Node(7);

        main.breadthFirstTraversal(node);
    }


    @Test
    public void testFibonacci() throws Exception {
        int result = main.fibonacci(3);
        assertEquals(1, result);

        result = main.fibonacci(4);
        assertEquals(2, result);

        result = main.fibonacci(5);
        assertEquals(3, result);

        result = main.fibonacci(6);
        assertEquals(5, result);
    }


    @Test
    public void testFibonacciIteratively() throws Exception {
        int result = main.fibonacciIteratively(3);
        assertEquals(1, result);

        result = main.fibonacciIteratively(4);
        assertEquals(2, result);

        result = main.fibonacciIteratively(5);
        assertEquals(3, result);

        result = main.fibonacciIteratively(6);
        assertEquals(5, result);
    }


    @Test
    public void testCountSetBits() throws Exception {
        int result = main.countSetBits(2);
        assertEquals(1, result);

        result = main.countSetBits(0);
        assertEquals(0, result);

        result = main.countSetBits(1);
        assertEquals(1, result);

        result = main.countSetBits(3);
        assertEquals(2, result);

        result = main.countSetBits(-1);
        assertEquals(1, result);
    }


    @Test
    public void testRevArr() throws Exception {
        char[] chars = new char[] {'a','b','c','d','e','f'};
        String expected = new StringBuffer(new String(chars)).reverse().toString();
        main.revArr(chars);
        String result = new String(chars);
        assertEquals(expected, result);

        chars = new char[] {'a','b','c','d','e','f', 'g'};
        expected = new StringBuffer(new String(chars)).reverse().toString();
        main.revArr(chars);
        result = new String(chars);
        assertEquals(expected, result);
    }


    @Test
    public void testPermute() throws Exception {
        main.permute("ABC");
        main.permute("ABCD");
    }


    @Test
    public void testIsOneBitSet() throws Exception {
        assertTrue(main.isPowerOfTwo(1));
        assertTrue(main.isPowerOfTwo(0));
        assertFalse(main.isPowerOfTwo(3));
        assertTrue(main.isPowerOfTwo(2));
        assertTrue(main.isPowerOfTwo(4));
        assertTrue(main.isPowerOfTwo(8));
        assertFalse(main.isPowerOfTwo(10));
    }


    @Test
    public void testGetAcuteAngle() throws Exception {
        int result = main.getAcuteAngle(3, 0);
        assertEquals(90, result);

        for (int i = 1; i <= 12; i++) {
            for (int j = 0; j <= 59; j++) {
                int angle = main.getAcuteAngle(i, j);
                assertEquals(expected(i,j), angle);
            }
        }
    }


    private int expected(int h, int m) {
        double hAngle = 0.5D * (h * 60 + m);
        double mAngle = 6 * m;
        double angle = Math.abs(hAngle - mAngle);
        angle = Math.min(angle, 360 - angle);
        return (int) angle;
    }

    @Test
    public void testTransform() {
        String testStr1 = "this is a test to check what {b} gets {/b} replaced";
        String transformed = Main.transform(testStr1);
        assertEquals("this is a test to check what <b> gets </b> replaced",
        transformed);
        LOGGER.log(Level.INFO, transformed);
    }

    @Test
    public void testBreakIntoSpaces() throws Exception {
        final String source = "peanutbutter";

        assertEquals("peanut butter", Main.breakIntoSpaces(source));
    }

    @Test
    public void testIsAnagramBySorting() throws Exception {
        String s1 = "dog";
        String s2 = "god";

        assertTrue(Main.isAnagramBySorting(s1, s2));
        assertTrue(s1.equals("dog"));
    }

    @Test
    public void testIsAnagramBySorting2() throws Exception {
        String s1 = "dog";
        String s2 = "not";

        assertFalse(Main.isAnagramBySorting(s1, s2));
    }

    @Test
    public void testIsAnagramBySorting3() throws Exception {
        String s1 = "dog";
        String s2 = "gdd";

        assertFalse(Main.isAnagramBySorting(s1, s2));
    }

    @Test
    public void testIsAnagram() throws Exception {
        String s1 = "dog";
        String s2 = "god";

        assertTrue(Main.isAnagram(s1, s2));
    }

    @Test
    public void testIsAnagram2() throws Exception {
        String s1 = "dog";
        String s2 = "not";

        assertFalse(Main.isAnagram(s1, s2));
    }

    @Test
    public void testIsAnagram3() throws Exception {
        String s1 = "dog";
        String s2 = "gdd";

        assertFalse(Main.isAnagram(s1, s2));
    }

    @Test
    public void testIsAnagram4() throws Exception {
        String s1 = "dog";
        String s2 = "godd";

        assertFalse(Main.isAnagram(s1, s2));
    }

    @Test
    public void testIsAnagram5() throws Exception {
        String s1 = "gooddog";
        String s2 = "doggood";

        assertTrue(Main.isAnagram(s1, s2));
    }

    @Test
    public void isRotationTest1() throws Exception {
        String s1 = "waterbottle";
        String s2 = "bottlewater";

        assertTrue(Main.isRotation(s1, s2));
    }

    @Test
    public void isRotationTest2() throws Exception {
        String s1 = "waterbottle";
        String s2 = "ottlewaterb";

        assertTrue(Main.isRotation(s1, s2));
    }

    @Test
    public void isRotationTest3() throws Exception {
        String s1 = "waterbottl";
        String s2 = "bottlewate";

        assertFalse(Main.isRotation(s1, s2));
    }

    @Test
    public void testNthToLastRecursive() throws Exception {
        Main.Node head = new Main.Node(1);
        head.next = new Main.Node(2);
        head.next.next = new Main.Node(3);
        head.next.next.next = new Main.Node(4);

        assertTrue(Main.nthToLastRecursive(head, 2).equals(new Main.Node(2)));
    }

    @Test
    public void testLongestPalindrome() throws Exception {
        String str = "forgeeksskeegfor";
        System.out.println("Length is: " +
                longestPalSubstr(str));
    }

    @Test
    public void testLongestPalindromeSubstr() throws Exception {
        String string = "forgeeksskeegfor";
        System.out.println(longestPalindromeSubstr(string));
    }

    @Test
    public void testLongestPalSubst() throws Exception {
        String string = "racecar";
        System.out.println(longestPalindromeSubst(string));
        System.out.println(longestPalindromeSubst("forgeeksskeegfor"));
        System.out.println(longestPalindromeSubst("amanaplanpanama"));
    }

    @Test
    public void testChains() {
        Map<String, String> chains = new HashMap<>();
        Main.parseMap("4-2;BEGIN-3;3-4;2-END", chains);

        assertEquals(4, chains.size());

        String end = Main.followChains("BEGIN", chains);

        assertEquals("END", end);
    }

    @Test
    public void testChains2() {
        Map<String, String> chains = new HashMap<>();
        Main.parseMap("4-2;BEGIN-3;3-4;2-3", chains);

        assertEquals(4, chains.size());

        String end = Main.followChains("BEGIN", chains);

        assertThat("bad", not(end));
    }

    @Test
    public void reverseString() throws Exception {
        Main main = new Main();
        String reversed = main.reverseString("this is a test");

        assertEquals("tset a si siht", reversed);
    }

    @Test
    public void reverseString1() throws Exception {
        Main main = new Main();
        String reversed = main.reverseString("this is a test ");

        assertEquals(" tset a si siht", reversed);
    }

    @org.junit.Test
    public void testReverseWords2() throws Exception {
        Main main = new Main();
        String reversed = main.reverseWords2("this is a test");

        assertEquals("test a is this", reversed);

        reversed = main.reverseWords2("this is a test ");
        assertEquals(" test a is this", reversed);

        reversed = main.reverseWords2(" this is a test");
        assertEquals("test a is this ", reversed);

        reversed = main.reverseWords2(" this is a test ");
        assertEquals(" test a is this ", reversed);
    }

    @Test
    public void removeDupsOrderedTest01() {
        Main main = new Main();

        LOGGER.info(main.removeDupsOrdered("tthis"));
        LOGGER.info(main.removeDupsOrdered("tt"));
        LOGGER.info(main.removeDupsOrdered("ttttthis"));
        LOGGER.info(main.removeDupsOrdered("this"));
        LOGGER.info(main.removeDupsOrdered("tthistt"));
        LOGGER.info(main.removeDupsOrdered("tthiistt"));
    }

    @Test
    public void removeDupsOrderedTest02() {
        Main main = new Main();

        LOGGER.info(main.removeDups("tthiss".toCharArray()));
        LOGGER.info(main.removeDups("tt".toCharArray()));
        LOGGER.info(main.removeDups("ttttthis".toCharArray()));
        LOGGER.info(main.removeDups("ttttthiiis".toCharArray()));
        LOGGER.info(main.removeDups("ttttthisa test".toCharArray()));
    }

    @Test
    public void testgetKthMostFrequent() {
        Main main = new Main();
        int[] input = {1,2,3,4,4,2,4,5,5,2,2,2};
        int result = Main.getKthMostFrequent(input, 0);
        LOGGER.info("" + result);

        result = Main.getKthMostFrequent(input, 1);
        LOGGER.info("" + result);

        result = Main.getKthMostFrequent(input, 2);
        LOGGER.info("" + result);

        result = Main.getKthMostFrequent(input, 3);
        LOGGER.info("" + result);

        result = Main.getKthMostFrequent(input, 4);
        LOGGER.info("" + result);
    }

    @Test
    public void testMinStepsToOneTabulation() {
        Main main = new Main();

        long startTime = System.currentTimeMillis();
        LOGGER.info("" + main.minStepsToOneTabulation(11));
        LOGGER.info("" + main.minStepsToOneTabulation(300));
        LOGGER.info("" + main.minStepsToOneTabulation(5));
        LOGGER.info("" + main.minStepsToOneTabulation(3000));
        LOGGER.info("" + main.minStepsToOneTabulation(12));
        LOGGER.info("" + main.minStepsToOneTabulation(100000));
        LOGGER.info("Process took: " + (System.currentTimeMillis() - startTime));
    }

    @Test
    public void testMinStepsToOneRecursive() {
        Main main = new Main();

        long startTime = System.currentTimeMillis();
        LOGGER.info("" + main.minStepsToOneRecursive(11));
        LOGGER.info("" + main.minStepsToOneRecursive(300));
        LOGGER.info("" + main.minStepsToOneRecursive(5));
        LOGGER.info("" + main.minStepsToOneRecursive(3000));
        LOGGER.info("" + main.minStepsToOneRecursive(12));
        LOGGER.info("" + main.minStepsToOneRecursive(1000));
        LOGGER.info("Process took: " + (System.currentTimeMillis() - startTime));
    }

    @Test
    public void testLongestCommonSubsequence() {

        Main main = new Main();

        String str1 = "aabb";
        String str2 = "bbaa";

        assertThat(main.longestCommonSubsequenceRecursive(str1, str2), is(2));
    }

    @Test
    public void testLcsTab() {
        Main main = new Main();

        String str1 = "aabb";
        String str2 = "bbaa";

        assertThat(main.longestCommonSubDynamic(str1,  str2), is(2));
    }

    @Test
    public void testLcsTab2() {
        Main main = new Main();

        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";

        assertThat(main.longestCommonSubDynamic(s1,  s2), is(4));
    }

    @Test
    public void testConvertNtoM() {
        Main main = new Main();

        int n = 4;
        int m = 6;

        assertThat(main.convertNtoM(n, m), is(2));
    }

    @Test
    public void testConvertNtoM2() {
        Main main = new Main();

        int n = 10;
        int m = 1;

        assertThat(main.convertNtoM(n, m), is(9));
    }

    @Test
    public void testMaximumSumToLeaf() {
        Main main = new Main();
        int n = 14;

        // adjacency list
        @SuppressWarnings("unchecked")
        Vector<Integer>[] v = new Vector[n + 1];

        for (int i = 0; i < v.length; i++)
            v[i] = new Vector<>();

        // create undirected edges
        // initialize the tree given in the diagram
        v[1].add(2); v[2].add(1);
        v[1].add(3); v[3].add(1);
        v[1].add(4); v[4].add(1);
        v[2].add(5); v[5].add(2);
        v[2].add(6); v[6].add(2);
        v[3].add(7); v[7].add(3);
        v[4].add(8); v[8].add(4);
        v[4].add(9); v[9].add(4);
        v[4].add(10); v[10].add(4);
        v[5].add(11); v[11].add(5);
        v[5].add(12); v[12].add(5);
        v[7].add(13); v[13].add(7);
        v[7].add(14); v[14].add(7);

        // values of node 1, 2, 3....14
        int a[] = { 3, 2, 1, 10, 1, 3, 9,
                1, 5, 3, 4, 5, 9, 8 };

        // function call
        System.out.println(main.maximumSumToLeaf(a, v));
        assertThat(main.maximumSumToLeaf(a, v), is(22));
    }

    @Test
    public void testMaximumSumToLeaf2() {
        Main main = new Main();
        int n = 14;

        // adjacency list
        @SuppressWarnings("unchecked")
        Vector<Integer>[] v = new Vector[n + 1];

        for (int i = 0; i < v.length; i++)
            v[i] = new Vector<>();

        // create undirected edges
        // initialize the tree given in the diagram
        v[1].add(2); v[2].add(1);
        v[1].add(3); v[3].add(1);
        v[1].add(4); v[4].add(1);
        v[2].add(5); v[5].add(2);
        v[2].add(6); v[6].add(2);
        v[3].add(7); v[7].add(3);
        v[4].add(8); v[8].add(4);
        v[4].add(9); v[9].add(4);
        v[4].add(10); v[10].add(4);
        v[5].add(11); v[11].add(5);
        v[5].add(12); v[12].add(5);
        v[7].add(13); v[13].add(7);
        v[7].add(14); v[14].add(7);

        // values of node 1, 2, 3....14
        int a[] = { 3, 2, 1, 10, 1, 3, 9,
                1, 5, 3, 4, 5, 9, 11 };

        // function call
        System.out.println(main.maximumSumToLeaf(a, v));
        assertThat(main.maximumSumToLeaf(a, v), is(24));
    }

    @Test
    public void testReverseWords3() throws Exception {
        Main main = new Main();
        String reversed = main.reverseWords3("this is a test");

        assertEquals("test a is this", reversed);

        reversed = main.reverseWords3("this is a test ");
        assertEquals("test a is this", reversed);

        reversed = main.reverseWords3(" this is a test");
        assertEquals("test a is this", reversed);

        reversed = main.reverseWords3(" this is a test ");
        assertEquals("test a is this", reversed);
    }

    @Test
    public void permuteTest() throws Exception {
        Main main = new Main();

        main.Permute("ABCD");
    }

    @Test
    public void testCountPairs() {
        Main main = new Main();

        int[] a = {1, 2, 3};
        int[] b = {4, 5, 6};

        assertThat(main.countPairsSummingToN(a, b, 7), is(3) );
    }

    @Test
    public void testCountPairs2() {
        Main main = new Main();

        int[] a = {1, 2, 3, 3};
        int[] b = {4, 5, 6, 6};

        assertThat(main.countPairsSummingToN(a, b, 7), is(5) );
    }

    @Test
    public void testCountPairs3() {
        Main main = new Main();

        int[] a = {1, 2, 3, 3};
        int[] b = {4, 5, 6, 6};

        assertThat(main.countPairsSummingToN(a, b, 9), is(4) );
    }

    @Test
    public void removeDups() throws Exception {
        Main main = new Main();
        int size = main.deleteDups("AABC".toCharArray());

        assertEquals(3, size);

        size = main.deleteDups("ABC".toCharArray());
        assertEquals(3, size);
    }


    @Test
    public void findFactTest() throws Exception {
        Main main = new Main();

        long result = main.findFactorialRecursive(4);
        assertEquals(24, result);
    }


    @Test
    public void countSetBits() throws Exception {
        Main main = new Main();

        int result = main.countSetBits(2);

        assertEquals(1, result);

        result = main.countSetBits(3);
        assertEquals(2, result);

        result = main.countSetBits(-1);
        assertEquals(1, result);
    }


    @Test
    public void reverseWordsTest() throws Exception {
        Main main = new Main();

        String test = "this is a test";

        String reversed = main.reverseWords(test);

        assertEquals("test a is this", reversed);
    }


    @Test
    public void removeDupsTest() throws Exception {
        Main main = new Main();

        String narnia = new String("Narnia");

        assertEquals("Narni", main.removeDups(narnia));
    }

    @Test
    public void testReconstruction() {
        Tree tree = new Tree(null);
        char[] in = new char[]{ 'D', 'B', 'E', 'A', 'F', 'C' };
        char[] pre = new char[] { 'A', 'B', 'D', 'E', 'C', 'F' };

        Tree.CharNode root = tree.reconstruct(in, pre);

        tree.inOrder(root);
    }

    @Test
    public void testReconstruction2() {
        Tree tree = new Tree(null);
        char[] in = new char[]{ 'A', 'B', 'C', 'D' };
        char[] pre = new char[] { 'D', 'C', 'B', 'A' };

        Tree.CharNode root = tree.reconstruct(in, pre);

        tree.inOrder(root);
    }
}