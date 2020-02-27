package net.cozz

import junit.framework.Assert
import org.apache.commons.lang3.StringUtils
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.nio.file.Files
import java.nio.file.Paths
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.Logger
import javax.print.attribute.IntegerSyntax

const val SCHEDULE_DATE_FORMAT_FULL = "E, MMMM dd 'at' hh:mm aa"
private const val INSTANCE_IDS_FILE_NAME = "test"
private val LOGGER = Logger.getLogger(Main::class.java.name)

class KotlinMain {

    val dateFormat: DateFormat = SimpleDateFormat(SCHEDULE_DATE_FORMAT_FULL)
    private val createdInstanceIdsStore = Paths.get(INSTANCE_IDS_FILE_NAME)
    val hs: MutableSet<String> = HashSet()
    private val LOG = Logger.getLogger(Main::class.java.name)
    var books = arrayOf(arrayOf("Moby Dick", "Herman Melville", "classic", "19.99", "Ishmael narrates the monomaniacal quest of Ahab, captain of the whaler Pequod, for revenge on the albino sperm whale Moby Dick"), arrayOf("Catcher In The Rye", "J. D. Salinger", "classic", "6.49", "The novel's protagonist Holden Caulfield has become an icon for teenage rebellion. The novel also deals with complex issues of identity, belonging, loss, connection, and alienation."), arrayOf("Ender's Game", "Orson Scott Card", "sci-fi", "8.20", "Ender's Game presents an imperiled mankind after two conflicts with the \"buggers\", an insectoid alien species. In preparation for an anticipated third invasion, children, including the novel's protagonist, Ender Wiggin, are trained from a very young age through increasingly difficult games including some in zero gravity, where Ender's tactical genius is revealed."), arrayOf("The Hobbit", "JRR Tolkien", "fantasy", "18.59", "The Hobbit follows the quest of home-loving hobbit Bilbo Baggins to win a share of the treasure guarded by the dragon, Smaug."))

    // find smallest array of matching degree -- degree defined as most frequently occurring integer in int[]
    // find palindrome substring within a string
    // write code to translate input integer to "English" -- 4,321 to "Four thousand, three hundred twenty one"
    // write code to re-order a linked list, moving the last element to the 2nd element until there are only 2 elements

    @Throws(Exception::class)
    fun main(args: Array<String>) {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val target = br.readLine().toInt()
        var root: Node? = null
        if (target < 1) {
            println("NIL")
        } else {
            val data = br.readLine()
            val stringTokenizer = StringTokenizer(data)
            val numbers: List<String> = ArrayList()
            root = Node(stringTokenizer.nextToken())
            var node = root
            while (stringTokenizer.hasMoreElements()) {
                node!!.next = Node(stringTokenizer.nextToken())
                node = node.next
            }
            val targetNode = nthToLast(root, target - 1)
            if (targetNode == null) {
                println("NIL")
            } else {
                println(targetNode.strData!!.trim { it <= ' ' })
            }
        }

        if (true) System.exit(0)
        LOGGER.info(dateFormat.format(Date()))
        passByRrefTest()
        // dequeMain();
        doFoodTest()
        arrayTests()
        val forecastDate = Date()
        val dayFormatter: DateFormat = SimpleDateFormat("EEE K a", Locale.US)
        LOGGER.info(dayFormatter.format(forecastDate))
        val timeFormatter: DateFormat = SimpleDateFormat("", Locale.US)
        val logFiles = File(".")
                .list { dir, name ->
                    LOGGER.info(dir.absolutePath)
                    LOGGER.info("Name = $name")
                    name.startsWith("test")
                }
        val sorted = Arrays.asList(*logFiles)
        Collections.sort(sorted)
        val numberFormat: NumberFormat = DecimalFormat("000")
        var num = sorted[sorted.size - 1]
                .substring(sorted[sorted.size - 1].indexOf("_") + 1).toLong()
        val outPath = Paths.get(String.format("%s_%s", INSTANCE_IDS_FILE_NAME,
                numberFormat.format(++num)))
        try {
            Files.copy(createdInstanceIdsStore, Files.newOutputStream(outPath))
        } catch (e: IOException) {
            LOGGER.warning(e.message)
        }
        LOGGER.info(sorted[sorted.size - 1])
        LOGGER.info(removeDups(charArrayOf('a', 'a', 'b', 'c', 'c')))
        LOGGER.info(removeDups("TTEST"))
        LOGGER.info(reverseWords("this is a test"))
        LOGGER.info(revWords("this is a test"))
        LOGGER.info("" + (atoI("123") + 1))
    }

    fun revS(s: String?): String? {
        return if (s == null || s.length == 1) {
            s
        } else revS(s.substring(1)) + s[0]
    }

    private fun hackerRankJavaGenerics() { //printArray();
    }

    private fun printArray(arr: Array<Any>) {
        for (i in arr.indices) {
            println(arr[i])
        }
    }

    private fun hackerRankJavaHashset() {
        val s = Scanner(System.`in`)
        val t = s.nextInt()
        val pair_left = arrayOfNulls<String>(t)
        val pair_right = arrayOfNulls<String>(t)
        for (i in 0 until t) {
            pair_left[i] = s.next()
            pair_right[i] = s.next()
        }
        val left_right: MutableSet<String> = LinkedHashSet(t)
        for (i in 0 until t) {
            left_right.add(String.format("%s %s", pair_left[i], pair_right[i]))
            println(left_right.size)
        }
    }

    var validBrackets = loadValidValues()
    fun hackerRankJavaStack() {
        val sc = Scanner(System.`in`)
        while (sc.hasNext()) {
            val input = sc.next()
            println(processStrings(input))
        }
    }

    private fun loadValidValues(): Map<Char, Char?> {
        val validValues: MutableMap<Char, Char?> = HashMap(3)
        validValues['['] = ']'
        validValues['{'] = '}'
        validValues['<'] = '>'
        validValues['('] = ')'
        return validValues
    }

    private fun processStrings(input: String): Boolean {
        if (input.length % 2 != 0) {
            return false
        }
        val chars = Stack<Char>()
        for (ch in input.toCharArray()) {
            if (validBrackets.containsKey(ch)) {
                chars.push(ch)
            } else {
                if (validBrackets.containsValue(ch)) {
                    // if the stack is empty, or this isn't the closing version of the last item in the stack
                    if (chars.empty() || validBrackets[chars.pop()] != ch) {
                        return false
                    }
                }
            }
        }
        // we unloaded the stack every time we got a closing bracket, if the stack isn't empty, then it's not balanced
        return chars.empty()
    }

