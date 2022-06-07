package unboundedknapsack;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Max no of ways to get change of the required change amount
 * Input:
 3
 5
 1 2 3
 */
public class CoinChangeMaxNoOfWays {

    static int[][] t;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int change = scanner.nextInt();
        int[] coins = new int[size];
        for (int i = 0; i < size; i++)
            coins[i] = scanner.nextInt();
        t = new int[change + 1][size + 1];
        for (int[] x: t)
            Arrays.fill(x, -1);
        System.out.println(getMaximumNoOfWays(coins, change, size));
        System.out.println(getMaximumNumberOfWaysDP(coins, change, size));
    }

    static int getMaximumNoOfWays(int[] coins, int change, int coinsSize) {
        if (t[change][coinsSize] != -1) return t[change][coinsSize];
        if (change == 0) {
            t[change][coinsSize] = 1;
            return 1;
        }
        if (coinsSize == 0) {
            t[change][coinsSize] = 0;
            return 0;
        }

        if (coins[coinsSize - 1] <= change) {
            t[change][coinsSize] =  getMaximumNoOfWays(coins, change - coins[coinsSize - 1], coinsSize) +
                    getMaximumNoOfWays(coins, change, coinsSize - 1);
        } else {
            t[change][coinsSize] = getMaximumNoOfWays(coins, change, coinsSize - 1);
        }
        return t[change][coinsSize];
    }

    static int getMaximumNumberOfWaysDP(int[] coins, int change, int coinsSize) {
        int[][] dp = new int[change + 1][coinsSize + 1];
        for (int i = 0; i <= change; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= coinsSize; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i <= change; i++) {
            for (int j = 1; j <= coinsSize; j++) {
                if (coins[j - 1] <= i) {
                    dp[i][j] = dp[i - coins[j - 1]][j] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[change][coinsSize];
    }
}
