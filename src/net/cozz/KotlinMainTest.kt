package net.cozz

import junit.framework.Assert.assertTrue
import net.cozz.hackerrank.ArrayDSChallenge
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Test


internal class KotlinMainTest {

    data class User(val name: String, val age: Int, val hobbies: List<String>)
    val user1 = User("John", 18, listOf("Hiking"))
    val user2 = User("Sara", 25, listOf("Chess"))
    val user3 = User("Dave", 34, listOf("Games"))
    val user4 = User("Dan", 34, listOf("Soccer"))

    @Test
    @Throws(Exception::class)
    fun testBreadthFirstTraversal() {
        val node = KotlinMain.Node(5)
        node.left = KotlinMain.Node(3)
        node.left!!.left = KotlinMain.Node(1)
        node.right = KotlinMain.Node(7)
        val calculated = KotlinMain().breadthFirstTraversal(node)
        assertThat(calculated, equalTo("5371"))
    }

    @Test
    @Throws(Exception::class)
    fun testBreadthFirstTraversal2() {
        val node = KotlinMain.Node(5)
        node.left = KotlinMain.Node(3)
        node.left!!.left = KotlinMain.Node(1)
        node.right = KotlinMain.Node(7)
        val calculated = KotlinMain().breadthFirstTraversal2(node)
        assertThat(calculated, equalTo("5371"))
    }

    @Test
    fun givenList_whenConvertToMap_thenResult() {

        val myList = listOf(user1, user2, user3)
        val myMap = myList.map { it.name to it.age }.toMap()

        assertTrue(myMap.get("John") == 18)
    }

    @Test
    fun givenStringList_whenConvertToMap_thenResult() {
        val myList = listOf("a", "b", "c")
        val myMap = myList.map { it to it }.toMap()

        assertTrue(myMap.get("a") == "a")
    }

    @Test
    fun givenList_whenAssociatedBy_thenResult() {
        val myList = listOf(user1, user2, user3)
        val myMap = myList.associateBy({ it.name }, { it.hobbies })

        assertTrue(myMap.get("John")!!.contains("Hiking"))
    }

    @Test
    fun givenStringList_whenAssociate_thenResult() {
        val myList = listOf("a", "b", "c", "d")
        val myMap = myList.associate{ it to it }

        assertTrue(myMap.get("a") == "a")
    }

    @Test
    fun givenStringList_whenAssociateTo_thenResult() {
        val myList = listOf("a", "b", "c", "c", "b")
        val myMap = mutableMapOf<String, String>()

        myList.associateTo(myMap) {it to it}

        assertTrue(myMap.get("a") == "a")
    }

    @Test
    fun givenStringList_whenAssociateByToUser_thenResult() {
        val myList = listOf(user1, user2, user3, user4)
        val myMap = mutableMapOf<String, User>()

        myList.associateByTo(myMap) {it.name}

        assertTrue(myMap.get("Dave")!!.age == 34)
    }

    @Test
    //valueTransform function of associateByTo
    fun givenstringlistWhenassociatebytoThenresult() {
        val myList = listOf(user1, user2, user3, user4)
        val myMap = mutableMapOf<String, Int>()

        myList.associateByTo(myMap, {it.name}, {it.age})

        assertTrue(myMap.get("Dave") == 34)
    }

    @Test
    fun `list to map`() {
        val numbers = setOf(1, 2, 3)
        val pairs = numbers.map { it to it*it }
        pairs.forEach {
            println("${it.first} squared = ${it.second}")
        }

        val map = pairs.toMap()
        map.keys.forEach {
            println("$it : ${map[it]}")
        }
    }

    // Zipping extension function
    @Test
    fun `zipping examples`() {
        val colors = listOf("red", "brown", "grey")
        val animals = listOf("fox", "bear", "wolf")
        println(colors zip animals)

        val twoAnimals = listOf("fox", "bear")
        println(colors.zip(twoAnimals))

        println(colors.zip(animals) { color, animal -> "The ${animal.capitalize()} is $color"})

        val numberPairs = listOf("one" to 1, "two" to 2, "three" to 3, "four" to 4)
        println(numberPairs.unzip())
    }