    fun hackerRankJavaMap() {
        val pb: MutableMap<String, Long?> = TreeMap()
        val sc = Scanner(System.`in`)
        val pbSize = sc.nextInt()
        sc.nextLine()
        for (i in 0 until pbSize) {
            val key = sc.nextLine()
            val `val` = sc.nextLine().toLong()
            pb[key] = `val`
        }
        while (sc.hasNext()) {
            val query = sc.nextLine()
            if (pb.containsKey(query)) {
                println(String.format("%s=%s", query, pb[query]))
            } else {
                println("Not found")
            }
        }
        sc.close()
    }

    fun hackerRankJavaList() {
        val myList: MutableList<Int> = ArrayList()
        val sc = Scanner(System.`in`)
        val listSize = sc.nextInt()
        sc.nextLine()
        for (i in 0 until listSize) {
            myList.add(sc.nextInt())
        }
        val queryCount = sc.nextInt()
        for (i in 0 until queryCount) {
            val command = sc.next()
            sc.nextLine()
            when (command.toLowerCase()) {
                "insert" -> {
                    val index = sc.nextInt()
                    val `val` = sc.nextInt()
                    sc.nextLine()
                    myList.add(index, `val`)
                }
                "delete" -> {
                    val delIndex = sc.nextInt()
                    myList.removeAt(delIndex)
                    if (i < queryCount - 1) {
                        sc.nextLine()
                    }
                }
            }
        }
        for (integer in myList) {
            print(String.format("%d ", integer))
        }
        sc.close()
    }

    fun solution(A: IntArray, B: IntArray, M: Int, X: Int, Y: Int): Int {
        var result = 0
        var weight: Long = 0
        val floors: MutableSet<Int> = TreeSet()
        val theQueue: Queue<Int> = LinkedList()
        for (i in A.indices) {
            theQueue.add(A[i])
        }
        var personCount = 0
        var floorIndex = 0
        while (!theQueue.isEmpty()) {
            weight += theQueue.peek()
            if (weight < Y && personCount < X) {
                theQueue.poll()
                floors.add(B[floorIndex++])
                personCount++
            } else { // we're at the size or weight limit. send the elevator
                result += floors.size + 1 // add an additional stop for the return trip
                floors.clear()
                weight = 0
                personCount = 0
            }
            if (theQueue.isEmpty()) {
                result += floors.size + 1 // add an additional stop for the return trip
                floors.clear()
                weight = 0
                personCount = 0
            }
        }
        return result
    }

    fun bestBuy01(A: IntArray): Int {
        var result = -1
        for (i in A.indices) {
            if (isEquilibriumIndex(i, A)) {
                result = i
                break
            }
        }
        return result
    }

    fun isEquilibriumIndex(i: Int, A: IntArray): Boolean {
        var lowSum = 0
        var highSum = 0
        for (j in 0 until i) {
            lowSum += A[j]
        }
        for (j in A.size - 1 downTo i + 1) {
            highSum += A[j]
        }
        return lowSum == highSum
    }

    /*
        Kth Most frequent element.
        int[] input = {1,2,3,4,4,2,4,5,5,2,2,2}
        k=2;
        2,4
     */
    fun getKthMostFrequent(input: IntArray, k: Int): Int {
        val hist = HashMap<Int, Int>()
        for (i in input) {
            if (hist.containsKey(i)) {
                hist[i] = hist[i]!! + 1
            } else {
                hist[i] = 1
            }
        }
        val sorted = sortByValues(hist)
        val keyset: Set<Map.Entry<Int, Int>> = sorted.entries
        val theList: MutableList<Map.Entry<Int, Int>> = ArrayList()
        theList.addAll(keyset)
        return theList[k].key
    }

    fun sortList(input: List<Int>) {
        val histogram: MutableMap<Int, Int> = TreeMap()
        for (i in input) {
            if (!histogram.containsKey(i)) {
                histogram[i] = 1
            } else {
                histogram[i] = histogram[i]!! + 1
            }
        }
        val sorted = sortByValue(histogram)
        for ((key, value) in sorted) {
            for (i in 0 until value!!) {
                println(key.toString())
            }
        }
    }

    /**
     * Alternate sort by values implementation
     *
     * @param map
     * @return
     */
    private fun sortByValues(map: HashMap<Int, Int>): HashMap<Int, Int> {
        val list = map.toList().sortedBy { it.second }
        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        val sortedHashMap: HashMap<Int, Int> = LinkedHashMap<Int, Int>()
        val it: Iterator<Pair<Int, Int>> = list.iterator()
        while (it.hasNext()) {
            val entry = it.next() as Map.Entry<Int, Int>
            sortedHashMap[entry.key] = entry.value
        }
        return sortedHashMap
    }

    /**
     * Sort map by values
     *
     * @param map
     * @return
     */
    fun sortByValue(map: Map<Int, Int>): Map<Int, Int?> {
        val list: List<Map.Entry<Int, Int>> = LinkedList(map.entries)
        val sorted = list.sortedWith(MyKVComparator())
        val sorted2 = list.sortedBy { it.value } // basically same as above...
        val result: MutableMap<Int, Int?> = LinkedHashMap()
        for ((key, value) in sorted) {
            result[key] = value
        }
        return result
    }

    fun prac5() {
        val sc = Scanner(System.`in`)
        val t = sc.nextInt()
        for (i in 0 until t) {
            try {
                val x = sc.nextLong()
                println("$x can be fitted in:")
                if (x >= -128 && x <= 127) println("* byte")
                //Complete the code
                if (x >= -Math.pow(2.0, 16.0) && x <= Math.pow(2.0, 16.0)) println("* short")
                if (x >= -Math.pow(2.0, 32.0) && x <= Math.pow(2.0, 32.0)) println("* int")
                if (x >= -Math.pow(2.0, 64.0) && x <= Math.pow(2.0, 64.0)) println("* long")
            } catch (e: Exception) {
                println(sc.next() + " can't be fitted anywhere.")
            }
        }
    }

    fun prac4() {
        val `in` = Scanner(System.`in`)
        val t = `in`.nextInt()
        for (i in 0 until t) {
            val a = `in`.nextInt()
            val b = `in`.nextInt()
            val n = `in`.nextInt()
            for (j in 0 until n) print(String.format("%1.0f ", a + (Math.pow(2.0, j + 1.toDouble()) - 1) * b))
            println()
        }
        `in`.close()
    }

    fun prac3() {
        val sc = Scanner(System.`in`)
        val count = sc.nextInt()
        for (i in 1..10) {
            println(String.format("%d x %d = %d", count, i, count * i))
        }
    }

    fun prac2() {
        val sc = Scanner(System.`in`)
        println("================================")
        for (i in 0..2) {
            val s1 = sc.next()
            val x = sc.nextInt()
            //Complete this line
            print(String.format("%-15s%03d", s1, x))
            println()
        }
        println("================================")
    }

    fun sum(arr: IntArray): Int {
        var sum = 0
        for (i in 1 until arr.size) {
            sum += arr[i]
        }
        return sum
    }

