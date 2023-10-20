package net.cozz.hackerrank;

import java.util.Arrays;

public class CoinChange {
    public static void main(String[] args) {
        System.out.println(change(12, new int[] {1,2,5}));

        System.out.println(coinChange(new int[]{1,2,5}, 11));
    }

    // number of combinations for amount from coins
    public static int change(int amount, int[] coins) {
        int[] combos = new int[amount + 1];
        combos[0] = 1;

        for (int coin : coins) {
            for (int i = 1; i < combos.length; i++) {
                if (i >= coin) {
                    combos[i] += combos[i - coin];
                }
            }
        }

        return combos[amount];
    }

    public static void printAmount(int[] arr) {
        for (int coin: arr) {
            System.out.print(coin + " ");
        }
        System.out.println();
    }

    // fewest coins question
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];

        Arrays.sort(coins);
        Arrays.fill(dp, amount + 1);

        dp[0] = 0;
        for (int i = 0; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                } else {
                    break;
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

}
