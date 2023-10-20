package net.cozz.hackerrank

import java.util.regex.Matcher
import java.util.regex.Pattern

fun main(args: Array<String>) {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
    var ip = readLine()
    while (ip != null) {
        println(MyRegex().isValid(ip))
        ip = readLine()
    }
}

private class MyRegex {
    val zeroTo255 = "(\\d{1,2}|(0|1)\\d{2}|2[0-4]\\d|25[0-5])"
    val pattern: String = "$zeroTo255.$zeroTo255.$zeroTo255.$zeroTo255"

    val p: Pattern = Pattern.compile(pattern)

    fun isValid(ip: String?) : Boolean {
        var m: Matcher? = null
        ip?.let{
            m = p.matcher(ip)
        }
        return m?.matches() ?: false
    }
}