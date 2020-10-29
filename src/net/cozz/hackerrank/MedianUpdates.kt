package net.cozz.hackerrank

import java.util.*

class MedianUpdates {

}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val operation: MutableList<Pair<String, Int>> = mutableListOf()

    val n = scan.nextInt()
    for (i in 1..n) {
        val op = scan.next()
        val value = scan.nextInt()
        operation.add(Pair(op, value))
    }

    operation.forEach {
        printMedian(it.first.toLowerCase(), it.second)
    }
}

val minHeap: MutableList<Int> = mutableListOf()
val maxHeap: MutableList<Int> = mutableListOf()

fun printMedian(op: String, value: Int) {
    if (minHeap.isEmpty() && maxHeap.isEmpty() && op == "r") {
        println("Wrong!")
        return
    }

    if (op == "a") {
        if (minHeap.isEmpty() && maxHeap.isEmpty()) {
            minHeap.add(value)
        } else if (minHeap.isEmpty() && value <= maxHeap.first()) {
            minHeap.add(value)
        } else if (minHeap.isEmpty() && value > maxHeap.first()) {
            minHeap.add(maxHeap.first())
            maxHeap.remove(maxHeap.first())
            maxHeap.add(binarySearch(maxHeap, value), value)
        } else if (maxHeap.isEmpty() && minHeap.last() <= value) {
            maxHeap.add(value)
        } else if (maxHeap.isEmpty() && minHeap.last() > value) {
            maxHeap.add(minHeap.last())
            minHeap.remove(minHeap.last())
            minHeap.add(binarySearch(minHeap, value), value)
        } else { // both heaps have 1 one or more values
            if (minHeap.size >= maxHeap.size) {
                if (minHeap.last() > value) {
                    maxHeap.add(binarySearch(maxHeap, value), minHeap.last())
                    minHeap.remove(minHeap.last())
                    minHeap.add(binarySearch(minHeap, value), value)
                } else {
                    maxHeap.add(binarySearch(maxHeap, value), value)
                }
            } else if (maxHeap.first() >= value) { // maxHeap has more elements
                minHeap.add(binarySearch(minHeap, value), value)
            } else if (maxHeap.first() < value) {
                minHeap.add(maxHeap.first())
                maxHeap.remove(maxHeap.first())
                maxHeap.add(binarySearch(maxHeap, value), value)
            }
        }
    } else {
        if (minHeap.contains(value)) {
            minHeap.remove(value)
            if (minHeap.size < maxHeap.size) {
                minHeap.add(binarySearch(minHeap, maxHeap.first()), maxHeap.first())
                maxHeap.remove(maxHeap.first())
            }
        } else if (maxHeap.contains(value)) {
            maxHeap.remove(value)
            if (maxHeap.size < minHeap.size) {
                maxHeap.add(binarySearch(maxHeap, minHeap.last()), minHeap.last())
                minHeap.remove(minHeap.last())
            }
        } else {
            println("Wrong!")
            return
        }
    }

    when {
        minHeap.isEmpty() && maxHeap.isEmpty() -> println("Wrong!")
        minHeap.isEmpty() -> println(maxHeap.first())
        maxHeap.isEmpty() -> println(minHeap.first())
        minHeap.size > maxHeap.size -> println(minHeap.last())
        maxHeap.size > minHeap.size -> println(maxHeap.first())
        else -> {
            val sum: Long = minHeap.last().toLong() + maxHeap.first().toLong()
            if (sum % 2 == 0.toLong()) {
                println(sum / 2)
            } else {
                println("%.1f".format(sum / 2.toDouble()))
            }
        }
    }
}

fun binarySearch(sortedList: List<Int>, value: Int): Int {
    val candidate = Math.abs(Collections.binarySearch(sortedList, value) + 1)
    return candidate
}

/*
  // Functionally correct but too slow
val sortedList = mutableListOf<Int>()

fun printMedian(op: String, value: Int) {
    if ((sortedList.isEmpty() && op == "r")) {
        println("Wrong!")
        return
    }

    if (op == "a") {
        insert(value)
    } else {
        val index = binarySearch(value)
        if (index >= 0) {
            sortedList.removeAt(index)
        } else {
            println("Wrong!")
            return
        }
    }

    val index = sortedList.count() / 2
    when {
        sortedList.count() == 0 -> println("Wrong!")
        sortedList.count() == 1 -> println(sortedList[0])
        sortedList.count() % 2 == 0 -> {
            val sum: Long = sortedList[index - 1].toLong() + sortedList[index].toLong()
            if (sum % 2 == 0.toLong()) {
                println(sum / 2)
            } else {
                println("%.1f".format(sum / 2.toDouble()))
            }
        }
        else -> {
            println(sortedList[index])
        }
    }
}

fun insert(value: Int) {
    if (sortedList.isEmpty()) {
        sortedList.add(value)
        return
    }

    var index = binarySearch(value)
    if (index >= 0) {
        sortedList.add(index, value)
    } else {
        index = sortedList.indexOfFirst { it >= value }
        if (index >= 0) {
            sortedList.add(index, value)
        } else {
            index = if (value >= sortedList[0]) sortedList.count() else 0
            sortedList.add(index, value)
        }
    }
}

fun binarySearch(eleToSearch: Int) : Int{
    var low = 0
    var high = sortedList.size-1
    var mid:Int
    while(low <= high) {
        mid = (low + high)/2
        when {
            eleToSearch > sortedList[mid]  -> low = mid + 1
            eleToSearch == sortedList[mid] -> return mid
            eleToSearch < sortedList[mid]  -> high = mid - 1
        }
    }
    return -1
}
*/