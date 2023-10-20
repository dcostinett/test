package net.cozz.hackerrank

import java.util.*
import kotlin.jvm.JvmStatic

object LargestArea {
    @JvmStatic
    fun main(args: Array<String>) {


        val samplesRows = readLine()!!.trim().toInt()
        val samplesColumns = readLine()!!.trim().toInt()

        val samples = Array<Array<Int>>(samplesRows, { Array<Int>(samplesColumns, { 0 }) })

        for (i in 0 until samplesRows) {
            samples[i] = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()
        }

        val result = largestArea(samples)
    }

    fun largestArea(samples: Array<Array<Int>>): Int {
        var largestArea = 0

        val numRows = samples[0].size

        for (row in samples) {
            for (column in row) {

            }
        }

        return 0
    }
}