    // Association
    @Test
    fun `association examples`() {
        val numbers = listOf("one", "two", "three", "four")
//        println(numbers.associateWith { it.length }) // according to https://kotlinlang.org/docs/reference/collection-transformations.html
//        this should be present in 1.3.61 but it doesn't appear to work

        println(numbers.associateBy { it.first().toUpperCase() })
        println(numbers.associateBy(keySelector = { it.first().toUpperCase() }, valueTransform = { it.length }))

        val names = listOf("Alice Adams", "Brian Brown", "Clara Campbell")
        println(names.associate { name -> parseFullName(name).let { it.lastName to it.firstName } })
    }

    data class FullName(val firstName: String, val lastName: String)

    private fun parseFullName(name: String) : FullName {
        val arr = name.split(" ")
        return FullName(arr.first(), arr.last())
    }

    //Flattening (for nested lists)
    @Test
    fun `flatten examples`() {
        val numberSets = listOf(setOf(1, 2, 3), setOf(4, 5, 6), setOf(1, 2))
        println(numberSets.flatten())

        val containers = listOf(
                StringContainer(listOf("one", "two", "three")),
                StringContainer(listOf("four", "five", "six")),
                StringContainer(listOf("seven", "eight"))
        )
        println(containers.flatMap { it.values })
    }

    data class StringContainer(val values: List<String>)

    // join to string
    /*
        joinToString() builds a single String from the collection elements based on the provided arguments.
        joinTo() does the same but appends the result to the given Appendable object.
     */
    @Test
    fun `join example1`() {
        val numbers = listOf("one", "two", "three", "four")

        println(numbers)
        println(numbers.joinToString())

        val listString = StringBuffer("The list of numbers: ")
        numbers.joinTo(listString)
        println(listString)

        println(numbers.joinToString(separator = " | ", prefix = "start: ", postfix = ": end"))

        println(numbers.joinToString { "Element: ${it.toUpperCase()}"})
    }

    @Test
    fun `join example2`() {
        val numbers = (1..100).toList()
        println(numbers.joinToString(limit = 10, truncated = "<...>"))

        val shortList = (0..9).toMutableList()
        println(shortList.joinToString(limit = 10, truncated = "..."))
        shortList.add(10)
        println(shortList.joinToString(limit = 10, truncated = "..."))
    }

    @Test
    fun `test max area`() {
        val arr = intArrayOf(1,8,6,2,5,4,8,3,7)

        val main = KotlinMain()

        val result = main.maximizeProduct(arr)

        Assert.assertThat(result, CoreMatchers.`is`(49))
    }

    @Test
    fun `test max area 2`() {
        val arr = intArrayOf(1,8,6,2,5,4,8,3,7,8)

        val main = KotlinMain()

        val result = main.maximizeProduct(arr)

        Assert.assertThat(result, CoreMatchers.`is`(64))
    }

    @Test
    fun `aToI test 1`() {
        val str = "123"

        val main = KotlinMain()

        assertThat(main.atoI(str), CoreMatchers.`is`(123))
    }

    @Test
    fun `aToI test 2`() {
        val str = "123"

        val main = KotlinMain()

        assertThat(main.atoI(str) + 10, CoreMatchers.`is`(133))
    }

    @Test
    fun `min coin count for change 1`() {
        val main = KotlinMain()

        assertThat(main.minimizeCoinCount(intArrayOf(1, 5, 10, 25), 11), CoreMatchers.`is`(2))
    }

    @Test
    fun `min coin count for change 2`() {
        val main = KotlinMain()

        assertThat(main.minimizeCoinCount(intArrayOf(1, 5, 6, 9), 11), CoreMatchers.`is`(2))
    }

    @Test
    fun `min coin count for change 3`() {
        val main = KotlinMain()

        assertThat(main.minimizeCoinCount(intArrayOf(1, 5, 10, 21, 25), 63), CoreMatchers.`is`(3))
    }

    @Test
    fun `min coin count for change 4`() {
        val main = KotlinMain()

        assertThat(main.minimizeCoinCount(intArrayOf(1, 5, 10, 21, 25, 50), 63), CoreMatchers.`is`(3))
    }

