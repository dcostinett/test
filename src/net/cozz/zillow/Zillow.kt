package net.cozz.zillow

import org.apache.commons.lang3.tuple.ImmutablePair
import org.apache.commons.lang3.tuple.Pair
import java.util.*
import java.util.regex.Pattern

// Question :
// Given someone's calendar for a day as a series of blocked intervals [8,11], [12, 14], [10, 15], [16, 18], [17, 20]
// Write a function that takes in the calendar and a duration ‘d' (say 45min) as input, and returns all
// available intervals in that day that satisfy the duration. (Assume hours range from 0 - 24)
// Output: [0-8], [15-16], [20-24]
object Zillow {
    var timeslots: List<Pair<Int, Int>> = ArrayList()
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) { /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        val available = IntArray(24)
        val inputSet: MutableList<Pair<Int, Int>> = ArrayList()
        val scanner = Scanner(System.`in`)
        scanner.useDelimiter(", ")
        val line = scanner.nextLine()
        val numbers = Pattern.compile("(\\d+), *(\\d+)")
        val matcher = numbers.matcher(line)
        while (matcher.find()) {
            inputSet.add(ImmutablePair(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2))))
        }
        scanner.close()
        //        inputSet.add(new ImmutablePair<>(8,11));
//        inputSet.add(new ImmutablePair<>(12,14));
//        inputSet.add(new ImmutablePair<>(10,15));
//        inputSet.add(new ImmutablePair<>(16,18));
//        inputSet.add(new ImmutablePair<>(17,20));
//
        println(inputSet)
        println(findAvailable(inputSet, 45))
    }

    fun normalize(pairs: List<Pair<Int, Int>>): Map<Int, Int?> {
        pairs.sortedBy {
            it.left
        }
        val ranges: MutableMap<Int, Int?> = TreeMap()
        for (pair in pairs) {
            if (ranges.containsKey(pair.left)) {
                ranges[pair.left] = Math.max(pair.right, ranges[pair.left]!!)
            } else if (ranges.filter { it.key <= pair.left && it.value!! >= pair.left  }.isNotEmpty()) {
                
            } else {
                ranges[pair.left] = pair.right
            }
            // INCOMPLETE -- need to solve for pair.left in between existing range
        }
        return ranges
    }

    fun findAvailable(list: List<Pair<Int, Int>>, d: Int): List<Pair<Int, Int>> {
        val result: MutableList<Pair<Int, Int>> = ArrayList()
        val ranges = normalize(list)
        for (start in ranges.keys) {
            val end = ranges[start]!!
            if (start > 0) {
                result.add(ImmutablePair(0, start - 1))
            }
        }
        return result
    }
}