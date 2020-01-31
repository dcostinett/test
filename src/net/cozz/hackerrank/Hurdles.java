package net.cozz.hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hurdles {

    public void minimumEnhancement() {
        Scanner scanner = new Scanner(System.in);

        int count = 0;
        int height = 0;

        count = scanner.nextInt();
        height = scanner.nextInt();

        List<Integer> hurdles = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            hurdles.add(scanner.nextInt());
        }

        int max = Integer.MIN_VALUE;

        for (Integer integer : hurdles) {
            if (integer - height > max) {
                max = integer - height;
            }
        }

        System.out.println(max);
    }

    static int hurdleRace(int k, int[] height) {
        // Complete this function
        int max = 0;
        for (int i : height) {
            if (i - k > max && i - k > 0) {
                max = i - k;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] height = new int[n];
        for(int height_i = 0; height_i < n; height_i++){
            height[height_i] = in.nextInt();
        }
        int result = hurdleRace(k, height);
        System.out.println(result);
        in.close();
    }

}