    @Test
    fun `min coin count for change 5`() {
        val main = KotlinMain()

        assertThat(main.minimizeCoinCount(intArrayOf(1, 5, 10, 25), 63), CoreMatchers.`is`(6))
    }

    @Test
    fun `test fibonacci tabulated`() {
        assertThat(KotlinMain().fiboTabulated(9), CoreMatchers.`is`(34))
    }

    @Test
    fun `test fibonacci tabulated 2`() {
        assertThat(KotlinMain().fiboTabulated(10), CoreMatchers.`is`(55))
    }

    @Test
    fun `test fibonacci tabulated 3`() {
        assertThat(KotlinMain().fiboTabulated(1), CoreMatchers.`is`(1))
    }

    @Test
    fun `test fibonacci tabulated 4`() {
        assertThat(KotlinMain().fiboTabulated(3), CoreMatchers.`is`(2))
    }

    @Test
    fun `test fibonacci tabulated 5`() {
        assertThat(KotlinMain().fiboTabulated(4), CoreMatchers.`is`(3))
    }

    @Test
    fun `test fibonacci tabulated 6`() {
        assertThat(KotlinMain().fiboTabulated(8), CoreMatchers.`is`(21))
    }

    @Test
    fun `test nested recursion`() {
        println(KotlinTree().nestedRecursionExample(95))
    }

    @Test
    fun testReconstruction() {
        val tree = KotlinTree()
        val `in` = charArrayOf('D', 'B', 'E', 'A', 'F', 'C')
        val pre = charArrayOf('A', 'B', 'D', 'E', 'C', 'F')
        val root = tree.reconstruct(`in`, pre)
        tree.inOrder(root)
    }

    @Test
    fun testReconstruction2() {
        val tree = KotlinTree()
        val `in` = charArrayOf('A', 'B', 'C', 'D')
        val pre = charArrayOf('D', 'C', 'B', 'A')
        val root = tree.reconstruct(`in`, pre)
        tree.inOrder(root)
    }

    @Test
    fun `stick cost test 1`() {
        val result = KotlinMain().minimizeStickCost(intArrayOf(2, 4, 3))
        assertThat(result, CoreMatchers.equalTo(14))
    }

    @Test
    fun `stick cost test 2`() {
        val result = KotlinMain().minimizeStickCost(intArrayOf(1,8,3,5))
        assertThat(result, CoreMatchers.equalTo(30))
    }

    @Test
    fun `countIslandsTest 1`() {
        val input = arrayOf(
                intArrayOf(1, 1, 0, 0, 0),
                intArrayOf(0, 1, 0, 0, 1),
                intArrayOf(1, 0, 0, 1, 1),
                intArrayOf(0, 0, 0, 0, 0),
                intArrayOf(1, 0, 1, 0, 1))

        val actual = ArrayDSChallenge().countIslands(input)
        assertThat("Number of islands: ", actual, CoreMatchers.equalTo(6))
    }

    @Test
    fun `countIslandsTest 2`() {
        val input = arrayOf(
                intArrayOf(1, 1, 0, 0, 0),
                intArrayOf(1, 1, 0, 0, 1),
                intArrayOf(0, 0, 0, 1, 1),
                intArrayOf(0, 0, 0, 0, 0),
                intArrayOf(1, 0, 0, 0, 1))

        assertThat("Number of islands: ", ArrayDSChallenge().countIslands(input), CoreMatchers.equalTo(4))
    }

    @Test
    fun `countIslandsTest 3`() {
        val input = arrayOf(
            intArrayOf(1, 1, 0, 0, 0),
            intArrayOf(1, 1, 0, 1, 1),
            intArrayOf(0, 0, 1, 0, 0),
            intArrayOf(0, 0, 0, 1, 1),
            intArrayOf(0, 0, 0, 1, 1))

        val calculated = ArrayDSChallenge().countIslands(input)
        assertThat("Number of islands: ", calculated, CoreMatchers.equalTo(4))
    }