    // fail
    fun practice1(n: Int) {
        for (i in n downTo 1) {
            for (j in 0..n - i) {
                if (i < j - n) print(" ") else print("#")
            }
            println()
        }
    }

    /*
practice 1 answer:
    for(int j=0;j<num;j++){
    for(int i=1;i<=num;i++){
        System.out.print(i<num-j?" ":"#");
    }
    System.out.println("");
*/
//    public static void challenge3() {
//        Scanner scanner = new Scanner(System.in);
//        Pattern pattern = Pattern.compile("(\\d,\\d)");
//
//        String pointStr = scanner.findInLine(pattern);
//        Point2D p1 = parsePoint2D(pointStr);
//
//        pointStr = scanner.findInLine(pattern);
//        Point2D p2 = parsePoint2D(pointStr);
//
//        pointStr = scanner.findInLine(pattern);
//        Point2D p3 = parsePoint2D(pointStr);
//
//        pointStr = scanner.findInLine(pattern);
//        Point2D p4 = parsePoint2D(pointStr);
//
//        if (isSquare(p1, p2, p3, p4)) {
//            System.out.println(true);
//        } else {
//            System.out.println(false);
//        }
//    }
//
//    private static boolean isSquare(Point2D p1, Point2D p2, Point2D p3, Point2D p4) {
//        List<Double> distances = new ArrayList<>();
//        distances.add(p1.distance(p2));
//        distances.add(p1.distance(p3));
//        distances.add(p1.distance(p4));
//
//        distances.sort(null);
//
//        // we should have 2 equal lengths and a 3rd that's the sqrt of the sum of the squares of those lengths
//
//        if (Math.abs(distances.get(0) - distances.get(1)) >= .000001) {
//            return false;
//        }
//
//        return Math.abs(distances.get(2) - Math.sqrt(Math.pow(distances.get(0), 2) + Math.pow(distances.get(1), 2))) <= .00001;
//    }
//
    @Throws(IOException::class)
    fun challenge2() {
        val scanner = Scanner(System.`in`)
        val pointCount = scanner.nextInt()
        val points: MutableList<Point> = ArrayList(pointCount)
        val shortestDistance = loadPoints(pointCount, scanner, points)
        val nf: NumberFormat = DecimalFormat("#.0000")
        println(nf.format(shortestDistance.toDouble()))
    }

    private fun loadPoints(pointCount: Int, scanner: Scanner, points: MutableList<Point>): Float {
        var candidate = Float.MAX_VALUE
        for (i in 0 until pointCount) {
            val point = Point(scanner.nextInt(), scanner.nextInt())
            for (p in points) {
                if (p.distanceFrom(point) < candidate) {
                    candidate = p.distanceFrom(point)
                }
            }
            points.add(point)
            scanner.nextLine()
        }
        val sentinel = scanner.nextInt()
        // assert sentinal == 0; // lots of ways to do this, there were 2 terminal conditions though... not sure which wins
        return candidate
    }

    @Throws(IOException::class)
    fun challenge1(args: Array<String?>?) {
        val chains: MutableMap<String, String?> = HashMap()
        val reader = BufferedReader(InputStreamReader(System.`in`))
        var s = ""
        while (reader.readLine().also { s = it } != null) {
            parseMap(s, chains)
            val end = followChains("BEGIN", chains)
            if (end == "END" && chains.isEmpty()) {
                println("GOOD")
            } else {
                println("BAD")
            }
        }
    }

    fun parseMap(input: String, chains: MutableMap<String, String?>) {
        val elements = input.split(";".toRegex()).toTypedArray()
        for (s in elements) {
            val chain = s.split("-".toRegex()).toTypedArray()
            // assert chain.length == 2;
// assert inRange(chain[1], 2, 10000
            chains[chain[0]] = chain[1]
        }
    }

    fun followChains(begin: String?, chains: MutableMap<String, String?>): String? { // assert !chains.empty()
        var nextKey = chains[begin]
        if (nextKey == null || !chains.containsKey(nextKey)) {
            return "bad"
        }
        chains.remove(begin)
        while (!chains.isEmpty()) {
            nextKey = chains.remove(nextKey)
            if (nextKey == null) {
                return "bad"
            }
        }
        return nextKey
    }

    fun isPairSummingToN(sortedArray: IntArray, target: Int): Boolean {
        var li = 0
        var hi = sortedArray.size - 1
        var found = false
        while (!found && li < hi) {
            when {
                sortedArray[li] + sortedArray[hi] == target -> {
                    found = true
                }
                sortedArray[li] + sortedArray[hi] < target -> {
                    li++
                }
                else -> {
                    hi--
                }
            }
        }
        return found
    }

    fun reverseWords(words: String): String {
        var words = words
        val sb = StringBuilder(words.length)
        // start at the end
        var bw = words.length - 1
        while (words.length > 0) {
            while (bw >= 0 && isWhite(words[bw])) {
                bw--
            }
            if (bw < words.length - 1) { // append the trailing whitespace
                sb.append(words.substring(bw + 1))
                words = words.substring(0, bw + 1)
            }
            while (bw >= 0 && !isWhite(words[bw])) {
                bw--
            }
            // append the word
            sb.append(words.substring(bw + 1))
            words = words.substring(0, bw + 1)
        }
        return sb.toString()
    }

    fun getDay(day: String?, month: String?, year: String?): String {
        val cal = GregorianCalendar.getInstance()
        cal[Calendar.YEAR] = Integer.valueOf(year)
        cal[Calendar.MONTH] = Integer.valueOf(month) - 1
        cal[Calendar.DAY_OF_MONTH] = Integer.valueOf(day)
        return SimpleDateFormat("EEE").format(cal.time).toUpperCase()
    }

    // A utility function to print a substring str[low..high]
    fun printSubStr(str: String, low: Int, high: Int) {
        println(str.substring(low, high + 1))
    }

    // This function prints the longest palindrome substring
    // of str[].
    // It also returns the length of the longest palindrome
    fun longestPalSubstr(str: String): Int {
        val n = str.length // get length of input string
        // table[i][j] will be false if substring str[i..j]
        // is not palindrome.
        // Else table[i][j] will be true
        val table = Array(n) { BooleanArray(n) }
        // All substrings of length 1 are palindromes
        var maxLength = 1
        for (i in 0 until n) {
            table[i][i] = true
        }
        // check for sub-string of length 2.
        var start = 0
        for (i in 0 until n - 1) {
            if (str[i] == str[i + 1]) {
                table[i][i + 1] = true
                start = i
                maxLength = 2
            }
        }
        // Check for lengths greater than 2. k is length of substring
        for (k in 3..n) { // Fix the starting index
            for (i in 0 until n - k + 1) { // Get the ending index of substring from starting index i and length k
                val j = i + k - 1
                // checking for sub-string from ith index to
                // jth index iff str.charAt(i+1) to
                // str.charAt(j-1) is a palindrome
                if (table[i + 1][j - 1] && str[i] ==
                        str[j]) {
                    table[i][j] = true
                    if (k > maxLength) {
                        start = i
                        maxLength = k
                    }
                }
            }
        }
        print("Longest palindrome substring is; ")
        printSubStr(str, start, start + maxLength - 1)
        return maxLength // return length of LPS
    }

