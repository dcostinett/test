package net.cozz

class DigitMappings {
    fun digitMapping(digits: String) : List<String> {
        val result = mutableListOf<String>()
        if (digits.isEmpty()) {
            return result
        }

        val mapping = arrayOf("0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz")

        doDigitMapping(result, mapping, digits, "", 0)
        return result
    }

    fun doDigitMapping(result: MutableList<String>, mapping: Array<String>, digits: String, current: String, index: Int) {
        if (current.length == digits.length) {
            result.add(current)
            return
        }

        val letters = mapping[digits[index] - '0']
        letters.forEach {
            doDigitMapping(result, mapping, digits, current + it, index + 1)
        }
    }
}