    @Test
    fun `countIslandsTest 4`() {
        val input = arrayOf(
            intArrayOf(1, 1, 0, 0, 0),
            intArrayOf(1, 1, 0, 0, 0),
            intArrayOf(0, 0, 1, 0, 0),
            intArrayOf(0, 0, 0, 1, 0),
            intArrayOf(0, 0, 0, 1, 1))

        val calculated = ArrayDSChallenge().countIslands(input)
        assertThat("Number of islands: ", calculated, CoreMatchers.equalTo(3))
    }

    @Test
    fun `countIslandsTest BF`() {
        val input = arrayOf(
                intArrayOf(1, 1, 0, 0, 0),
                intArrayOf(1, 1, 0, 0, 1),
                intArrayOf(0, 0, 0, 1, 1),
                intArrayOf(0, 0, 0, 0, 0),
                intArrayOf(1, 0, 0, 0, 1))

        assertThat("Number of islands: ", ArrayDSChallenge().countIslandsBf(input), CoreMatchers.equalTo(4))
    }

    @Test
    fun `countIslandsTest BF2`() {
        val input = arrayOf(
                intArrayOf(1, 1, 0, 0, 0),
                intArrayOf(1, 1, 0, 0, 1),
                intArrayOf(0, 0, 1, 1, 1),
                intArrayOf(0, 0, 0, 0, 0),
                intArrayOf(1, 0, 0, 0, 1))

        assertThat("Number of islands: ", ArrayDSChallenge().countIslandsBf(input), CoreMatchers.equalTo(4))
    }

    @Test
    fun `countIslandsTest BF3`() {
        val input = arrayOf(
            intArrayOf(1, 1, 0, 0, 0),
            intArrayOf(1, 1, 0, 0, 1),
            intArrayOf(0, 0, 1, 0, 1),
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(1, 0, 0, 0, 1))

        assertThat("Number of islands: ", ArrayDSChallenge().countIslandsBf(input), CoreMatchers.equalTo(5))
    }

    @Test
    fun `findLargestArea dfs`() {
        val input = arrayOf(
            arrayOf(1, 1, 0, 0, 0),
            arrayOf(1, 1, 0, 0, 1),
            arrayOf(0, 0, 1, 0, 1),
            arrayOf(0, 0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 1))

        val calculated = ArrayDSChallenge().largestIsland(input)
        assertThat("Largest island size: ", calculated, CoreMatchers.equalTo(4))
    }

    @Test
    fun `findLargestArea dfs_2`() {
        val input = arrayOf(
            arrayOf(1, 1, 0, 0, 1),
            arrayOf(1, 1, 0, 0, 1),
            arrayOf(0, 0, 1, 0, 1),
            arrayOf(0, 0, 0, 0, 1),
            arrayOf(1, 0, 0, 0, 1))

        val calculated = ArrayDSChallenge().largestIsland(input)
        assertThat("Largest island size: ", calculated, CoreMatchers.equalTo(5))
    }

    @Test
    fun `max area of island`() {
        val grid = arrayOf(
            intArrayOf(1,1,1,0,0,0,0,1,0,0,0,0,0),
            intArrayOf(0,0,0,0,0,0,0,1,1,1,0,0,0),
            intArrayOf(0,1,1,0,1,0,0,0,0,0,0,0,0),
            intArrayOf(0,1,0,0,1,1,0,0,1,0,1,0,0),
            intArrayOf(0,1,0,0,1,1,0,0,1,1,1,0,0),
            intArrayOf(0,0,0,0,0,0,0,0,0,0,1,0,0),
            intArrayOf(1,1,1,1,1,0,0,1,1,1,0,0,0),
            intArrayOf(1,1,1,1,1,0,0,1,1,0,0,0,0))

        val calculated = ArrayDSChallenge().largestIsland(grid)
        assertThat("Largest island size: ", calculated, CoreMatchers.equalTo(10))
    }