    // Google's fruit into baskets question -- maximize the count
    fun longestSubstring(tree: IntArray) : Int {
        if (tree.isEmpty()) {
            return 0
        }

        var count = 1
        val map = mutableMapOf<Int, Int>()

        var i = 0
        var j = 0
        while (j < tree.size) {
            if (map.size <= 2) {
                map.put(tree[j], j++)
            }

            if (map.size > 2) {
                var min = tree.size - 1
                map.values.forEach {
                    min = Math.min(min, it)
                }
                i = min + 1
                map.remove(tree[min])
            }

            count = Math.max(count, j - i)
        }

        return count
    }

    // https://www.programcreek.com/2013/12/leetcode-solution-of-longest-palindromic-substring-java/
    fun longestPalindromeSubstr(str: String?): String? {
        if (str == null || str.length == 0) {
            return str
        }
        val len = str.length
        var maxLen = 1
        val matrix = Array(len) { BooleanArray(len) }
        var candidate: String? = null
        for (i in 0 until len) {
            for (j in 0 until len - i) {
                val k = i + j
                if (str[j] == str[k] && (k - j <= 2 || matrix[j + 1][k - 1])) {
                    matrix[j][k] = true
                    if (maxLen < k - j + 1) {
                        maxLen = k - j + 1
                        candidate = str.substring(j, k + 1)
                    }
                }
            }
        }
        return candidate
    }

    // also https://www.programcreek.com/2013/12/leetcode-solution-of-longest-palindromic-substring-java/
    // a simple algorithm
    fun longestPalindromeSubst(s: String?): String? {
        if (s == null || s.length == 0) {
            return s
        }
        var candidate = s.substring(0, 1)
        for (i in 0 until s.length) {        // get longest  pal centered at index i
            var tmp = getPalindrome(s, i, i) // handles odd numbered strings (the entire string is a palindrome)
            if (tmp.length > candidate.length) {
                candidate = tmp
            }
            //
            tmp = getPalindrome(s, i, i + 1) // handles even numbered strings
            if (tmp.length > candidate.length) {
                candidate = tmp
            }
        }
        return candidate
    }

    private fun getPalindrome(s: String, beginIdx: Int, endIdx: Int): String {
        var beginIdx = beginIdx
        var endIdx = endIdx
        while (beginIdx >= 0 && endIdx < s.length && s[beginIdx] == s[endIdx]) {
            beginIdx--
            endIdx++
        }
        return s.substring(beginIdx + 1, endIdx)
    }

    private fun doFoodTest() {}
    fun transform(input: String?): String {
        val toRemove = arrayOf("{b}", "{/b}")
        val replaceWith = arrayOf("<b>", "</b>")
        return StringUtils.replaceEach(input, toRemove, replaceWith)
    }

    fun arrayTests() {
        val a = Array(5) { Array(5) { Array(6) { IntArray(10) } } }
        val b: Array<Array<IntArray>?> = arrayOfNulls(5)
        LOGGER.fine("" + b[0]?.get(0)?.size) //NPE
    }

    fun passByRrefTest() {
        val aDog = Dog("Max")
        foo(aDog)
        if (aDog.name == "Max") { //true
            println("Java passes by value.")
        } else if (aDog.name == "Fifi") { // you can change the properties of aDog, but you can't change the memory location it points to
            println("Java passes by reference.")
        }
    }

    fun foo(d: Dog) {
        var d = d
        d.name == "Max" // true
        d = Dog("Fifi")
        Assert.assertTrue(d.name == "Fifi") // true
    }

    fun removeDups(input: String): String {
        val stringBuilder = StringBuilder()
        val uniqueChars = TreeSet<Char>()
        for (ch in input.toCharArray()) {
            if (!uniqueChars.contains(ch)) {
                uniqueChars.add(ch)
                stringBuilder.append(ch)
            }
        }
        return stringBuilder.toString()
    }

    fun removeDups(chars: CharArray): String {
        Arrays.sort(chars)
        var index = 0
        for (i in chars.indices) {
            if (chars[i] != chars[index]) {
                index++
                chars[index] = chars[i]
            }
        }
        val result = Arrays.copyOf(chars, index + 1)
        return String(result)
    }

    fun printBinaryRep(i: Int) {
        var i = i
        val s: Stack<Int> = Stack<Int>()
        while (i > 0) {
            if (i % 2 > 0) {
                s.push(1)
            } else {
                s.push(0)
            }
            i = i shr 1
        }
        val sb = StringBuilder(s.size)
        while (s.size > 0) {
            sb.append(s.pop())
        }
        LOGGER.info(sb.toString())
    }

    fun revWords(words: String): String {
        val sb = StringBuilder(words.length)
        val wordArr = words.split(" ".toRegex()).toTypedArray()
        for (i in wordArr.indices.reversed()) {
            sb.append(wordArr[i]).append(" ")
        }
        return sb.toString().trim { it <= ' ' }
    }

    fun isWhite(c: Char): Boolean {
        return Character.isWhitespace(c)
    }

    fun atoI(s: String): Int {
        var s = s
        var sum = 0
        val factor = 10
        var negative = false
        if (s.startsWith("-")) {
            negative = true
            s = s.substring(1)
        }
        for (c in s.toCharArray()) {
            sum *= factor
            val i = convert(c) // i = c - '0';
            sum += i
        }
        return if (negative) sum * -1 else sum
    }

    fun convert(c: Char): Int {
        val i = c - '0'
        //if (Character.isDigit(c))
        if (i < 0 || i > 9) throw IllegalArgumentException(String.format("Unable to convert %d to int", c))
        return i
    }

    fun permute(str: String) {
        permute("", str)
    }

    private fun permute(prefix: String, str: String) {
        val n = str.length
        if (n == 0) {
            LOGGER.info(prefix)
        } else {
            for (i in 0 until n) {
                permute(prefix + str[i], noti(str, i))
            }
        }
    }

    private fun noti(str: String, i: Int): String {
        return str.substring(0, i) +
                str.substring(i + 1, str.length)
    }

