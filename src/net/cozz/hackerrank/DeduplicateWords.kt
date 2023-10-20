package net.cozz.hackerrank

import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

fun main(args: Array<String>) {

    val regex = "\\b(\\w+)(\\s+\\1\\b)+";
    val p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

    val scanner = Scanner(System.`in`);
    var numSentences = Integer.parseInt(scanner.nextLine());

    while (numSentences-- > 0) {
        var input = scanner.nextLine();

        val m = p.matcher(input);

        // Check for subsequences of input that match the compiled pattern
        while (m.find()) {
            input = input.replace(""/* The regex to replace */, m.group(0)/* The replacement. */);
        }

        // Prints the modified sentence.
        println(input);
    }

    scanner.close();
}
