package net.cozz.hackerrank

import java.util.*


class ArrayDSChallenge {
    // Complete the reverseArray function below.
    fun reverseArray(a: Array<Int>): Array<Int> {
        return a.reversedArray()
    }

    fun mainold(args: Array<String>) {
        val scan = Scanner(System.`in`)

        val arrCount = scan.nextLine().trim().toInt()

        val arr = scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()

        val res = reverseArray(arr)

        println(res.joinToString(" "))
    }

    // Complete the hourglassSum function below.
    fun hourglassSum(arr: Array<Array<Int>>): Int {
        var candidate = Int.MIN_VALUE;
        for (i in 0..3) {
            for (j in 0..3) {
                candidate = Math.max(candidate, arr[i][j] + arr[i][j+1] + arr[i][j+2] + arr[i+1][j+1] + arr[i+2][j] + arr[i+2][j+1] + arr[i+2][j+2])
            }
        }

        return candidate
    }

    fun countIslands(input: Array<IntArray>) : Int {
        var count = 0

        for (i in 0.until(input.size)) {
            for (j in 0.until(input[i].size)) {
                if (input[i][j] == 1) {
                    count += dfs(input, i, j)
                }
            }
        }

        return count
    }

    private fun dfs(arr: Array<IntArray>, row: Int, col: Int) : Int {
        if (row < 0 || row >= arr.size || col < 0 || col >= arr[row].size || arr[row][col] == 0) {
            return 0
        }

        arr[row][col] = 0
        dfs(arr, row + 1, col)
        dfs(arr, row - 1, col)
        dfs(arr, row, col + 1)
        dfs(arr, row, col - 1)
        return 1
    }

    fun countIslandsBf(input: Array<IntArray>) : Int {
        var count = 0

        for (i in 0.until(input.size)) {
            for (j in 0.until(input[i].size)) {
                if (input[i][j] == 1) {
                    count += bfs(input, i, j)
                }
            }
        }

        return count
    }

    private fun bfs(arr: Array<IntArray>, row: Int, col: Int) : Int {
        if (row < 0 || row >= arr.size || col < 0 || col >= arr[row].size || arr[row][col] == 0) {
            return 0
        }

        arr[row][col] = 0
        bfs(arr, row - 1, col - 1)
        bfs(arr, row - 1, col)
        bfs(arr, row - 1, col + 1)
        bfs(arr, row, col - 1)
        bfs(arr, row, col + 1)
        bfs(arr, row + 1, col - 1)
        bfs(arr, row + 1, col)
        bfs(arr, row + 1, col + 1)
        return 1
    }
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val arr = Array<Array<Int>>(6) { Array<Int>(6) { 0 } }

    for (i in 0 until 6) {
        arr[i] = scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()
    }

    val result = ArrayDSChallenge().hourglassSum(arr)

    println(result)
}