    //    bool IsCircular(Node* head)
//    {
//        if (head == NULL || head->next == NULL)
//            return false;
//
//        Node* pSlow = head;
//        Node* pFast = head->next;
//
//        while (pFast != NULL && pFast->next != NULL)
//        {
//            if (pFast == pSlow || pFast->next == pSlow)
//                return true;
//            pFast = pFast->next->next;
//            pSlow = pSlow->next;
//        }
//
//        return false;
//    }
    fun isCircular(list: Node?): Boolean {
        if (list == null || list.next == null) {
            return false
        }
        var slow = list
        var fast = list.next
        while (fast != null && fast.next != null) {
            slow = slow!!.next
            fast = fast.next!!.next
            if (fast === slow) {
                return true
            }
        }
        return false
    }

    fun breakIntoSpaces(source: String): String {
        for (i in 0 until source.length) {
            val left = source.substring(0, i)
            val right = source.substring(i, source.length)
            if (hs.contains(left) && hs.contains(right)) {
                return "$left $right"
            }
        }
        return source
    }

    fun isAnagram(str1: String, str2: String): Boolean { // check for nulls...
        if (str1.length != str2.length) {
            return false
        }
        val str1Chars: MutableMap<Char, Int?> = TreeMap()
        val str2Chars: MutableMap<Char, Int?> = TreeMap()
        for (i in 0 until str1.length) {
            if (str1Chars.containsKey(str1[i])) {
                str1Chars[str1[i]] = str1Chars[str1[i]]!! + 1
            } else {
                str1Chars[str1[i]] = 1
            }
            if (str2Chars.containsKey(str2[i])) {
                str2Chars[str2[i]] = str2Chars[str2[i]]!! + 1
            } else {
                str2Chars[str2[i]] = 1
            }
        }
        for (character in str1Chars.keys) {
            if (!str2Chars.containsKey(character) || str1Chars[character] != str2Chars[character]) {
                return false
            }
        }
        return true
    }

    fun isAnagramByArray(str1: String, str2: String): Boolean { // INCOMPLETE -- create a lookup table and try using a boolean set with a char as the index?
        if (str1.length != str2.length) {
            return false
        }
        val letters = IntArray(26)
        for (ch in str1.toCharArray()) {
            letters[Character.toLowerCase(ch).toInt()]++
        }
        val isAnagram = true
        for (ch in str2.toCharArray()) {
        }
        // INCOMPLETE!!!!!
        return false
    }

    fun isAnagramBySorting(str1: String, str2: String): Boolean {
        return if (str1.length != str2.length) {
            false
        } else isAnagram(str1.toCharArray(), str2.toCharArray())
    }

    private fun isAnagram(s1: CharArray, s2: CharArray): Boolean {
        Arrays.sort(s1)
        Arrays.sort(s2)
        for (i in s1.indices) {
            if (s1[i] != s2[i]) {
                return false
            }
        }
        return true
    }

    fun isRotation(s1: String?, s2: String?): Boolean {
        return if (s1 == null || s2 == null || s1.length != s2.length) {
            false
        } else (s1 + s1).contains(s2)
    }

    fun removeDupsFromList(node: Node?) {
        var node = node
        val unique: MutableSet<Node> = HashSet()
        var previous = node
        while (node != null) {
            if (unique.contains(node)) { //remove from list -- set the previous node's next pointer to this next pointer
                previous!!.next = node.next
            } else {
                unique.add(node)
                previous = node
            }
            node = node.next
        }
    }

    fun removeDupsFromListWithoutSet(head: Node?) {
        if (head == null) {
            return
        }
        var previous = head
        var current = head.next // need to compare first 2 nodes, not node to itself
        while (current != null) {
            var runner = head
            while (runner !== current) { // run through items to look for dup
                if (runner!!.data == current!!.data) {
                    val tmp = current.next // remove current, original item wins
                    previous!!.next = tmp
                    current = tmp
                    // optimization -- break here since previous dups already removed
                    break
                }
                runner = runner.next
            }
            if (runner === current) { // shouldn't this always be the case?
                previous = current
                current = current!!.next
            }
        }
    }

    fun nthToLast(head: Node?, n: Int): Node? {
        if (head == null || n < 0) {
            return null
        }
        var nthToLast = head
        var last = head
        for (i in 0 until n) { // skip first n nodes;
            if (last == null) {
                return null
            }
            last = last.next
        }
        if (last == null) {
            return null
        }
        while (last!!.next != null) { // increment both
            nthToLast = nthToLast!!.next
            last = last.next
        }
        return nthToLast
    }

    fun nthToLastRecursive(head: Node?, n: Int): Node? {
        val pos = 0
        return nthToLastRecursive(head, n, intArrayOf(pos))
    }

    private fun nthToLastRecursive(head: Node?, n: Int, pos: IntArray): Node? {
        if (head == null || n < 1) {
            return head
        }
        var target = nthToLastRecursive(head.next, n, pos)
        pos[0]++
        if (pos[0] == n) {
            target = head
        }
        return target
    }

    /*
Deque https://www.hackerrank.com/challenges/java-dequeue/problem
read M arrays of N integers each and count maximum number of unique integers among all contiguous subarrays

given starting code:
        Scanner in = new Scanner(System.in);
    Deque deque = new ArrayDeque<>();
    int n = in.nextInt();
    int m = in.nextInt();

    for (int i = 0; i < n; i++) {
        int num = in.nextInt();

    }

*/
    fun dequeMain() {
        val `in` = Scanner(System.`in`)
        val deque: Deque<Int> = ArrayDeque()
        val n = `in`.nextInt()
        val m = `in`.nextInt()
        val set: MutableSet<Int> = HashSet(m)
        var count = 0
        for (i in 0 until n) {
            val num = `in`.nextInt()
            deque.add(num)
            set.add(num)
            if (deque.size == m) {
                if (set.size > count) {
                    count = set.size
                }
                val head = deque.remove()
                if (!deque.contains(head)) {
                    set.remove(head)
                }
            }
        }
        println(count)
    }

    init {
        hs.add("peanut")
        hs.add("butter")
    }

    internal class Student(val id: Int, val fname: String, val cgpa: Double)

    internal object JavaSortSolution {
        val STUDENT_COMPARATOR = Comparator<Student> { o1, o2 ->
            if (java.lang.Double.compare(o1.cgpa, o2.cgpa) == 0) {
                return@Comparator if (o1.fname == o2.fname) { // return ID in ascending order
                    o1.id - o2.id
                } else {
                    o1.fname.compareTo(o2.fname)
                }
            }
            // descending order
            o2.cgpa.compareTo(o1.cgpa)
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val `in` = Scanner(System.`in`)
            var testCases = `in`.nextLine().toInt()
            val studentList: MutableList<Student> = ArrayList()
            while (testCases > 0) {
                val id = `in`.nextInt()
                val fname = `in`.next()
                val cgpa = `in`.nextDouble()
                val st = Student(id, fname, cgpa)
                studentList.add(st)
                testCases--
            }
            studentList.sortWith(STUDENT_COMPARATOR)
            for (st in studentList) {
                println(st.fname)
            }
        }
    }

