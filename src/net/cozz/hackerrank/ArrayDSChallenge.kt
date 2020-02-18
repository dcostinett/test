package net.cozz.hackerrank

import java.util.*


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

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val arr = Array<Array<Int>>(6) { Array<Int>(6) { 0 } }

    for (i in 0 until 6) {
        arr[i] = scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()
    }

    val result = hourglassSum(arr)

    println(result)
}