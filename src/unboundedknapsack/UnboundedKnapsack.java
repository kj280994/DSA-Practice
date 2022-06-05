package unboundedknapsack;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Given unlimited items of weights and value, find the maximum value which can be achieved
 * by adding value to knapsack of weight capacity w.
 *
 * Input:
 * 5
 * 14
 * 1 4 2 6 3
 * 3 2 4 1 5
 */
public class UnboundedKnapsack {

    static int[][] t;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        int w = scan.nextInt();
        int[] value = new int[size];
        int[] weight = new int[size];
        for (int i = 0; i < size; i++)
            weight[i] = scan.nextInt();

        for (int i = 0; i < size; i++)
            value[i] = scan.nextInt();

        t = new int[w + 1][size + 1];
        for (int[] x: t)
            Arrays.fill(x, -1);

        System.out.println(unboundedKnapsackRecursiveMemoized(weight, value, w, size));
        System.out.println(unboundedKnapsackDp(weight, value, w, size));
    }

    static int unboundedKnapsackRecursiveMemoized(int[] weight, int[] value, int w, int size) {
        if (t[w][size] != -1) return t[w][size];
        if (size == 0 || w == 0) {
            t[w][size] = 0;
            return 0;
        }
        if (weight[size - 1] <= w) {
            t[w][size] = Math.max(
                    value[size - 1] + unboundedKnapsackRecursiveMemoized(weight, value, w - weight[size - 1], size),
                    unboundedKnapsackRecursiveMemoized(weight, value, w, size - 1));
        } else {
            t[w][size] = unboundedKnapsackRecursiveMemoized(weight, value, w, size - 1);
        }
        return t[w][size];
    }

    static int unboundedKnapsackDp(int[] weight, int[] value, int w, int size) {
        int[][] dp = new int[w + 1][size + 1];
        for (int i = 0; i <= w; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= size; i++) {
            dp[0][i] = 0;
        }

        for (int i = 1; i <= w; i++) {
            for (int j = 1; j <= size; j++) {
                if (weight[j - 1] <= i) {
                    dp[i][j] = Math.max(
                            value[j - 1] + dp[i - weight[j - 1]][j],
                            dp[i][j - 1]
                    );
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[w][size];
    }
}
