package net.cozz.flexe;

import java.util.*;

public class FlexeTest {

    public static int solution(int N, String S) {
        int available =0;

        // looks like a map where we have 3 possible places we can place 3 people side-by-side in each row

        if (S.trim().length() <= 0) {
            return N * 3;
        }

        String[] seats = S.toUpperCase().split(" ");
        Arrays.sort(seats);

        // doesn't work, ignores edge cases
//        Set<String> set = new HashSet<>();
//        for (int i = 1; i <= N; i++) {
//            set.add("" + i + "0");
//            set.add("" + i + "1");
//            set.add("" + i + "2");
//        }
//
//        for (String seat : seats) {
//            int column = mapSeat(seat);
//            String key = "" + seat.charAt(0) + column;
//            set.remove(key);
//        }

        // working solution - brute force, not efficient:
//        List<String> taken = Arrays.asList(seats);
//        for (int i = 1; i <= N; i++) {
//            if (taken.contains(i + "A") ||
//                    taken.contains(i + "B") ||
//                    taken.contains(i + "C")) {
//                available--;
//            }
//            if ((taken.contains(i + "D") && taken.contains(i + "G")) ||
//                    (taken.contains(i + "E") || taken.contains(i + "F"))) {
//                available--;
//            }
//            if (taken.contains(i + "H") ||
//                    taken.contains(i + "J") ||
//                    taken.contains(i + "K")) {
//                available--;
//            }
//        }

        // need a list of sets
        List<String> taken = Arrays.asList(seats);
        Map<Integer, Set<Character>> chart = new HashMap<>();
        for (String seat : taken) {
            Integer key = Integer.parseInt(Character.toString(seat.charAt(0)));
            Character column = seat.charAt(1);
            if (chart.containsKey(key)) {
                chart.get(key).add(column);
            } else {
                Set<Character> set = new HashSet<>();
                set.add(column);
                chart.put(key, set);
            }
        }

        for (Integer key : chart.keySet()) {
            if (chart.containsKey(key)) {
                Set<Character> set = chart.get(key);
                if (!set.contains('A') && !set.contains('B') && !set.contains('C')) {
                    available++;
                }
                if (!set.contains('H') && !set.contains('J') && !set.contains('K')) {
                    available++;
                }
                if (!(set.contains('D') && set.contains('G')) &&
                        !(set.contains('E') || set.contains('F'))) {
                    available++;
                }
            } else {
                available += 3;
            }
        }

        return available;
    }

    private static boolean isEdge(char ch) {
        return ch == 'D' || ch == 'G';
    }

    private static boolean consumesColumn(char ch) {
        switch (ch) {
            case 'A' :
            case 'B' :
            case 'C' :
            case 'H' :
            case 'J' :
            case 'K' :
                return true;

            case 'E' :
            case 'F' :
                return true;

            default:
                return false;
        }
    }



    private static int mapSeat(String s) {
        String upper = s.toUpperCase();
        switch (upper.charAt(1)) {
            case 'A' :
            case 'B' :
            case 'C' :
                return 0;

            case 'E' :
            case 'F' :
                return 1;

            case 'H' :
            case 'J' :
            case 'K' :
                return 2;

            case 'D':
            case 'G':
                return 1; // D & G are edge cases where the column only gets removed if both are taken in the same row

            default:
                return -1;
        }
    }



    public static int practice(int[] A) {
        Arrays.sort(A);

        int candidate = A[A.length - 1] + 1;
        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] <= 0) {
                candidate = 1;
                break;
            }
            if (i == 0) {
                if (A[i] > 1) {
                    candidate = A[i] - 1;
                    break;
                }
            }
            if (A[i - 1] != A[i] - 1) {
                candidate = A[i] - 1;
                break;
            }
        }

        return candidate;
    }
}
