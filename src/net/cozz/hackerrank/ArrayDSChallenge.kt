package net.cozz.hackerrank

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet


class ArrayDSChallenge {
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

//    // this algorithm seems to wrap and count the adjoining land mass at the bottom...
//    fun largestArea(input: Array<Array<Int>>) : Int {
//        var largestArea = 0
//
//        for (i in 0.until(input.size)) {
//            for (j in 0.until(input[0].size)) {
//                if (input[i][j] == 1) {
//                    largestArea += checkSurrounding(input, i, j)
//                }
//            }
//        }
//
//        return largestArea
//    }
//
//    private fun checkSurrounding(arr: Array<Array<Int>>, row: Int, col: Int) : Int {
//        if (row < 0 || row >= arr.size || col < 0 || col >= arr[row].size || arr[row][col] == 0) {
//            return 0
//        }
//
//        arr[row][col] = 0
//        checkSurrounding(arr, row + 1, col)
//        checkSurrounding(arr, row - 1, col)
//        checkSurrounding(arr, row, col + 1)
//        checkSurrounding(arr, row, col - 1)
//        return 1
//    }

    fun largestIsland(grid: Array<Array<Int>>) : Int {
        if (grid.isEmpty()) return 0

        var max = 0
        for (i in 0.until(grid.size)) {
            for (j in 0.until(grid[0].size)) {
                if (grid[i][j] == 1) {
                    val area = dfs(grid, i, j, 0)
                    max = area.coerceAtLeast(max)
                }
            }
        }
        return max
    }

    fun largestIsland(grid: Array<IntArray>) : Int {
        if (grid.isEmpty()) return 0

        var max = 0
        for (i in 0.until(grid.size)) {
            for (j in 0.until(grid[0].size)) {
                if (grid[i][j] == 1) {
                    val area = dfs(grid, i, j, 0)
                    max = area.coerceAtLeast(max)
                }
            }
        }
        return max
    }

    private fun dfs(arr: Array<Array<Int>>, row: Int, col: Int, area: Int) : Int {
        var area = area
        if (row < 0 || row >= arr.size || col < 0 || col >= arr[row].size || arr[row][col] == 0) {
            return area
        }

        arr[row][col] = 0
        area++
        area = dfs(arr, row + 1, col, area)
        area = dfs(arr, row - 1, col, area)
        area = dfs(arr, row, col + 1, area)
        area = dfs(arr, row, col - 1, area)
        return area
    }

    private fun dfs(arr: Array<IntArray>, row: Int, col: Int, area: Int) : Int {
        var area = area
        if (row < 0 || row >= arr.size || col < 0 || col >= arr[row].size || arr[row][col] == 0) {
            return area
        }

        arr[row][col] = 0
        area++
        area = dfs(arr, row + 1, col, area)
        area = dfs(arr, row - 1, col, area)
        area = dfs(arr, row, col + 1, area)
        area = dfs(arr, row, col - 1, area)
        return area
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

        for (i in input.indices) {
            for (j in input[i].indices) {
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
//        bfs(arr, row - 1, col - 1)
        bfs(arr, row - 1, col)
//        bfs(arr, row - 1, col + 1)
        bfs(arr, row, col - 1)
        bfs(arr, row, col + 1)
//        bfs(arr, row + 1, col - 1)
        bfs(arr, row + 1, col)
//        bfs(arr, row + 1, col + 1)
        return 1
    }

    fun makeLargestIsland(grid: Array<IntArray>) : Int {
        if (grid.isEmpty()) return 0

        var max = 0
        var islandId = 2
        val islands = HashMap<Int, Int>()
        for (i in 0.until(grid.size)) {
            for (j in 0.until(grid[0].size)) {
                if (grid[i][j] == 1) {
                    val size = getIslandSize(grid, i, j, islandId)
                    max = max.coerceAtLeast(size)
                    islands[islandId++] = size
                }
            }
        }

        val directions = arrayOf(
            arrayOf(1, 0),
            arrayOf(-1, 0),
            arrayOf(0, 1),
            arrayOf(0, -1)
        )

        for (i in 0.until(grid.size)) {
            for (j in 0.until(grid[0].size)) {
                if (grid[i][j] == 0) {
                    val islandIds = HashSet<Int>()
                    for (direction in directions) {
                        val x = direction[0] + i
                        val y = direction[1] + j
                        if (x > -1 && y > -1 && x < grid.size && y < grid[0].size && grid[x][y] != 0) {
                            islandIds.add(grid[x][y])
                        }
                    }

                    var sum = 1
                    islandIds.forEach {
                        sum += islands[it]!!
                    }
                    max = max.coerceAtLeast(sum)
                }
            }
        }

        return max
    }

    // depth first search
    private fun getIslandSize(grid: Array<IntArray>, row: Int, col: Int, islandId: Int) : Int {
        if (row < 0 || row >= grid.size || col < 0 || col >= grid[row].size || grid[row][col] != 1) return 0

        grid[row][col] = islandId
        val left = getIslandSize(grid, row, col - 1, islandId)
        val right = getIslandSize(grid, row, col + 1, islandId)
        val up = getIslandSize(grid, row - 1, col, islandId)
        val down = getIslandSize(grid, row + 1, col, islandId)

        return left + right + up + down + 1
    }
}



fun containsWord(input: String, target: String): Int {
    var targetEncoding: String = ""

    for (c in target) {
        println(c)
        targetEncoding = targetEncoding.plus(encoding.get(c.toString()).toString())
    }

    println(targetEncoding)

    return input.indexOf(targetEncoding)
}

val encoding = mapOf("A" to ".-", "B" to "-...",
        "C" to "-.-.", "D" to "-..", "E" to ".",
        "F" to "..-.", "G" to "--.", "H" to "....",
        "I" to "..", "J" to ".---", "K" to "-.-",
        "L" to ".-..", "M" to "--", "N" to "-.",
        "O" to "---", "P" to ".--.", "Q" to "--.-",
        "R" to ".-.", "S" to "...", "T" to "-",
        "U" to "..-", "V" to "...-", "W" to ".--",
        "X" to "-..-", "Y" to "-.--", "Z" to "--..")


fun main(args: Array<String>) {
//    val scan = Scanner(System.`in`)
//
//    val arr = Array<Array<Int>>(6) { Array<Int>(6) { 0 } }
//
//    for (i in 0 until 6) {
//        arr[i] = scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()
//    }


    containsWord("......-...-..---", "HELLO")
//
//    val samplesRows = readLine()!!.trim().toInt()
//    val samplesColumns = readLine()!!.trim().toInt()
//
//    val samples = Array<Array<Int>>(samplesRows, { Array<Int>(samplesColumns, { 0 }) })
//
//    for (i in 0 until samplesRows) {
//        samples[i] = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()
//    }
//
//    val result = ArrayDSChallenge().largestArea(samples)
//
//    println(result)
}
