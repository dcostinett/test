package net.cozz

import java.util.*
import java.util.stream.IntStream


object Toptal {

    // should be solved via dynamic programming -- see KotlinMain.minimizeCoinCount,
    // or possibly https://www.geeksforgeeks.org/coin-change-dp-7/
    fun getChange( M: Double,  P: Double): IntArray {
        val amount: Int = (M * 100).toInt()
        val price: Int = (P * 100).toInt()

        val response = intArrayOf(0,0,0,0,0,0)

        var change = amount - price

        val dollars = change / 100

        if (dollars > 0) {
            response[5] = dollars
            change -= (dollars * 100)
        }

        val fifties = (change / 50 )
        if (fifties > 0) {
            response[4] = fifties
            change -= (fifties * 50)
        }

        val tf = (change / 25 )
        if (tf > 0) {
            response[3] = tf
            change  -= (tf * 25)
        }

        val t = (change / 10)
        if (t > 0) {
            response[2] = tf
            change -= (t * 10)
        }

        val f = (change / 5 )
        if (f > 0) {
            response[1] = tf
            change -= (f * 5)
        }

        if (change > 0) {
            response[0] = change
        }

        response.forEach {
            print(it)
        }

        return response
    }

    // recursive structure from geeksforgeeks above
    fun getChangeRecursive(arr: IntArray, target: Int) : Int {
        return getChangeRecursive(arr, arr.size, target)
    }

    // count the number of ways we can sum coin values from value arr to reach target
    private fun getChangeRecursive(arr: IntArray, count: Int, target: Int) : Int {
        if (target == 0) {
            return 1
        }

        if (target < 0) {
            return 0
        }

        if (count <= 0 && target > 0) {
            return 0
        }

        return getChangeRecursive(arr, count - 1, target) +
                getChangeRecursive(arr, count, target - arr[count - 1])
    }

    // solve with dynamic programming
    fun getCountOfWaysToMakeChange(coins: IntArray, target: Int): Int {
        val ways = IntArray(target + 1)
        ways.fill(0)

        ways[0] = 1 // there's only 1 way to give 0 change

        for (i in coins.indices) { // for each coin
            for (j in coins[i]..target) { // for each coin value less than target
                ways[j] += ways[j - coins[i]] // add the value from previous sub problem (coin value)
            }
        }

        return ways[target]
    }

    fun getCountOfWaysToMakeChange2(coins: IntArray, target: Int): Int {
        val ways = arrayOfNulls<Int>(target + 1)
        ways.fill(0)

        ways[0] = 1 // there's only 1 way to give 0 change

        for (i in coins.indices) { // for each coin
            for (j in coins[i]..target) { // for each coin value less than target
                ways[j] = ways[j]?.plus(ways[j - coins[i]]!!) // add the value from previous sub problem (coin value)
            }
        }

       for (amount in valueMap.entries) {
           val amt = amount.key
           val v = amount.value
       }

        return ways[target]!!
    }

    val valueMap : Map<String, Double> = mapOf("PENNY" to .01, "NICKEL" to .05, "DIME" to .10, "QUARTER" to .25, "HALF DOLLAR" to .50,
        "ONE" to 1.00, "TWO" to 2.00, "FIVE" to 5.00, "TEN" to 10.00, "TWENTY" to 20.00, "FIFTY" to 50.00, "ONE HUNDRED" to 100.00)


    fun printCoinCombos(arr: IntArray, target: Int) : List<List<Int>> {
        val filtered = valueMap.filter { it.value > target }
        return printCoinCombos(0, arr, target, IntArray(arr.size))
    }


    val result = mutableListOf<List<Int>>()
    private fun printCoinCombos(index: Int, coins: IntArray, target: Int, coinsInTarget: IntArray) : List<List<Int>> {

        if (target == 0) {
            result.add(coinsInTarget.toList())
            println(coinsInTarget.joinToString(" "))
            return result
        }

        if (index == coins.size) {
            return result
        }

        var i = 0
        while (i * coins[index] <= target) {
            coinsInTarget[index] = i // for each coin less than the target value
            printCoinCombos(index + 1, coins, target - i * coins[index], coinsInTarget)
            coinsInTarget[index] = 0
            i++
        }

        return result
    }

    fun getOptimizedChange(pp: Double, cash: Double) : List<String?> {
        if (pp > cash) return listOf("ERROR")
        if (pp == cash) return listOf("ZERO")

        val changeAmount = cash - pp

        val waysToMakeChange = arrayOfNulls<String>(changeAmount.toInt() + 1)
        waysToMakeChange.fill("")

        waysToMakeChange[0] = "ZERO"

        for (i in valueMap.entries) {
            val key = i.key
            val changeValue = i.value
            val values = valueMap.values.toList().filter { it < changeAmount }
            values.forEachIndexed { index, value ->
                // FIXME
//                waysToMakeChange[index] = waysToMakeChange[index]?.plus(waysToMakeChange[])
            }
        }

        return waysToMakeChange.toList()
    }

}