    internal object Multiply {
        fun multiply(a: Double, b: Double): Double {
            return a * b
        }
    }

    internal class MyKVComparator : Comparator<Map.Entry<Int, Int>> {
        override fun compare(lhs: Map.Entry<Int, Int>, rhs: Map.Entry<Int, Int>): Int {
            return if (lhs.value == rhs.value) {
                lhs.key.compareTo(rhs.key)
            } else lhs.value.compareTo(rhs.value)
        }
    }

    // Memoization implementation
    private val cache: MutableMap<Int, Int?> = HashMap()

    fun minStepsToOneRecursive(n: Int): Int {
        return traverseRecursive(n)
    }

    private fun traverseRecursive(current: Int): Int {
        if (cache.containsKey(current)) {
            return cache[current]!!
        }
        if (current == 1) {
            return 0
        }
        var option = traverseRecursive(current - 1)
        if (current % 3 == 0) {
            val candidate = traverseRecursive(current / 3)
            option = Math.min(option, candidate)
        }
        if (current % 2 == 0) {
            val candidate = traverseRecursive(current / 2)
            option = Math.min(option, candidate)
        }
        cache[current] = option + 1
        return option + 1
    }

    fun minStepsToOneTabulation(n: Int): Int {
        val result = IntArray(n + 1)
        result[1] = 0
        for (i in 2 until result.size) {
            var option = result[i - 1]
            if (i % 3 == 0) {
                val candidate = result[i / 3]
                option = Math.min(option, candidate)
            }
            if (i % 2 == 0) {
                val candidate = result[i / 2]
                option = Math.min(option, candidate)
            }
            result[i] = option + 1
        }
        return result[n]
    }

    fun longestCommonSubsequenceRecursive(str1: String, str2: String): Int {
        return lcsRecurse(str1, str2, str1.length, str2.length)
    }

    private fun lcsRecurse(str1: String, str2: String, m: Int, n: Int): Int {
        if (m == 0 || n == 0) {
            return 0
        }
        return if (str1[m - 1] == str2[n - 1]) {
            1 + lcsRecurse(str1, str2, m - 1, n - 1)
        } else {
            Math.max(lcsRecurse(str1, str2, m, n - 1), lcsRecurse(str1, str2, m - 1, n))
        }
    }

    // Tabulated solution (vs. Memoization)
    fun longestCommonSubDynamic(str1: String, str2: String): Int {
        val m = str1.length
        val n = str2.length
        val dp = Array(m + 1) { IntArray(n + 1) }
        for (i in 0..m) {
            for (j in 0..n) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0
                } else if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1])
                }
            }
        }
        return dp[m][n]
    }

    // GeeksForGeeks -- convert n to m
/*
        Given two integers N and M and the task is to convert N to M with the following operations:

        Multiply N by 2 i.e. N = N * 2.
        Subtract 1 from N i.e. N = N – 1.
     */
    fun convertNtoM(n: Int, m: Int): Int {
        if (n == m) {
            return 0
        }
        val dp = IntArray(10000)
        Arrays.fill(dp, -1)
        return convertNtoM(dp, n, m)
    }

    private fun convertNtoM(dp: IntArray, n: Int, m: Int): Int {
        if (n <= 0 || n >= dp.size) {
            return 100000
        }
        if (n == m) {
            return 0
        }
        if (dp[n] != -1) {
            return dp[n]
        }
        dp[n] = Int.MAX_VALUE
        dp[n] = 1 + Math.min(convertNtoM(dp, n * 2, m), convertNtoM(dp, n - 1, m))
        return dp[n]
    }

    var dp = IntArray(1000)
    fun maximumSumToLeaf(arr: IntArray, v: Array<Vector<Int>>): Int {
        dfs(arr, v, 1, 0)
        return dp[1]
    }

    private fun dfs(arr: IntArray, vector: Array<Vector<Int>>, n: Int, parent: Int) {
        dp[n] = arr[n - 1]
        var max = 0
        for (child in vector[n]) {
            if (child == parent) {
                continue
            }
            dfs(arr, vector, child, n)
            max = Math.max(max, dp[child])
        }
        dp[n] += max
    }

    internal object MyRegex {
        // copied from HackerRank github:
// https://github.com/yanz67/HackerRank/blob/master/Java/Strings/JavaRegex.java
        var pattern = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$"
    }

    //    static Point2D parsePoint2D(String str) {
