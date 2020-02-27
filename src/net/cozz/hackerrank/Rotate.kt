package net.cozz.hackerrank

import java.util.*

class Rotate {
    fun rotate(arr: Array<Int>, count: Int) : Array<Int> {
        return arr.copyOfRange(count, arr.size).plus(arr.copyOfRange(0, count))
    }
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val nd = scan.nextLine().split(" ")

    val n = nd[0].trim().toInt()

    val d = nd[1].trim().toInt()

    val a = scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()

    Rotate().rotate(a, d).forEach { print("$it ") }
}

