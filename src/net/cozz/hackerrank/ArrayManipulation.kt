package net.cozz.hackerrank

import java.io.File
import java.util.*

// Complete the arrayManipulation function below.
fun arrayManipulation(n: Int, queries: Array<Array<Int>>): Long {
    val results = LongArray(n + 1)

    queries.forEach {
        val (a, b, k) = it
        results[a - 1] = results[a - 1] + k
        if (b < results.size) results[b] = results[b] - k
    }

    var runningSum: Long = 0
    var max: Long = 0
    results.forEach {
        runningSum += it
        max = maxOf(max, runningSum)
    }

    return max
}

fun main(args: Array<String>) {
    val scan = Scanner(File("/Users/dancostinett/Downloads/input07.txt"))

    val nm = scan.nextLine().split(" ")

    val n = nm[0].trim().toInt()

    val m = nm[1].trim().toInt()

    val queries = Array<Array<Int>>(m, { Array<Int>(3, { 0 }) })

    for (i in 0 until m) {
        queries[i] = scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()
    }

    val result = arrayManipulation(n, queries)

    println(result)
}
