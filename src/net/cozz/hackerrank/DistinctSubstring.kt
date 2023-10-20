package net.cozz.hackerrank


class DistinctSubstring {


    fun distinctSubstring(str: String): Set<String>? {

        // Put all distinct substring in a HashSet
        val result: MutableSet<String> = HashSet()

        // List All Substrings
        for (i in 0..str.length) {
            for (j in i + 1..str.length) {

                // Add each substring in Set
                result.add(str.substring(i, j))
            }
        }

        // Return the HashSet
        return result
    }

}