    @Test
    fun `max area of island 2`() {
        val grid = arrayOf(
            intArrayOf(1,1,1,0,0,0,0,1,0,0,0,0,1),
            intArrayOf(0,0,0,0,0,0,0,1,1,1,0,0,1),
            intArrayOf(0,1,1,0,1,0,0,0,0,0,0,0,1),
            intArrayOf(0,1,0,0,1,1,0,0,1,0,1,0,1),
            intArrayOf(0,1,0,0,1,1,0,0,1,1,1,0,1),
            intArrayOf(0,0,0,0,0,0,0,0,0,0,1,0,1),
            intArrayOf(0,0,0,0,0,0,0,0,0,0,1,0,1),
            intArrayOf(0,0,0,0,0,0,0,0,0,0,1,0,1),
            intArrayOf(0,0,0,0,0,0,0,0,0,0,1,0,1),
            intArrayOf(1,1,1,1,1,0,0,1,1,1,0,0,1),
            intArrayOf(1,1,1,1,1,0,0,1,1,0,0,0,1))

        val calculated = ArrayDSChallenge().largestIsland(grid)
        assertThat("Largest island size: ", calculated, CoreMatchers.equalTo(11))
    }

    @Test
    fun `max area of island 3`() {
        val grid = arrayOf(
            intArrayOf(0,0,1,0,0,0,0,1,0,0,0,0,0),
            intArrayOf(0,0,0,0,0,0,0,1,1,1,0,0,0),
            intArrayOf(0,1,1,0,1,0,0,0,0,0,0,0,0),
            intArrayOf(0,1,0,0,1,1,0,0,1,0,1,0,0),
            intArrayOf(0,1,0,0,1,1,0,0,1,1,1,0,0),
            intArrayOf(0,0,0,0,0,0,0,0,0,0,1,0,0),
            intArrayOf(0,0,0,0,0,0,0,1,1,1,1,0,0),
            intArrayOf(0,0,0,0,0,0,0,1,1,0,0,0,0))


        val calculated = ArrayDSChallenge().largestIsland(grid)
        assertThat("Largest island size: ", calculated, CoreMatchers.equalTo(12))
    }

    @Test
    fun `make largest island`() {
        val grid = arrayOf(
            intArrayOf(0,1,0,1,0),
            intArrayOf(1,1,0,0,1),
            intArrayOf(0,0,1,1,0)
            )


        val calculated = ArrayDSChallenge().makeLargestIsland(grid)
        assertThat("Largest possible island size: ", calculated, CoreMatchers.equalTo(6))
    }

    @Test
    fun `letterCombinationsTest 1`() {
        assertThat(DigitMappings().digitMapping("23"), CoreMatchers.equalTo(
                listOf("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf")))
    }

    @Test
    fun `longestSubstring test 1`() {
        assertThat(KotlinMain().longestSubstring(intArrayOf(1, 2, 1)), CoreMatchers.equalTo(3))
    }

    @Test
    fun `longestSubstring test 2`() {
        assertThat(KotlinMain().longestSubstring(intArrayOf(0, 1, 2, 2)), CoreMatchers.equalTo(3))
    }

    @Test
    fun `longestSubstring test 3`() {
        assertThat(KotlinMain().longestSubstring(intArrayOf(1, 2, 3, 2, 2)), CoreMatchers.equalTo(4))
    }

