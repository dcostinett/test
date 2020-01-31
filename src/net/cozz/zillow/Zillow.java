package net.cozz.zillow;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

// Question :
// Given someone's calendar for a day as a series of blocked intervals [8,11], [12, 14], [10, 15], [16, 18], [17, 20]
// Write a function that takes in the calendar and a duration ‘d' (say 45min) as input, and returns all
// available intervals in that day that satisfy the duration. (Assume hours range from 0 - 24)
// Output: [0-8], [15-16], [20-24]

public class Zillow {
    static List<Integer> timeslots = new ArrayList(24);
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */

        int[] available = new int[24];

        List<ImmutablePair<Integer, Integer>> inputSet = new ArrayList();

        inputSet.add(new ImmutablePair<>(8,11));
        inputSet.add(new ImmutablePair(12,14));
        inputSet.add(new ImmutablePair(10,15));
        inputSet.add(new ImmutablePair(16,18));
        inputSet.add(new ImmutablePair(17,20));

        System.out.println(findAvailable(inputSet));
    }

    public static List<Pair<Integer, Integer>> findAvailable(List<ImmutablePair<Integer, Integer>> list) {

        for (Pair<Integer, Integer> p : list) {
            int start = p.getLeft();
            int end = p.getRight();

            for (int i = start; i <= end; i++) {
                timeslots.remove(i);
            }
        }

        List<Pair<Integer, Integer>> available = new ArrayList<>();

        int start = timeslots.get(0);
        int previous = start;
        int count = 0;
        for (int hour = 1; hour <= timeslots.size(); hour++) {
            while (timeslots.get(hour) == previous + 1) {
                count++;
                previous = hour;
            }

            if (count > 0) {
                addPair(start, count, available);
            }
        }

        return available;
    }

    public static void addPair(int start, int count, List<Pair<Integer, Integer>> list) {
        list.add(new ImmutablePair<>(start, start + count));
    }
}
