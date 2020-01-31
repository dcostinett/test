package net.cozz


fun main(args: Array<String>) {
    val map = mapOf("Al" to 68, "Chris" to 22, "Jessica" to 41, "Jeff" to 28, "Jenn" to 19, "Jim" to 22, "Jessica" to 30)

    sort(map)
}

private fun sort(names: Map<String, Int>) {

    val ofAge = names.filter {
        it.value >= 25
    }

    val sorted = ofAge.toSortedMap(compareBy<String> {
        it[it.length - 1]
    })

    sorted.forEach { n, v ->
        println(n)
    }
}