    @Test
    fun `calculate rain water test 1`() {
        val arr = intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1)
        assertThat(KotlinMain().rainWaterTrappingDynamic(arr), CoreMatchers.equalTo(6))
    }

    @Test
    fun `calculate rain water test 2`() {
        val arr = intArrayOf(0,1,0,2,1,0,1,4,3,1,3,1)
        assertThat(KotlinMain().rainWaterTrappingDynamic(arr), CoreMatchers.equalTo(7))
    }

    @Test
    fun `calculate rain water test 3`() {
        val arr = intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1,3)
        assertThat(KotlinMain().rainWaterTrappingDynamic(arr), CoreMatchers.equalTo(11))
    }

    @Test
    fun `calculate rain water stack test iterative`() {
        val arr = intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1)
        val result = KotlinMain().rainWaterTrappedIterative(arr)
        assertThat(result, CoreMatchers.equalTo(6))
    }

    @Test
    fun `calculate rain water nested_loops`() {
        val arr = intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1)
        val result = KotlinMain().maxWater(arr)
        assertThat(result, CoreMatchers.equalTo(6))
    }

    @Test
    fun `calculate rain water nested_loops_2`() {
        val arr = intArrayOf(3,0,2,0,4)
        val result = KotlinMain().maxWater(arr)
        assertThat(result, CoreMatchers.equalTo(7))
    }

    @Test
    fun `calculate rain water with_pointers test`() {
        val arr = intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1)
        val result = KotlinMain().rainwaterTrappingWithPointers(arr)
        assertThat(result, CoreMatchers.equalTo(6))
    }

    @Test
    fun `calculate rain water stack test 1`() {
        val arr = intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1)
        val result = KotlinMain().rainWaterStackBased(arr)
        assertThat(result, CoreMatchers.equalTo(6))
    }

    @Test
    fun `calculate rain water stack test 2`() {
        val arr = intArrayOf(0,1,0,2,1,0,1,4,3,1,3,1)
        assertThat(KotlinMain().rainWaterStackBased(arr), CoreMatchers.equalTo(7))
    }

    @Test
    fun `calculate rain water stack test 3`() {
        val arr = intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1,3)
        assertThat(KotlinMain().rainWaterStackBased(arr), CoreMatchers.equalTo(11))
    }

    @Test
    fun `calculate rain water iterative test 1`() {
        val arr = intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1)
        val result = KotlinMain().rainWaterTrappingIterative(arr)
        assertThat(result, CoreMatchers.equalTo(6))
    }

    @Test
    fun `calculate rain water iterative test 2`() {
        val arr = intArrayOf(0,1,0,2,1,0,1,4,3,1,3,1)
        assertThat(KotlinMain().rainWaterTrappingIterative(arr), CoreMatchers.equalTo(7))
    }

    @Test
    fun `calculate rain water iterative test 3`() {
        val arr = intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1,3)
        assertThat(KotlinMain().rainWaterTrappingIterative(arr), CoreMatchers.equalTo(11))
    }

    @Test
    fun `calculate that the longest palindrome works test 1`() {
        val input =  "dabcba"
        val calculated = KotlinMain().longestPalindromeSubst(input)
        assertThat(calculated, equalTo("abcba"))
    }

    @Test
    fun `calculate that the longest palindrome works test 2`() {
        val input =  "dabcbad"
        val calculated = KotlinMain().longestPalindromeSubst(input)
        assertThat(calculated, equalTo("dabcbad"))
    }

    @Test
    fun `calculate that the longest palindrome_dp works test 1`() {
        val input =  "dabcba"
        val calculated = KotlinMain().longestPalindromeSubstr(input)
        assertThat(calculated, equalTo("abcba"))
    }

    @Test
    fun `calculate that the longest palindrome _dp_works test 2`() {
        val input =  "dabcbad"
        val calculated = KotlinMain().longestPalindromeSubstr(input)
        assertThat(calculated, equalTo("dabcbad"))
    }

    @Test
    fun `mergeSortTest 1`() {
        val ll1 = KotlinMain.SinglyLinkedListNode(1)
        val n2 = KotlinMain.SinglyLinkedListNode(2)
        n2.next = KotlinMain.SinglyLinkedListNode(3)
        ll1.next = n2

        val ll2 = KotlinMain.SinglyLinkedListNode(3)
        ll2.next = KotlinMain.SinglyLinkedListNode(4)

        var merged = KotlinMain().mergeSortLinkedLists(ll1, ll2)

        while (merged != null) {
            print("${merged.data} ")
            merged = merged.next
        }
        println()
    }

    @Test
    fun testConvertNtoM_1() {
        val n = 10
        val m = 20
        val calculated = KotlinMain().convertNtoM(n,m)
        assertThat(calculated, equalTo(1))
    }

    @Test
    fun testConvertNtoM_2() {
        val n = 10
        val m = 19
        val calculated = KotlinMain().convertNtoM(n,m)
        assertThat(calculated, equalTo(2))
    }

    @Test
    fun testConvertNtoM_2b() {
        val n = 10
        val m = 19
        val subject = KotlinMain()
        subject.operationsArray.fill(-1)

        val calculated = subject.minOperations(n,m)
        assertThat(calculated, equalTo(2))
    }

    @Test
    fun testConvertNtoM_3() {
        val n = 10
        val m = 21
        val subject = KotlinMain()
        subject.operationsArray.fill(-1)

        val calculated = subject.minOperations(n,m)
        assertThat(calculated, equalTo(8))
    }

}
