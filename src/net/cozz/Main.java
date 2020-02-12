package net.cozz;


import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import static junit.framework.Assert.assertTrue;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static final String SCHEDULE_DATE_FORMAT_FULL = "E, MMMM dd 'at' hh:mm aa";
    public static final DateFormat dateFormat = new SimpleDateFormat(SCHEDULE_DATE_FORMAT_FULL);

    private static final String INSTANCE_IDS_FILE_NAME = "test";
    private static final Path createdInstanceIdsStore =
            Paths.get(INSTANCE_IDS_FILE_NAME);

    static final Set<String> hs = new HashSet<>();

    static {
        hs.add("peanut");
        hs.add("butter");
    }

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    static String[][] books = {
            {"Moby Dick", "Herman Melville", "classic", "19.99", "Ishmael narrates the monomaniacal quest of Ahab, captain of the whaler Pequod, for revenge on the albino sperm whale Moby Dick"},
            {"Catcher In The Rye", "J. D. Salinger", "classic", "6.49", "The novel's protagonist Holden Caulfield has become an icon for teenage rebellion. The novel also deals with complex issues of identity, belonging, loss, connection, and alienation."},
            {"Ender's Game", "Orson Scott Card", "sci-fi", "8.20", "Ender's Game presents an imperiled mankind after two conflicts with the \"buggers\", an insectoid alien species. In preparation for an anticipated third invasion, children, including the novel's protagonist, Ender Wiggin, are trained from a very young age through increasingly difficult games including some in zero gravity, where Ender's tactical genius is revealed."},
            {"The Hobbit", "JRR Tolkien", "fantasy", "18.59", "The Hobbit follows the quest of home-loving hobbit Bilbo Baggins to win a share of the treasure guarded by the dragon, Smaug."}
    };

    // find smallest array of matching degree -- degree defined as most frequently occurring integer in int[]

    // find palindrome substring within a string

    // write code to translate input integer to "English" -- 4,321 to "Four thousand, three hundred twenty one"

    // write code to re-order a linked list, moving the last element to the 2nd element until there are only 2 elements

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int target = Integer.parseInt(br.readLine());

        Node root = null;
        if (target < 1) {
            System.out.println("NIL");
        } else {
            String data = br.readLine();
            StringTokenizer stringTokenizer = new StringTokenizer(data);
            List<String> numbers = new ArrayList<>();
            root = new Node(stringTokenizer.nextToken());
            Node node = root;
            while (stringTokenizer.hasMoreElements()) {
                node.next = new Node(stringTokenizer.nextToken());
                node = node.next;
            }

            Node targetNode = nthToLast(root, target - 1);
            if (targetNode == null) {
                System.out.println("NIL");
            } else {
                System.out.println(targetNode.strData.trim());
            }
        }

        if (true) System.exit(0);


        LOGGER.info(dateFormat.format(new Date()));

        passByRrefTest();

        // dequeMain();

        doFoodTest();

        arrayTests();

        Date forecastDate = new Date();
        DateFormat dayFormatter = new SimpleDateFormat("EEE K a", Locale.US);
        LOGGER.info(dayFormatter.format(forecastDate));
        DateFormat timeFormatter = new SimpleDateFormat("", Locale.US);


        String[] logFiles = new File(".")
                .list(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        LOGGER.info(dir.getAbsolutePath());
                        LOGGER.info("Name = " + name);
                        return name.startsWith("test");
                    }
                });

        List<String> sorted = Arrays.asList(logFiles);
        Collections.sort(sorted);

        NumberFormat numberFormat = new DecimalFormat("000");

        int num = new Integer(sorted.get(sorted.size() - 1)
                .substring(sorted.get(sorted.size() - 1).indexOf("_") + 1));
        Path outPath = Paths.get(String.format("%s_%s", INSTANCE_IDS_FILE_NAME,
                numberFormat.format(++num)));
        try {
            Files.copy(createdInstanceIdsStore, Files.newOutputStream(outPath));
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }


        LOGGER.info(sorted.get(sorted.size() - 1));

        LOGGER.info(removeDups(new char[]{'a', 'a', 'b', 'c', 'c'}));
        LOGGER.info(removeDups("TTEST"));

        LOGGER.info(reverseWords("this is a test"));
        LOGGER.info(revWords("this is a test"));

        LOGGER.info("" + (atoI("123") + 1));
    }


    static class Student {
        private int id;
        private String fname;
        private double cgpa;

        public Student(int id, String fname, double cgpa) {
            super();
            this.id = id;
            this.fname = fname;
            this.cgpa = cgpa;
        }

        public int getId() {
            return id;
        }

        public String getFname() {
            return fname;
        }

        public double getCgpa() {
            return cgpa;
        }
    }

    static String revS(String s) {
        if (s == null || s.length() == 1) {
            return s;
        }
        return revS(s.substring(1)) + s.charAt(0);
    }


    static class JavaSortSolution {
        static final Comparator<Student> STUDENT_COMPARATOR = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (Double.compare(o1.getCgpa(), o2.getCgpa()) == 0) {
                    if (o1.getFname().equals(o2.getFname())) {
                        // return ID in ascending order
                        return o1.getId() - o2.getId();
                    } else {
                        return o1.getFname().compareTo(o2.getFname());
                    }
                }

                // descending order
                return (Double.compare(o2.getCgpa(), o1.getCgpa()));
            }
        };


        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            int testCases = Integer.parseInt(in.nextLine());

            List<Student> studentList = new ArrayList<Student>();
            while (testCases > 0) {
                int id = in.nextInt();
                String fname = in.next();
                double cgpa = in.nextDouble();

                Student st = new Student(id, fname, cgpa);
                studentList.add(st);

                testCases--;
            }

            studentList.sort(STUDENT_COMPARATOR);

            for (Student st : studentList) {
                System.out.println(st.getFname());
            }
        }
    }

    private static void hackerRankJavaGenerics() {
        //printArray();
    }

    private static void printArray(Object[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    private static void hackerRankJavaHashset() {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        String[] pair_left = new String[t];
        String[] pair_right = new String[t];

        for (int i = 0; i < t; i++) {
            pair_left[i] = s.next();
            pair_right[i] = s.next();
        }

        Set<String> left_right = new LinkedHashSet<>(t);

        for (int i = 0; i < t; i++) {
            left_right.add(String.format("%s %s", pair_left[i], pair_right[i]));
            System.out.println(left_right.size());
        }
    }

    static Map<Character, Character> validBrackets = loadValidValues();

    public static void hackerRankJavaStack() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.next();
            System.out.println(processStrings(input));
        }
    }

    private static Map<Character, Character> loadValidValues() {
        Map<Character, Character> validValues = new HashMap<>(3);
        validValues.put('[', ']');
        validValues.put('{', '}');
        validValues.put('<', '>');
        validValues.put('(', ')');
        return validValues;
    }

    private static boolean processStrings(String input) {
        if (input.length() % 2 != 0) {
            return false;
        }

        final Stack<Character> chars = new Stack<>();
        for (Character ch : input.toCharArray()) {
            if (validBrackets.containsKey(ch)) {
                chars.push(ch);
            } else {
                if (validBrackets.containsValue(ch)) {
                    // if the stack is empty, or this isn't the closing version of the last item in the stack
                    if (chars.empty() || !validBrackets.get(chars.pop()).equals(ch)) {
                        return false;
                    }
                }
            }
        }

        // we unloaded the stack every time we got a closing bracket, if the stack isn't empty, then it's not balanced
        return chars.empty();
    }

    public static void hackerRankJavaMap() {
        Map<String, Long> pb = new TreeMap<>();

        Scanner sc = new Scanner(System.in);

        int pbSize = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < pbSize; i++) {
            String key = sc.nextLine();
            Long val = Long.parseLong(sc.nextLine());
            pb.put(key, val);
        }

        while (sc.hasNext()) {
            String query = sc.nextLine();
            if (pb.containsKey(query)) {
                System.out.println(String.format("%s=%s", query, pb.get(query)));
            } else {
                System.out.println("Not found");
            }
        }

        sc.close();
    }


    public static void hackerRankJavaList() {
        List<Integer> myList = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        int listSize = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < listSize; i++) {
            myList.add(sc.nextInt());
        }
        int queryCount = sc.nextInt();

        for (int i = 0; i < queryCount; i++) {
            String command = sc.next();
            sc.nextLine();
            switch (command.toLowerCase()) {
                case "insert":
                    int index = sc.nextInt();
                    int val = sc.nextInt();
                    sc.nextLine();
                    myList.add(index, val);
                    break;
                case "delete":
                    int delIndex = sc.nextInt();
                    myList.remove(delIndex);
                    if (i < queryCount - 1) {
                        sc.nextLine();
                    }
                    break;
            }
        }

        for (Integer integer : myList) {
            System.out.print(String.format("%d ", integer));
        }

        sc.close();
    }


    public static int solution(int[] A, int[] B, int M, int X, int Y) {
        int result = 0;

        long weight = 0;
        Set<Integer> floors = new TreeSet<>();
        Queue<Integer> theQueue = new LinkedList<>();
        for (int i = 0; i < A.length; i++) {
            theQueue.add(A[i]);
        }

        int personCount = 0;
        int floorIndex = 0;
        while (!theQueue.isEmpty()) {
            weight += theQueue.peek();
            if (weight < Y && personCount < X) {
                theQueue.poll();
                floors.add(B[floorIndex++]);
                personCount++;
            } else {
                // we're at the size or weight limit. send the elevator
                result += floors.size() + 1; // add an additional stop for the return trip
                floors.clear();
                weight = 0;
                personCount = 0;
            }

            if (theQueue.isEmpty()) {
                result += floors.size() + 1; // add an additional stop for the return trip
                floors.clear();
                weight = 0;
                personCount = 0;
            }
        }

        return result;
    }

    static class Multiply {

        public static Double multiply(Double a, Double b) {
            return a * b;
        }

    }


    public static int bestBuy01(int[] A) {
        int result = -1;

        for (int i = 0; i < A.length; i++) {
            if (isEquilibriumIndex(i, A)) {
                result = i;
                break;
            }
        }

        return result;
    }

    public static boolean isEquilibriumIndex(int i, int[] A) {
        int lowSum = 0;
        int highSum = 0;

        for (int j = 0; j < i; j++) {
            lowSum += A[j];
        }

        for (int j = A.length - 1; j > i; j--) {
            highSum += A[j];
        }

        return lowSum == highSum;
    }

    /*
    Kth Most frequent element.
int[] input = {1,2,3,4,4,2,4,5,5,2,2,2}
k=2;
2,4
     */

    public static int getKthMostFrequent(int[] input, int k) {
        HashMap<Integer, Integer> hist = new HashMap<>();

        for (Integer i : input) {
            if (hist.containsKey(i)) {
                hist.put(i, hist.get(i) + 1);
            } else {
                hist.put(i, 1);
            }
        }

        HashMap<Integer, Integer> sorted = sortByValues(hist);

        Set<Map.Entry<Integer, Integer>> keyset = sorted.entrySet();
        List<Map.Entry<Integer, Integer>> theList = new ArrayList<>();
        theList.addAll(keyset);

        int kthElement = theList.get(k).getKey();

        return kthElement;
    }

    public static void sortList(List<Integer> input) {
        Map<Integer, Integer> histogram = new TreeMap<>();

        for (Integer i : input) {
            if (!histogram.containsKey(i)) {
                histogram.put(i, 1);
            } else {
                histogram.put(i, histogram.get(i) + 1);
            }
        }

        Map<Integer, Integer> sorted = sortByValue(histogram);

        for (Map.Entry<Integer, Integer> p : sorted.entrySet()) {
            for (int i = 0; i < p.getValue(); i++) {
                System.out.println(String.valueOf(p.getKey()));
            }
        }
    }

    /**
     * Alternate sort by values implementation
     *
     * @param map
     * @return
     */
    private static HashMap<Integer, Integer> sortByValues(HashMap<Integer, Integer> map) {
        List<Integer> list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o2)).getValue())
                        .compareTo(((Map.Entry) (o1)).getValue());
            }
        });

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    /**
     * Sort map by values
     *
     * @param map
     * @return
     */
    public static Map<Integer, Integer> sortByValue(Map<Integer, Integer> map) {
        List<Map.Entry<Integer, Integer>> list = new LinkedList<>(map.entrySet());
        list.sort(new MyKVComparator());

        Map<Integer, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    static class MyKVComparator implements Comparator<Map.Entry<Integer, Integer>> {
        @Override
        public int compare(Map.Entry<Integer, Integer> lhs, Map.Entry<Integer, Integer> rhs) {
            if (lhs.getValue().equals(rhs.getValue())) {
                return lhs.getKey().compareTo(rhs.getKey());
            }
            return lhs.getValue().compareTo(rhs.getValue());
        }
    }

    class MyKeyValuePair implements Map.Entry<Integer, Integer>, Comparable<MyKeyValuePair> {
        private Integer key;
        private Integer value;

        @Override
        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        @Override
        public Integer getValue() {
            return value;
        }

        @Override
        public Integer setValue(Integer value) {
            this.value = value;
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MyKeyValuePair that = (MyKeyValuePair) o;

            if (!key.equals(that.key)) return false;
            return value.equals(that.value);

        }

        @Override
        public int hashCode() {
            int result = key.hashCode();
            result = 31 * result + value.hashCode();
            return result;
        }

        @Override
        public int compareTo(MyKeyValuePair other) {
            if (getValue().equals(other.getValue())) {
                return getKey().compareTo(other.getKey());
            }
            return getValue().compareTo(other.getValue());
        }
    }

    // Memoization implementation
    private Map<Integer, Integer> cache = new HashMap<>();
    public int minStepsToOneRecursive(int n) {
        return traverseRecursive(n);
    }

    private int traverseRecursive(int current) {
        if (cache.containsKey(current)) {
            return cache.get(current);
        }

        if (current == 1) {
            return 0;
        }

        int option = traverseRecursive(current - 1);

        if (current % 3 == 0) {
            int candidate = traverseRecursive(current / 3);
            option = Math.min(option, candidate);
        }

        if (current % 2 == 0) {
            int candidate = traverseRecursive(current / 2);
            option = Math.min(option, candidate);
        }

        cache.put(current, option + 1);
        return option + 1;
    }

    public int minStepsToOneTabulation(int n) {
        int[] result = new int[n + 1];
        result[1] = 0;

        for (int i = 2; i < result.length; i++) {
            int option = result[i - 1];

            if (i % 3 == 0) {
                int candidate = result[i / 3];
                option = Math.min(option, candidate);
            }

            if (i % 2 == 0) {
                int candidate = result[i / 2];
                option = Math.min(option, candidate);
            }

            result[i] = option + 1;
        }

        return result[n];
    }


    public int longestCommonSubsequenceRecursive(String str1, String str2) {
        return lcsRecurse(str1, str2, str1.length(), str2.length());
    }

    private int lcsRecurse(String str1, String str2, int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }

        if (str1.charAt(m - 1) == str2.charAt(n - 1)) {
            return 1 + lcsRecurse(str1, str2, m - 1, n - 1);
        } else {
            return Math.max(lcsRecurse(str1, str2, m, n - 1), lcsRecurse(str1, str2, m - 1, n));
        }
    }

    // Tabulated solution (vs. Memoization)
    public int longestCommonSubDynamic(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        int dp[][] = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    // GeeksForGeeks -- convert n to m
    /*
        Given two integers N and M and the task is to convert N to M with the following operations:

        Multiply N by 2 i.e. N = N * 2.
        Subtract 1 from N i.e. N = N – 1.
     */
    public int convertNtoM(int n, int m) {
        if (n == m) {
            return 0;
        }

        int[] dp = new int[10000];
        Arrays.fill(dp, -1);

        return convertNtoM(dp, n, m);
    }

    private int convertNtoM(int[] dp, int n, int m) {
        if (n <= 0 || n >= dp.length) {
            return 100000;
        }

        if (n == m) {
            return 0;
        }

        if (dp[n] != -1) {
            return dp[n];
        }
        dp[n] = Integer.MAX_VALUE;

        dp[n] = 1 + Math.min(convertNtoM(dp, n * 2, m), convertNtoM(dp, n - 1, m));

        return dp[n];
    }

    int[] dp = new int[1000];
    public int maximumSumToLeaf(int[] arr, Vector<Integer>[] v) {
        dfs(arr,  v, 1, 0);
        return dp[1];
    }

    private void dfs(int[] arr, Vector<Integer>[] vector, int n, int parent) {
        dp[n] = arr[n - 1];

        int max = 0;

        for (int child : vector[n]) {
            if (child == parent) {
                continue;
            }

            dfs(arr, vector, child, n);

            max = Math.max(max, dp[child]);
        }

        dp[n] += max;
    }

    static class MyRegex {
        // copied from HackerRank github:
        // https://github.com/yanz67/HackerRank/blob/master/Java/Strings/JavaRegex.java
        public static String pattern = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
    }

    public static void prac5() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {

            try {
                long x = sc.nextLong();
                System.out.println(x + " can be fitted in:");
                if (x >= -128 && x <= 127) System.out.println("* byte");
                //Complete the code
                if (x >= -Math.pow(2, 16) && x <= Math.pow(2, 16)) System.out.println("* short");

                if (x >= -Math.pow(2, 32) && x <= Math.pow(2, 32)) System.out.println("* int");

                if (x >= -Math.pow(2, 64) && x <= Math.pow(2, 64)) System.out.println("* long");

            } catch (Exception e) {
                System.out.println(sc.next() + " can't be fitted anywhere.");
            }

        }

    }

    public static void prac4() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int n = in.nextInt();
            for (int j = 0; j < n; j++)
                System.out.print(String.format("%1.0f ", a + (Math.pow(2, j + 1) - 1) * b));
            System.out.println();
        }
        in.close();
    }

    public static void prac3() {
        Scanner sc = new Scanner(System.in);

        int count = sc.nextInt();
        for (int i = 1; i <= 10; i++) {
            System.out.println(String.format("%d x %d = %d", count, i, count * i));
        }
    }

    public static void prac2() {
        Scanner sc = new Scanner(System.in);
        System.out.println("================================");
        for (int i = 0; i < 3; i++) {
            String s1 = sc.next();
            int x = sc.nextInt();
            //Complete this line
            System.out.print(String.format("%-15s%03d", s1, x));
            System.out.println();
        }
        System.out.println("================================");
    }

    public static int sum(int[] arr) {
        int sum = 0;
        for (int i = 1; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    // fail
    public static void practice1(int n) {
        for (int i = n; i > 0; i--) {
            for (int j = 0; j <= n - i; j++) {
                if (i < j - n)
                    System.out.print(" ");
                else
                    System.out.print("#");
            }
            System.out.println();
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
    public static void challenge2() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int pointCount = scanner.nextInt();
        List<Point> points = new ArrayList<>(pointCount);
        float shortestDistance = loadPoints(pointCount, scanner, points);

        NumberFormat nf = new DecimalFormat("#.0000");
        System.out.println(nf.format(shortestDistance));
    }

    private static float loadPoints(int pointCount, Scanner scanner, List<Point> points) {
        float candidate = Float.MAX_VALUE;
        for (int i = 0; i < pointCount; i++) {
            Point point = new Point(scanner.nextInt(), scanner.nextInt());

            for (Point p : points) {
                if (p.distanceFrom(point) < candidate) {
                    candidate = p.distanceFrom(point);
                }
            }

            points.add(point);
            scanner.nextLine();
        }
        int sentinel = scanner.nextInt();
        // assert sentinal == 0; // lots of ways to do this, there were 2 terminal conditions though... not sure which wins

        return candidate;
    }

//    static Point2D parsePoint2D(String str) {
//        String[] xy = str.split(",");
//        return new Point2D(Double.parseDouble(xy[0]), Double.parseDouble(xy[1]));
//    }

    static class Point {
        private double x;
        private double y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public float distanceFrom(Point p) {
            return (float) (Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2)));
        }

        public double distance(Point p) {
            return Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
        }
    }

    public static void challenge1(String[] args) throws IOException {

        Map<String, String> chains = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = reader.readLine()) != null) {
            parseMap(s, chains);
            String end = followChains("BEGIN", chains);
            if (end.equals("END") && chains.isEmpty()) {
                System.out.println("GOOD");
            } else {
                System.out.println("BAD");
            }
        }
    }

    public static void parseMap(String input, Map<String, String> chains) {
        String[] elements = input.split(";");
        for (String s : elements) {
            String[] chain = s.split("-");
            // assert chain.length == 2;
            // assert inRange(chain[1], 2, 10000
            chains.put(chain[0], chain[1]);
        }
    }

    public static String followChains(String begin, Map<String, String> chains) {
        // assert !chains.empty()
        String nextKey = chains.get(begin);
        if (nextKey == null || !chains.containsKey(nextKey)) {
            return "bad";
        }
        chains.remove(begin);
        while (!chains.isEmpty()) {
            nextKey = chains.remove(nextKey);
            if (nextKey == null) {
                return "bad";
            }
        }

        return nextKey;
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


    static class Book {
        String title;
        String author;
        String genre;
        double price;
        String description;

        Book(String[] strs) {
            this.title = strs[0];
            this.author = strs[1];
            this.genre = strs[2];
            this.price = Double.parseDouble(strs[3]);
            this.description = strs[4];
        }
    }

    public static boolean isPairSummingToN(int[] sortedArray, int target) {
        int li = 0;
        int hi = sortedArray.length - 1;

        boolean found = false;

        while (!found && li < hi) {
            if (sortedArray[li] + sortedArray[hi] == target) {
                found = true;
            }
            else if (sortedArray[li] + sortedArray[hi] < target) {
                li++;
            }
            else {
                hi--;
            }
        }

        return found;
    }

    public int countPairsSummingToN(int[] a, int[] b, int n) {
        Arrays.sort(a);
        Arrays.sort(b);

        int result = 0;

        Map<Integer, Integer> aMap = new TreeMap<>();
        Map<Integer, Integer> bMap = new TreeMap<>();
        for (int i : a) {
            if (!aMap.containsKey(i)) {
                aMap.put(i, 1);
            } else {
                aMap.put(i, aMap.get(i) + 1);
            }
        }

        for (int i : b) {
            if (!bMap.containsKey(i)) {
                bMap.put(i, 1);
            } else {
                bMap.put(i, bMap.get(i) + 1);
            }
        }

        for (int i : aMap.keySet()) {
            if (bMap.containsKey(n - i)) {
                result += (aMap.get(i) * bMap.get(n - i));
            }
        }

        return result;
    }

    public String reverseWords0(String input) {
        String[] words = input.split(" ");

        Stack<String> wordStack = new Stack<String>();

        for (String str : words) {
            wordStack.push(str);
        }

        StringBuilder stringBuilder = new StringBuilder(input.length());

        while (!wordStack.isEmpty()) {
            stringBuilder.append(wordStack.pop()).append(" ");
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }

    public static String reverseWords(String words) {
        StringBuilder sb = new StringBuilder(words.length());

        // start at the end
        int bw = words.length() - 1;

        while (words.length() > 0) {
            while (bw >= 0 && isWhite(words.charAt(bw))) {
                bw--;
            }
            if (bw < words.length() - 1) {
                // append the trailing whitespace
                sb.append(words.substring(bw + 1));
                words = words.substring(0, bw + 1);
            }

            while (bw >= 0 && !isWhite(words.charAt(bw))) {
                bw--;
            }
            // append the word
            sb.append(words.substring(bw + 1));
            words = words.substring(0, bw + 1);
        }

        return sb.toString();
    }

    public String reverseWords2(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        StringBuilder sb = new StringBuilder();

        while (s != null && s.length() > 0) {
            // go backwards in the input string until you've isolated a word, then append that substring to the builder
            int end = s.length() - 1;
            int beg = end;

            while (beg >= 0 && !Character.isWhitespace(s.charAt(beg))) {
                beg--;
            }
            sb.append(s.substring(beg + 1, end + 1));
            end = beg;

            if (beg < 0) {
                break;
            }

            while (beg >= 0 && Character.isWhitespace(s.charAt(beg))) {
                beg--;
            }
            sb.append(s.substring(beg + 1, end + 1));
            end = beg;

            if (beg < 0) {
                break;
            }

            s = s.substring(0, end + 1);
        }

        return sb.toString();
    }


    public String reverseWords3(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        StringBuilder sb = new StringBuilder();

        Stack<String> stack = new Stack<String>();
        String[] strs = s.split(" ");

        for (String str : strs) {
            if (str.length() > 0) {
                stack.push(str);
            }
        }

        while (!stack.empty()) {
            sb.append(stack.pop()).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }


    public String reverseString(String s) {
        char[] reversed = s.toCharArray();
        reverseString(reversed);

        return new String(reversed);
    }


    public void reverseString(char[] input) {
        int b = 0;
        int e = input.length - 1;

        while (b < e) {
            char temp = input[b];
            input[b] = input[e];
            input[e] = temp;
            b++;
            e--;
        }
    }


    public void Permute(String s) {
        Permute("", s);
    }


    private void Permute(String prefix, String s) {
        if (s.length() == 0) {
            LOG.info(prefix);
        }

        for (int i = 0; i < s.length(); i ++) {
            Permute(prefix + s.charAt(i), noti(i, s));
        }
    }


    public int deleteDups(char[] chars) {
        Arrays.sort(chars);
        int dest = 0;
        for (char c : chars) {
            if (c != chars[dest]) {
                dest++;
                chars[dest] = c;
            }
        }

        return dest +1;
    }


    private String noti(int i, String s) {
        StringBuilder stringBuilder = new StringBuilder(s);
        stringBuilder.deleteCharAt(i);
        return stringBuilder.toString();
    }


    private boolean isWhiteSpace(char c) {
        return Character.isWhitespace(c);
    }


    /// iterative
    public long findFactorial(int i) {
        long fact = 1;

        while (i > 1) {
            fact *= i;
            i--;
        }

        return fact;
    }


    public long findFactorialRecursive(int i) {
        long fact = 1;

        if (i == 1) {
            return fact;
        }

        return i * (findFactorialRecursive(i - 1));
    }

    public TreeNode findCommonAncestor(TreeNode root, int x, int y) {
        return findCommonAncestor(null, root, x, y);
    }


    public TreeNode findCommonAncestor(TreeNode parent, TreeNode node, int x, int y) {
        if (node == null) {
            return null;
        }

        if (node.data == x || node.data == y) {
            return parent;
        }

        if (node.data > x && node.data < y) {
            return node;
        }

        if (node.data > x) {
            return findCommonAncestor(node, node.left, x, y);
        }

        return findCommonAncestor(node, node.right, x, y);
    }


    public boolean includes(TreeNode root, int data) {
        if (root == null) return false;
        if (root.data == data) return true;

        return includes(root.left, data) || includes(root.right, data);
    }


    public TreeNode createBalancedTree(int[] arr) {
        return load(arr, 0, arr.length - 1);
    }


    private TreeNode load(int[] arr, int beginIndex, int endIndex) {
        Arrays.sort(arr);

        if (endIndex < beginIndex) {
            return null;
        }

        int mid = (beginIndex + endIndex) /2;

        TreeNode treeNode = new TreeNode(arr[mid]);
        treeNode.left = load(arr, beginIndex, mid - 1);
        treeNode.right = load(arr, mid + 1, endIndex);

        return treeNode;
    }


    static String getDay(String day, String month, String year) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(Calendar.YEAR, Integer.valueOf(year));
        cal.set(Calendar.MONTH, Integer.valueOf(month) - 1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(day));

        return new SimpleDateFormat("EEE").format(cal.getTime()).toUpperCase();
    }

    // A utility function to print a substring str[low..high]
    static void printSubStr(String str, int low, int high) {
        System.out.println(str.substring(low, high + 1));
    }

    // This function prints the longest palindrome substring
    // of str[].
    // It also returns the length of the longest palindrome
    static int longestPalSubstr(String str) {
        int n = str.length();   // get length of input string

        // table[i][j] will be false if substring str[i..j]
        // is not palindrome.
        // Else table[i][j] will be true
        boolean table[][] = new boolean[n][n];

        // All substrings of length 1 are palindromes
        int maxLength = 1;
        for (int i = 0; i < n; ++i) {
            table[i][i] = true;
        }

        // check for sub-string of length 2.
        int start = 0;
        for (int i = 0; i < n - 1; ++i) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                table[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }

        // Check for lengths greater than 2. k is length
        // of substring
        for (int k = 3; k <= n; ++k) {

            // Fix the starting index
            for (int i = 0; i < n - k + 1; ++i)
            {
                // Get the ending index of substring from
                // starting index i and length k
                int j = i + k - 1;

                // checking for sub-string from ith index to
                // jth index iff str.charAt(i+1) to
                // str.charAt(j-1) is a palindrome
                if (table[i + 1][j - 1] && str.charAt(i) ==
                        str.charAt(j)) {
                    table[i][j] = true;

                    if (k > maxLength) {
                        start = i;
                        maxLength = k;
                    }
                }
            }
        }
        System.out.print("Longest palindrome substring is; ");
        printSubStr(str, start, start + maxLength - 1);

        return maxLength; // return length of LPS
    }

    // https://www.programcreek.com/2013/12/leetcode-solution-of-longest-palindromic-substring-java/
    public static String longestPalindromeSubstr(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }

        int len = str.length();
        int maxLen = 1;
        boolean[][] matrix = new boolean[len][len];

        String candidate = null;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - i; j++) {
                int k = i + j;
                if (str.charAt(j) == str.charAt(k) && (k - j <= 2 || matrix[j + 1][k - 1])) {
                    matrix[j][k] = true;

                    if (maxLen < k - j + 1) {
                        maxLen = k - j + 1;
                        candidate = str.substring(j, k + 1);
                    }
                }
            }
        }

        return candidate;
    }

    // also https://www.programcreek.com/2013/12/leetcode-solution-of-longest-palindromic-substring-java/
    // a simple algorithm
    public static String longestPalindromeSubst(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        String candidate = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            // get longest  pal centered at index i
            String tmp = getPalindrome(s, i, i); // handles odd numbered strings (the entire string is a palindrome)
            if (tmp.length() > candidate.length()) {
                candidate = tmp;
            }

            //
            tmp = getPalindrome(s, i, i + 1); // handles even numbered strings
            if (tmp.length() > candidate.length()) {
                candidate = tmp;
            }
        }

        return candidate;
    }

    private static String getPalindrome(String s, int beginIdx, int endIdx) {
        while (beginIdx >= 0 && endIdx < s.length() && s.charAt(beginIdx) == s.charAt(endIdx)) {
            beginIdx--;
            endIdx++;
        }
        return s.substring(beginIdx + 1, endIdx);
    }

    interface Food {
        public String getType();
    }

    class Pizza implements Food {
        public String getType() {
            return "Someone ordered Fast Food!";
        }
    }

    class Cake implements Food {

        public String getType() {
            return "Someone ordered a Dessert!";
        }
    }

    class FoodFactory {
        public Food getFood(String order) {
            switch (order.toLowerCase()) {
                case "cake":
                    return new Cake();
                case "pizza":
                    return new Pizza();
                default:
                    return null;
            }
        }
    }

    private static void doFoodTest() {

    }

    public static String transform(String input) {
        final String[] toRemove = {"{b}", "{/b}"};
        final String[] replaceWith = {"<b>", "</b>"};

        return StringUtils.replaceEach(input, toRemove, replaceWith);
    }

    public static void arrayTests() {
        int[][][][] a = new int[5][5][6][10];
        int[][][] b = new int[5][][];

        LOGGER.fine("" + b[0][0].length); //NPE
    }

    public static void passByRrefTest() {
        Dog aDog = new Dog("Max");
        foo(aDog);

        if (aDog.getName().equals("Max")) { //true
            System.out.println("Java passes by value.");
        } else if (aDog.getName().equals("Fifi")) {
            // you can change the properties of aDog, but you can't change the memory locaiton it points to
            System.out.println("Java passes by reference.");
        }
    }

    public static void foo(Dog d) {
        d.getName().equals("Max"); // true

        d = new Dog("Fifi");
        assertTrue(d.getName().equals("Fifi")); // true
    }

    /*
        Remove duplicates from input string and return the unique chars in input order
     */
    public String removeDupsOrdered(String input) {
        StringBuilder sb = new StringBuilder();
        Set<Character> unique = new HashSet<Character>();

        for (char ch : input.toCharArray()) {
            unique.add(ch);
        }

        for (char ch : input.toCharArray()) {
            if (unique.contains(ch)) {
                sb.append(ch);
                unique.remove(ch);
            }
        }

        return sb.toString();
    }

    public static String removeDups(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        TreeSet<Character> uniqueChars = new TreeSet<Character>();
        for (Character ch : input.toCharArray()) {
            if (!uniqueChars.contains(ch)) {
                uniqueChars.add(ch);
                stringBuilder.append(ch);
            }
        }

        return stringBuilder.toString();
    }

    public static String removeDups(char[] chars) {
        Arrays.sort(chars);
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != chars[index]) {
                index++;
                chars[index] = chars[i];
            }
        }

        char[] result = Arrays.copyOf(chars, index + 1);
        return new String(result);
    }

    public int countSetBits(int inval) {
        int count = 0;

        while (inval > 0) {
            if (inval % 2 == 1) {
                count++;
            }

            inval >>= 1;
        }

        return count;
    }

    public int countSetBitsHandleNegative(int i) {
        if (i < 0) {
            return countSetBitsNegative(0);
        }
        int count = 0;
        while (i > 0) {
            if (i % 2 == 1) {
                count++;
            }
            i >>>= 1;   //unsigned shift operator -- shifts a zero into the left
        }

        return count;
    }

    public int countSetBitsNegative(int neg) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((neg & 1) == 1) {
                count++;
            }
            neg >>= 1;
        }

        return count;
    }

    public static void printBinaryRep(int i) {
        Stack s = new Stack();
        while (i > 0) {
            if (i % 2 > 0) {
                s.push(1);
            } else {
                s.push(0);
            }
            i = i >> 1;
        }
        StringBuilder sb = new StringBuilder(s.size());
        while (s.size() > 0) {
            sb.append(s.pop());
        }
        LOGGER.info(sb.toString());
    }

    public static String revWords(String words) {
        StringBuilder sb = new StringBuilder(words.length());

        String[] wordArr = words.split(" ");
        for (int i = wordArr.length - 1; i >= 0; i--) {
            sb.append(wordArr[i]).append(" ");
        }

        return sb.toString().trim();
    }

    public static boolean isWhite(char c) {
        return Character.isWhitespace(c);
    }

    public static int atoI(String s) {
        int sum = 0;
        int factor = 10;
        boolean negative = false;
        if (s.startsWith("-")) {
            negative = true;
            s = s.substring(1);
        }
        for (char c : s.toCharArray()) {
            sum *= factor;
            int i = convert(c);     // i = c - '0';
            sum += i;
        }

        return negative ? sum * -1 : sum;
    }

    public static int convert(char c) {
        int i = c - '0';

        //if (Character.isDigit(c))

        if (i < 0 || i > 9) throw new IllegalArgumentException(
                String.format("Unable to convert %d to int", c));
        return i;
    }

    public void traverseTreeInOrder(Node root) {
        if (root.left != null) {
            traverseTreeInOrder(root.left);
        }
        LOGGER.info("" + root.data);
        if (root.right != null) {
            traverseTreeInOrder(root.right);
        }
    }

    public int fibonacci(int i) {
        if (i <= 1) {
            return i;
        }

        return (fibonacci(i - 1) + fibonacci(i - 2));
    }

    private int fibonacciTailRecursive(int n, int accumulator) {
        if (n == 0) {
            return accumulator;
        }

        return fibonacciTailRecursive(n - 1, n * accumulator);
    }

    public int fibonacciTailRecursive(int n) {
        return fibonacciTailRecursive(n, 1);
    }

    public int fibonacciIteratively(int i) {
        if (i == 1) {
            return 0;
        }

        int fib0 = 0;
        int fib1 = 1;
        int fib = fib0 + fib1;
        for (int j = 3; j <= i; j++) {
            fib = fib0 + fib1;
            fib0 = fib1;
            fib1 = fib;
        }

        return fib;
    }

    public int fibonacciDynamicProgramming(int n) {
        int[] fibs = new int[n + 2]; // because we need to preload arr with at least 2 values

        fibs[0] = 0;
        fibs[1] = 1;

        for (int i = 2; i <= n; i++) {
            fibs[i] = fibs[i - 1] + fibs[i - 2];
        }

        return fibs[n];
    }

    public List<Integer> serialize(Node root) {
        List<Integer> serialized = new ArrayList<>();

        Queue<Node> nodes = new ArrayDeque<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            Node curr = nodes.poll();

            if (curr == null) {
                continue;
            }

            serialized.add(curr.data);

            if (curr.left != null) {
                nodes.add(curr.left);
            } else {
                serialized.add(-1);
            }

            if (curr.right != null) {
                nodes.add(curr.right);
            } else {
                serialized.add(-1);
            }
        }

        return serialized;
    }

    int depthCount = 0;
    public void breadthFirstTraversal2(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        depthCount++;

        while (!queue.isEmpty()) {
            Node curr = queue.poll(); // poll differs from remove in that it will return null for empty queue, remove will throw

            if (curr == null) {
                continue;
            }

            depthCount++;
            curr.print();

            queue.add(curr.left);
            queue.add(curr.right);
        }
    }

    public void breadthFirstTraversal(Node root) {
        Queue<Node> queue = new LinkedList<Node>();

        if (root == null) {
            return;
        }

        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.remove(); // remove will throw ex when queue is empty
            LOGGER.info("" + node.data);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public static void permute(String str) {
        permute("", str);
    }

    private static void permute(String prefix, String str) {
        int n = str.length();
        if (n == 0) {
            LOGGER.info(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                permute(prefix + str.charAt(i), noti(str, i));
            }
        }
    }

    private static String noti(String str, int i) {
        return str.substring(0, i) +
                str.substring(i + 1, str.length());
    }

    public void revArr(char[] chars) {
//        int j = chars.length - 1;
//        for (int i = 0; i < chars.length / 2; i++) {
//            char ch = chars[i];
//            chars[i] = chars[j];
//            chars[j] = ch;
//            j--;
//        }
        revArr(chars, 0, chars.length - 1);
    }

    public void revArr(char[] arr, int start, int end) {
        while (start < end) {
            char ch = arr[start];
            arr[start] = arr[end];
            arr[end] = ch;
            start++;
            end--;
        }
    }

    void reverseList(Node curr) {
        if (curr == null) return;

        Node prev = null;
        Node next;
        while (curr.next != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        curr.next = prev;
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

    public static boolean isCircular(Node list) {
        if (list == null || list.next == null) {
            return false;
        }

        Node slow = list;
        Node fast = list.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return true;
            }
        }

        return false;
    }

    /**
     * Determines if input integer is a power of 2
     *
     * @param i
     * @return true iff i is a power of 2
     */
    boolean isPowerOfTwo(int i) {
        // if more than 1 bit is set, return will be false
        return (i & (i - 1)) == 0;
    }

    /**
     * This implementation casts the hr angle to an int before subtracting from minute angle,
     * so may have inconsistent results from performing all calculations with doubles and casting at the end.
     *
     * @param hr  the number of the hour of the day
     * @param min number of minutes past the hour
     * @return int representing the acute angle between the hour and minute hands of an analog clock
     */
    public int getAcuteAngle(int hr, int min) {
        double result = 0;

        double minuteHandAngle = 6 * min;
        double hrHandAngle = (.5 * (60 * hr + min));

        result = Math.abs(hrHandAngle - minuteHandAngle);

        if (result > 180) {
            result = 360 - result;
        }

        return (int) result;
    }

    public static String breakIntoSpaces(final String source) {
        for (int i = 0; i < source.length(); i++) {
            String left = source.substring(0, i);
            String right = source.substring(i, source.length());
            if (hs.contains(left) && hs.contains(right)) {
                return left + " " + right;
            }
        }

        return source;
    }

    public static boolean isAnagram(String str1, String str2) {
        // check for nulls...
        if (str1.length() != str2.length()) {
            return false;
        }

        Map<Character, Integer> str1Chars = new TreeMap<>();
        Map<Character, Integer> str2Chars = new TreeMap<>();

        for (int i = 0; i < str1.length(); i++) {
            if (str1Chars.containsKey(str1.charAt(i))) {
                str1Chars.put(str1.charAt(i), str1Chars.get(str1.charAt(i)) + 1);
            } else {
                str1Chars.put(str1.charAt(i), 1);
            }
            if (str2Chars.containsKey(str2.charAt(i))) {
                str2Chars.put(str2.charAt(i), str2Chars.get(str2.charAt(i)) + 1);
            } else {
                str2Chars.put(str2.charAt(i), 1);
            }
        }

        for (Character character : str1Chars.keySet()) {
            if (!str2Chars.containsKey(character) || !str1Chars.get(character).equals(str2Chars.get(character))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isAnagramByArray(String str1, String str2) {
        // INCOMPLETE -- create a lookup table and try using a boolean set with a char as the index?
        if (str1.length() != str2.length()) {
            return false;
        }

        int[] letters = new int[26];

        for (char ch : str1.toCharArray()) {
            letters[Character.toLowerCase(ch)]++;
        }
        boolean isAnagram = true;
        for (char ch : str2.toCharArray()) {

        }

        // INCOMPLETE!!!!!
        return false;
    }

    public static boolean isAnagramBySorting(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }

        return isAnagram(str1.toCharArray(), str2.toCharArray());
    }

    private static boolean isAnagram(final char[] s1, final char[] s2) {
        Arrays.sort(s1);
        Arrays.sort(s2);

        for (int i = 0; i < s1.length; i++) {
            if (s1[i] != s2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isRotation(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }

        return (s1 + s1).contains(s2);
    }

    public static void removeDupsFromList(Node node) {
        Set<Node> unique = new HashSet<>();
        Node previous = node;
        while (node != null) {
            if (unique.contains(node)) {
                //remove from list -- set the previous node's next pointer to this next pointer
                previous.next = node.next;
            } else {
                unique.add(node);
                previous = node;
            }
            node = node.next;
        }
    }

    public static void removeDupsFromListWithoutSet(Node head) {
        if (head == null) {
            return;
        }

        Node previous = head;
        Node current = head.next; // need to compare first 2 nodes, not node to itself
        while (current != null) {
            Node runner = head;
            while (runner != current) {
                // run through items to look for dup
                if (runner.data == current.data) {
                    Node tmp = current.next; // remove current, original item wins
                    previous.next = tmp;
                    current = tmp;
                    // optimization -- break here since previous dups already removed
                    break;
                }
                runner = runner.next;
            }
            if (runner == current) { // shouldn't this always be the case?
                previous = current;
                current = current.next;
            }
        }
    }

    public static Node nthToLast(Node head, int n) {
        if (head == null || n < 0) {
            return null;
        }

        Node nthToLast = head;
        Node last = head;
        for (int i = 0; i < n; i++) {
            // skip first n nodes;
            if (last == null) {
                return null;
            }
            last = last.next;
        }
        if (last == null) {
            return null;
        }
        while (last.next != null) {
            // increment both
            nthToLast = nthToLast.next;
            last = last.next;
        }
        return nthToLast;
    }

    public static Node nthToLastRecursive(Node head, int n) {
        int pos = 0;
        return nthToLastRecursive(head, n, new int[] {pos});
    }

    private static Node nthToLastRecursive(Node head, int n, int[] pos) {
        if (head == null || n < 1) {
            return head;
        }
        Node target = nthToLastRecursive(head.next, n, pos);
        pos[0]++;
        if (pos[0] == n) {
            target = head;
        }
        return target;
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
    public static void dequeMain() {
        Scanner in = new Scanner(System.in);
        Deque<Integer> deque = new ArrayDeque<>();
        int n = in.nextInt();
        int m = in.nextInt();

        Set<Integer> set = new HashSet<>(m);
        int count = 0;
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            deque.add(num);
            set.add(num);

            if (deque.size() == m) {
                if (set.size() > count) {
                    count = set.size();
                }
                int head = deque.remove();
                if (!deque.contains(head)) {
                    set.remove(head);
                }
            }
        }

        System.out.println(count);
    }

    public static class Node {
        public int data;
        public String strData;
        public Node left;
        public Node right;
        public Node next;


        public Node() {
        }


        public void print() {
            LOGGER.info(String.format("{%d}", data));
        }

        public Node(int data) {
            this.data = data;
        }
        public Node(String strData) { this.strData = strData; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (data != node.data) return false;
            if (left != null ? !left.equals(node.left) : node.left != null) return false;
            if (right != null ? !right.equals(node.right) : node.right != null) return false;
            return next != null ? next.equals(node.next) : node.next == null;

        }

        @Override
        public int hashCode() {
            int result = data;
            result = 31 * result + (left != null ? left.hashCode() : 0);
            result = 31 * result + (right != null ? right.hashCode() : 0);
            result = 31 * result + (next != null ? next.hashCode() : 0);
            return result;
        }
    }


    private static final class TreeNode{

        public int data;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int data) {
            this.data = data;
        }
    }
}
