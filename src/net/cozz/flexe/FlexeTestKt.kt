package net.cozz.flexe

import java.util.*

class FlexeTestKt {
    fun solution(N: Int, S: String): Int {
        var available = 0
        // looks like a map where we have 3 possible places we can place 3 people side-by-side in each row
        if (S.trim { it <= ' ' }.length <= 0) {
            return N * 3
        }
        val seats = S.toUpperCase().split(" ".toRegex()).toTypedArray()
        Arrays.sort(seats)
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
        val taken = Arrays.asList(*seats) // * is the spread operator
        val chart: MutableMap<Int, MutableSet<Char>> = HashMap()
        for (seat in taken) {
            val key = Character.toString(seat[0]).toInt()
            val column = seat[1]
            if (chart.containsKey(key)) {
                chart[key]!!.add(column)
            } else {
                val set: MutableSet<Char> = HashSet()
                set.add(column)
                chart[key] = set
            }
        }
        for (key in chart.keys) {
            if (chart.containsKey(key)) {
                val set = chart[key]
                if (!set!!.contains('A') && !set.contains('B') && !set.contains('C')) {
                    available++
                }
                if (!set.contains('H') && !set.contains('J') && !set.contains('K')) {
                    available++
                }
                if (!(set.contains('D') && set.contains('G')) &&
                        !(set.contains('E') || set.contains('F'))) {
                    available++
                }
            } else {
                available += 3
            }
        }
        return available
    }

    private fun isEdge(ch: Char): Boolean {
        return ch == 'D' || ch == 'G'
    }

    private fun consumesColumn(ch: Char): Boolean {
        return when (ch) {
            'A', 'B', 'C', 'H', 'J', 'K' -> true
            'E', 'F' -> true
            else -> false
        }
    }


    private fun mapSeat(s: String): Int {
        val upper = s.toUpperCase()
        return when (upper[1]) {
            'A', 'B', 'C' -> 0
            'E', 'F' -> 1
            'H', 'J', 'K' -> 2
            'D', 'G' -> 1 // D & G are edge cases where the column only gets removed if both are taken in the same row
            else -> -1
        }
    }


    fun practice(A: IntArray): Int {
        Arrays.sort(A)
        var candidate = A[A.size - 1] + 1
        for (i in A.indices.reversed()) {
            if (A[i] <= 0) {
                candidate = 1
                break
            }
            if (i == 0) {
                if (A[i] > 1) {
                    candidate = A[i] - 1
                    break
                }
            }
            if (A[i - 1] != A[i] - 1) {
                candidate = A[i] - 1
                break
            }
        }
        return candidate
    }

}