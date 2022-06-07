package unboundedknapsack;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Rod of length l, can be cut into pieces given of sizes l[] which has
 * price price[]. Find max profit
 *
 * Input
 8 8
 1 2 3 4 5 6 7 8
 1 5 8 9 10 17 17 20
 */
public class RodCuttingProblem {

    static int[][] t;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int size = scanner.nextInt();
        int[] l = new int[size];
        int[] price = new int[size];

        for (int i = 0; i < size; i++)
            l[i] = scanner.nextInt();

        for (int i = 0; i < size; i++)
            price[i] = scanner.nextInt();

        t = new int[length + 1][size + 1];
        for (int[] x: t)
            Arrays.fill(x, -1);
        System.out.println(getMaximumProfit(l, price, length, size));
        System.out.println(getMaximumProfitDP(l, price, length));
    }

    static int getMaximumProfit(int[] l, int[] price, int length, int size) {
        if (t[length][size] != -1) return t[length][size];
        if (length == 0 || size == 0) {
            t[length][size] = 0;
            return 0;
        }
        if (l[size - 1] <= length) {
            t[length][size] = Math.max(price[size - 1] + getMaximumProfit(l, price, length - l[size - 1], size),
                    getMaximumProfit(l, price, length, size - 1));
        } else {
            t[length][size] = getMaximumProfit(l, price, length, size - 1);
        }
        return t[length][size];
    }

    static int getMaximumProfitDP(int[] l, int[] price, int length) {
        int[][] dp = new int[length + 1][l.length + 1];
        for (int i = 0; i <= length; i++)
            dp[i][0] = 0;
        for (int i = 0; i <= l.length; i++)
            dp[0][i] = 0;

        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= l.length; j++) {
                if (l[j - 1] <= i) {
                    dp[i][j] = Math.max(price[j - 1] + dp[i - l[j - 1]][j], dp[i][j - 1]);
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[length][l.length];
    }
}
