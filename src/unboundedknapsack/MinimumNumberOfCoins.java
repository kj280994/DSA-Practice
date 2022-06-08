package unboundedknapsack;

import java.util.Scanner;

public class MinimumNumberOfCoins {

    static int[][] t;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int change = scanner.nextInt();
        int[] coins = new int[size];
        for (int i = 0; i < size; i++)
            coins[i] = scanner.nextInt();

        t = new int[change + 1][coins.length + 1];
        System.out.println(minimumNumberOfCoins(coins, change, size));
    }

    static int minimumNumberOfCoins(int[] coins, int change, int coinsSize) {
        if (change == 0) {
            t[change][coinsSize] = 0;
            return 0;
        }
        if (coinsSize == 0) {
            t[change][coinsSize] = Integer.MAX_VALUE - 1;
            return Integer.MAX_VALUE - 1;
        }
        if (coins[coinsSize - 1] <= change) {
            t[change][coinsSize] = Math.min(1 + minimumNumberOfCoins(coins, change - coins[coinsSize - 1], coinsSize),
                    minimumNumberOfCoins(coins, change, coinsSize - 1));
        } else {
            t[change][coinsSize] = minimumNumberOfCoins(coins, change, coinsSize - 1);
        }
        return t[change][coinsSize];
    }
}
