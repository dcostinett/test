package net.cozz

class Toptal {

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

        for (i in 0.until(coins.size)) { // for each coin
            for (j in coins[i]..target) { // for each coin value less than target
                ways[j] += ways[j - coins[i]] // add the value from previous sub problem (coin value)
            }
        }

        return ways[target]
    }

    fun printCoinCombos(arr: IntArray, target: Int) : List<List<Int>> {
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
}