// from https://stackoverflow.com/questions/66079902/coin-change-logic?rq=4
object Change {
    /** The number of unique coins.  */
    val VALUES = intArrayOf(10, 20, 50, 100, 200)
    val WEIGHTS = intArrayOf(4, 3, 2, 1, 0)
    val SUPPLY = intArrayOf(10, 35, 40, 100, 2)
    var result = arrayOf(
        intArrayOf(Int.MAX_VALUE), intArrayOf( // The resulting combination of coins
            0, 0, 0, 0, 0
        )
    )

    @JvmStatic
    fun main(args: Array<String>) {
        val change = 160
        solve(IntArray(VALUES.size), change)
        if (result[0][0] == Int.MAX_VALUE) {
            println(
                "Can't return the change with the given SUPPLY of coins"
            )
        } else {
            println(result[1].contentToString())
        }
    }

    fun solve(c: IntArray, change: Int) {
        // check if out of supply
        val isOutOfSupply = IntStream.range(0, VALUES.size).anyMatch { i: Int ->
            SUPPLY[i] < c[i]
        }
        if (isOutOfSupply) return

        // compute weight
        val weight = IntStream.range(0, VALUES.size).map { i: Int -> WEIGHTS[i] * c[i] }.sum()

        // compute sum
        val sum = IntStream.range(0, VALUES.size).map { i: Int -> VALUES[i] * c[i] }.sum()
        if (sum == change && weight < result[0][0]) {
            result[0][0] = weight
            result[1] = c
        } else if (sum < change) {
            IntStream.range(0, VALUES.size).forEach { i: Int -> solve(increment(c, i), change) }
        }
    }

    private fun increment(array: IntArray, index: Int): IntArray {
        val clone = array.clone()
        clone[index]++
        return clone
    }
}

/*
    We have input values of N and an array Coins
    that holds all of the coins. We use data type
    of long because we want to be able to test
    large values without integer overflow
*/


internal object GetWays {
    private fun getNumberOfWays(change: Long, coins: IntArray): Long {
        // Create the ways array to 1 plus the amount
        // to stop overflow
        val ways = LongArray(change.toInt() + 1)

        // Set the first way to 1 because its 0 and
        // there is 1 way to make 0 with 0 coins
        ways[0] = 1

        // Go through all of the coins
        for (i in coins.indices) {

            // Make a comparison to each index value
            // of ways with the coin value.
            for (j in ways.indices) {
                if (coins[i] <= j) {

                    // Update the ways array
                    ways[j] += ways[(j - coins[i])]
                }
            }
        }

        // return the value at the Nth position
        // of the ways array.
        return ways[change.toInt()]
    }

    private fun printArray(coins: IntArray) {
        for (i in coins) println(i)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val coins = intArrayOf(1, 5, 10)
        println("The Coins Array:")
        printArray(coins)
        println("Solution:")
        println(getNumberOfWays(21, coins))
    }
}

//from Geeks for Geeks: https://www.geeksforgeeks.org/coin-change-dp-7/
internal object GFGCoinChange {
    // Recursive function to count the number of distinct
    // ways to make the sum by using n coins
    fun count(
        coins: IntArray, sum: Int, n: Int,
        dp: Array<IntArray>
    ): Int {
        // Base Case
        if (sum == 0) return 1.also { dp[n][sum] = it }

        // If number of coins is 0 or sum is less than 0 then
        // there is no way to make the sum.
        if (n == 0 || sum < 0) return 0

        // If the subproblem is previously calculated then
        // simply return the result
        return if (dp[n][sum] != -1) dp[n][sum] else (count(coins, sum - coins[n - 1], n, dp)
                + count(coins, sum, n - 1, dp)).also { dp[n][sum] = it }

        // Two options for the current coin
    }

    // Driver code
    @JvmStatic
    fun main(args: Array<String>) {
        val sum = 12
        val coins = intArrayOf(1, 5, 10)
        val n: Int = coins.size
        val dp = Array(n + 1) { IntArray(sum + 1) }
        for (row in dp) Arrays.fill(row, -1)
        val res = count(coins, sum, n, dp)
        println(res)
    }
}

fun main() {
    do {
        val line = readLine()
        if (line != null)
            println(parseLine(line)[0].toString() + ", " + parseLine(line)[1].toString())
    } while (line != null)
}

private fun parseLine(input: String) : FloatArray {
    val (pp, ch) = input.split(";")
    return floatArrayOf(pp.toFloat(),ch.toFloat())
}