//        String[] xy = str.split(",");
//        return new Point2D(Double.parseDouble(xy[0]), Double.parseDouble(xy[1]));
//    }
    internal class Point(x: Int, y: Int) {
        private val x: Double
        private val y: Double
        fun distanceFrom(p: Point): Float {
            return Math.sqrt(Math.pow(x - p.x, 2.0) + Math.pow(y - p.y, 2.0)).toFloat()
        }

        fun distance(p: Point): Double {
            return Math.sqrt(Math.pow(x - p.x, 2.0) + Math.pow(y - p.y, 2.0))
        }

        init {
            this.x = x.toDouble()
            this.y = y.toDouble()
        }
    }

    /*


    // write your code here
    PrintWriter out = new PrintWriter(System.out);
    out.print("Name");
    out.print("Value");
    Properties properties = System.getProperties();
    Set<Object> keys = properties.keySet();
    for (Object key : keys) {
        out.print("<tr>");
        out.print(String.format("<td>%s</td><td>%s</td>", key.toString(), properties.getProperty(key.toString())));
        out.println("</tr>");
    }

    List<Book> booksList = new ArrayList<Book>();
    for (String[] book : books) {
        booksList.add(new Book(book));
    }

    for (Book book : booksList) {
        out.println(book);
    }

    NumberFormat format = new DecimalFormat("00");


 */
    internal class Book(strs: Array<String>) {
        var title: String
        var author: String
        var genre: String
        var price: Double
        var description: String

        init {
            title = strs[0]
            author = strs[1]
            genre = strs[2]
            price = strs[3].toDouble()
            description = strs[4]
        }
    }

    fun countPairsSummingToN(a: IntArray, b: IntArray, n: Int): Int {
        Arrays.sort(a)
        Arrays.sort(b)
        var result = 0
        val aMap: MutableMap<Int, Int?> = TreeMap()
        val bMap: MutableMap<Int, Int?> = TreeMap()
        for (i in a) {
            if (!aMap.containsKey(i)) {
                aMap[i] = 1
            } else {
                aMap[i] = aMap[i]!! + 1
            }
        }
        for (i in b) {
            if (!bMap.containsKey(i)) {
                bMap[i] = 1
            } else {
                bMap[i] = bMap[i]!! + 1
            }
        }
        for (i in aMap.keys) {
            if (bMap.containsKey(n - i)) {
                result += aMap[i]!! * bMap[n - i]!!
            }
        }
        return result
    }

    fun reverseWords0(input: String): String {
        val words = input.split(" ".toRegex()).toTypedArray()
        val wordStack = Stack<String>()
        for (str in words) {
            wordStack.push(str)
        }
        val stringBuilder = StringBuilder(input.length)
        while (!wordStack.isEmpty()) {
            stringBuilder.append(wordStack.pop()).append(" ")
        }
        stringBuilder.deleteCharAt(stringBuilder.length - 1)
        return stringBuilder.toString()
    }

    fun reverseWords2(s: String?): String? {
        var s = s
        if (s == null || s.length <= 1) {
            return s
        }
        val sb = StringBuilder()
        while (s != null && s.length > 0) { // go backwards in the input string until you've isolated a word, then append that substring to the builder
            var end = s.length - 1
            var beg = end
            while (beg >= 0 && !Character.isWhitespace(s[beg])) {
                beg--
            }
            sb.append(s.substring(beg + 1, end + 1))
            end = beg
            if (beg < 0) {
                break
            }
            while (beg >= 0 && Character.isWhitespace(s[beg])) {
                beg--
            }
            sb.append(s.substring(beg + 1, end + 1))
            end = beg
            if (beg < 0) {
                break
            }
            s = s.substring(0, end + 1)
        }
        return sb.toString()
    }

    fun reverseWords3(s: String?): String? {
        if (s == null || s.length <= 1) {
            return s
        }
        val sb = StringBuilder()
        val stack = Stack<String>()
        val strs = s.split(" ".toRegex()).toTypedArray()
        for (str in strs) {
            if (str.length > 0) {
                stack.push(str)
            }
        }
        while (!stack.empty()) {
            sb.append(stack.pop()).append(" ")
        }
        sb.deleteCharAt(sb.length - 1)
        return sb.toString()
    }

    fun reverseString(s: String): String {
        val reversed = s.toCharArray()
        reverseString(reversed)
        return String(reversed)
    }

    fun reverseString(input: CharArray) {
        var b = 0
        var e = input.size - 1
        while (b < e) {
            val temp = input[b]
            input[b] = input[e]
            input[e] = temp
            b++
            e--
        }
    }

    fun Permute(s: String) {
        Permute("", s)
    }

    private fun Permute(prefix: String, s: String) {
        if (s.length == 0) {
            LOG.info(prefix)
        }
        for (i in 0 until s.length) {
            Permute(prefix + s[i], noti(i, s))
        }
    }

    fun deleteDups(chars: CharArray): Int {
        Arrays.sort(chars)
        var dest = 0
        for (c in chars) {
            if (c != chars[dest]) {
                dest++
                chars[dest] = c
            }
        }
        return dest + 1
    }

    private fun noti(i: Int, s: String): String {
        val stringBuilder = StringBuilder(s)
        stringBuilder.deleteCharAt(i)
        return stringBuilder.toString()
    }

    private fun isWhiteSpace(c: Char): Boolean {
        return Character.isWhitespace(c)
    }

    /// iterative
    fun findFactorial(i: Int): Long {
        var i = i
        var fact: Long = 1
        while (i > 1) {
            fact *= i.toLong()
            i--
        }
        return fact
    }

    fun findFactorialRecursive(i: Int): Long {
        val fact: Long = 1
        return if (i == 1) {
            fact
        } else i * findFactorialRecursive(i - 1)
    }

    fun findCommonAncestor(root: TreeNode?, x: Int, y: Int): TreeNode? {
        return findCommonAncestor(null, root, x, y)
    }

    fun findCommonAncestor(parent: TreeNode?, node: TreeNode?, x: Int, y: Int): TreeNode? {
        if (node == null) {
            return null
        }
        if (node.data == x || node.data == y) {
            return parent
        }
        if (node.data > x && node.data < y) {
            return node
        }
        return if (node.data > x) {
            findCommonAncestor(node, node.left, x, y)
        } else findCommonAncestor(node, node.right, x, y)
    }

    fun includes(root: TreeNode?, data: Int): Boolean {
        if (root == null) return false
        return if (root.data == data) true else includes(root.left, data) || includes(root.right, data)
    }

    fun createBalancedTree(arr: IntArray): TreeNode? {
        return load(arr, 0, arr.size - 1)
    }

    private fun load(arr: IntArray, beginIndex: Int, endIndex: Int): TreeNode? {
        Arrays.sort(arr)
        if (endIndex < beginIndex) {
            return null
        }
        val mid = (beginIndex + endIndex) / 2
        val treeNode = TreeNode(arr[mid])
        treeNode.left = load(arr, beginIndex, mid - 1)
        treeNode.right = load(arr, mid + 1, endIndex)
        return treeNode
    }

    internal interface Food {
        val type: String?
    }

    internal inner class Pizza : Food {
        override val type: String?
            get() = "Someone ordered Fast Food!"
    }

    internal inner class Cake : Food {
        override val type: String?
            get() = "Someone ordered a Dessert!"
    }

    internal inner class FoodFactory {
        fun getFood(order: String): Food? {
            return when (order.toLowerCase()) {
                "cake" -> Cake()
                "pizza" -> Pizza()
                else -> null
            }
        }
    }

    /*
    Remove duplicates from input string and return the unique chars in input order
 */
    fun removeDupsOrdered(input: String): String {
        val sb = StringBuilder()
        val unique: MutableSet<Char> = HashSet()
        for (ch in input.toCharArray()) {
            unique.add(ch)
        }
        for (ch in input.toCharArray()) {
            if (unique.contains(ch)) {
                sb.append(ch)
                unique.remove(ch)
            }
        }
        return sb.toString()
    }

    fun countSetBits(inval: Int): Int {
        var inval = inval
        var count = 0
        while (inval > 0) {
            if (inval % 2 == 1) {
                count++
            }
            inval = inval shr 1
        }
        return count
    }

    fun countSetBitsHandleNegative(i: Int): Int {
        var i = i
        if (i < 0) {
            return countSetBitsNegative(0)
        }
        var count = 0
        while (i > 0) {
            if (i % 2 == 1) {
                count++
            }
            i = i ushr 1 //unsigned shift operator -- shifts a zero into the left
        }
        return count
    }

    fun countSetBitsNegative(neg: Int): Int {
        var neg = neg
        var count = 0
        for (i in 0..31) {
            if (neg and 1 == 1) {
                count++
            }
            neg = neg shr 1
        }
        return count
    }

    fun traverseTreeInOrder(root: Node?) {
        if (root!!.left != null) {
            traverseTreeInOrder(root.left)
        }
        LOGGER.info("" + root.data)
        if (root.right != null) {
            traverseTreeInOrder(root.right)
        }
    }


    fun fibonacci(i: Int): Int {
        return when (i) {
            1 -> {
                0
            }
            2 -> {
                1
            }
            else -> fibonacci(i - 1) + fibonacci(i - 2)
        }
    }

    private fun fibonacciTailRecursive(n: Int, accumulator: Int): Int {
        return if (n == 0) {
            accumulator
        } else fibonacciTailRecursive(n - 1, n * accumulator)
    }

    fun fibonacciTailRecursive(n: Int): Int {
        return fibonacciTailRecursive(n, 1)
    }

    fun fibonacciIteratively(i: Int): Int {
        if (i == 1) {
            return 0
        }
        if (i == 2) {
            return 1
        }
        var fib0 = 0
        var fib1 = 1
        var fib = fib0 + fib1
        for (j in 3..i) {
            fib = fib0 + fib1
            fib0 = fib1
            fib1 = fib
        }
        return fib
    }

    fun fiboTabulated(n: Int) : Int {
        val fibs = IntArray(n + 1)

        fibs[0] = 0
        fibs[1] = 1

        for (i in 2..n) {
            fibs[i] = fibs[i - 1] + fibs[i - 2]
        }

        return fibs[n]
    }

    fun serialize(root: Node?): List<Int> {
        val serialized: MutableList<Int> = ArrayList()
        val nodes: Queue<Node?> = ArrayDeque()
        nodes.add(root)
        while (!nodes.isEmpty()) {
            val curr = nodes.poll() ?: continue
            serialized.add(curr.data)
            if (curr.left != null) {
                nodes.add(curr.left)
            } else {
                serialized.add(-1)
            }
            if (curr.right != null) {
                nodes.add(curr.right)
            } else {
                serialized.add(-1)
            }
        }
        return serialized
    }

    var depthCount = 0
    fun breadthFirstTraversal2(root: Node?) {
        if (root == null) {
            return
        }
        val queue: Queue<Node?> = ArrayDeque()
        queue.add(root)
        depthCount++
        while (!queue.isEmpty()) {
            val curr = queue.poll()
                    ?: continue // poll differs from remove in that it will return null for empty queue, remove will throw
            depthCount++
            curr.print()
            queue.add(curr.left)
            queue.add(curr.right)
        }
    }

    fun breadthFirstTraversal(root: Node?) {
        val queue: Queue<Node?> = LinkedList()
        if (root == null) {
            return
        }
        queue.add(root)
        while (!queue.isEmpty()) {
            val node = queue.remove() // remove will throw ex when queue is empty
            LOGGER.info("" + node!!.data)
            if (node.left != null) {
                queue.add(node.left)
            }
            if (node.right != null) {
                queue.add(node.right)
            }
        }
    }

    fun revArr(chars: CharArray) { //        int j = chars.length - 1;
//        for (int i = 0; i < chars.length / 2; i++) {
//            char ch = chars[i];
//            chars[i] = chars[j];
//            chars[j] = ch;
//            j--;
//        }
        revArr(chars, 0, chars.size - 1)
    }

    fun revArr(arr: CharArray, start: Int, end: Int) {
        var start = start
        var end = end
        while (start < end) {
            val ch = arr[start]
            arr[start] = arr[end]
            arr[end] = ch
            start++
            end--
        }
    }

    fun reverseList(curr: Node?) {
        var curr: Node? = curr ?: return
        var prev: Node? = null
        var next: Node?
        while (curr!!.next != null) {
            next = curr.next
            curr.next = prev
            prev = curr
            curr = next
        }
        curr.next = prev
    }

    /**
     * Determines if input integer is a power of 2
     *
     * @param i
     * @return true iff i is a power of 2
     */
    fun isPowerOfTwo(i: Int): Boolean { // if more than 1 bit is set, return will be false
        return i and i - 1 == 0
    }

    /**
     * This implementation casts the hr angle to an int before subtracting from minute angle,
     * so may have inconsistent results from performing all calculations with doubles and casting at the end.
     *
     * @param hr  the number of the hour of the day
     * @param min number of minutes past the hour
     * @return int representing the acute angle between the hour and minute hands of an analog clock
     */
    fun getAcuteAngle(hr: Int, min: Int): Int {
        var result = 0.0
        val minuteHandAngle = 6 * min.toDouble()
        val hrHandAngle = .5 * (60 * hr + min)
        result = Math.abs(hrHandAngle - minuteHandAngle)
        if (result > 180) {
            result = 360 - result
        }
        return result.toInt()
    }

    class Node {
        var data = 0
        var strData: String? = null
        var left: Node? = null
        var right: Node? = null
        var next: Node? = null

        constructor() {}

        fun print() {
            LOGGER.info(String.format("{%d}", data))
        }

        constructor(data: Int) {
            this.data = data
        }

        constructor(strData: String?) {
            this.strData = strData
        }

        override fun equals(o: Any?): Boolean {
            if (this === o) return true
            if (o == null || javaClass != o.javaClass) return false
            val node = o as Node
            if (data != node.data) return false
            if (if (left != null) left != node.left else node.left != null) return false
            if (if (right != null) right != node.right else node.right != null) return false
            return if (next != null) next == node.next else node.next == null
        }

        override fun hashCode(): Int {
            var result = data
            result = 31 * result + if (left != null) left!!.hashCode() else 0
            result = 31 * result + if (right != null) right!!.hashCode() else 0
            result = 31 * result + if (next != null) next!!.hashCode() else 0
            return result
        }
    }

    class TreeNode(var data: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun maximizeProduct(arr: IntArray): Int {
        var maxArea = 0;

        var left = 0
        var right = arr.size - 1
        while (left < right) {
            val limiter = Math.min(arr[left], arr[right])
            maxArea = Math.max(maxArea, (right - left) * limiter)
            if (arr[left] < arr[right]) {
                left++
            } else {
                right--
            }
        }

        return maxArea
    }

    fun findDistinct(a: IntArray, b: IntArray): List<Int> {
        return a.filter {
            !b.contains(it)
        }
    }

    fun minimizeCoinCount(coins: IntArray, change: Int) : Int {
        val candidates = IntArray(change + 1)

        // initialize table:
        candidates.fill(Integer.MAX_VALUE) // can use change + 1
        // change of 0 is always 0
        candidates[0] = 0

        for (i in 1..change) {
            coins.forEach {
                if (it <= i) { // if the coin is less than the change amount
                    candidates[i] = Math.min(candidates[i], 1 + candidates[i - it])
                }
            }
        }

        return candidates[change]
    }

    fun minimizeStickCost(sticks: IntArray): Int {
        var accumulator = 0;

        val minHeap = PriorityQueue<Int>()
        minHeap.addAll(sticks.toList())

        while (minHeap.size > 1) {
            val sum = minHeap.remove() + minHeap.remove()
            accumulator += sum
            minHeap.add(sum)
        }

        return accumulator
    }
}

