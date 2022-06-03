import java.util.Arrays;
import java.util.Scanner;

/**
 * Sample input:
 * 10
 * 5
 * 2 4 3 1 9
 * 3 2 7 1 3
 *
 * Input weight and value array
 * weight = []
 * value = []
 *
 * weight of knapsack = w
 *
 * find maximum value possible
 */
public class KnapsackZeroOne {

    private static int[][] t;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int w = scan.nextInt();
        int n = scan.nextInt();
        int[] weight = new int[n];
        int[] value = new int[n];
        for (int i = 0; i < n; i++) {
            weight[i] = scan.nextInt();
        }
        for (int i = 0; i < n; i++) {
            value[i] = scan.nextInt();
        }
        t = new int[w + 1][n + 1];
        for (int[] x: t)
            Arrays.fill(x, -1);

        System.out.println(knapsackRecursiveMemoized(weight, value, w, n));
        System.out.println(knapsackTable(weight, value, w, n));
    }

    private static int knapsackRecursiveMemoized(int[] weight, int[] value, int w, int n) {
        if (t[w][n] != -1) return t[w][n];
        if (n == 0 || w == 0) {
            t[w][n] = 0;
            return 0;
        }
        if (weight[n - 1] <= w) {
            t[w][n] = Math.max(
                    value[n - 1] + knapsackRecursiveMemoized(weight, value, w - weight[n-1], n - 1),
                    knapsackRecursiveMemoized(weight, value, w, n - 1)
            );
        } else {
            t[w][n] = knapsackRecursiveMemoized(weight, value, w, n - 1);
        }
        return t[w][n];
    }

    private static int knapsackTable(int[] weight, int[] value, int w, int n) {
        int[][] dp = new int[w + 1][n + 1];
        for (int i = 0; i < n; i++) {
            dp[0][i] = 0;
            dp[i][0] = 0;
        }
        for (int i = 1; i <= w; i++) {
            for (int j = 1; j <= n; j++) {
                if (weight[j - 1] <= i) {
                    dp[i][j] = Math.max(value[j - 1] + dp[i - weight[j - 1]][j - 1], dp[i][j-1]);
                } else {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        return dp[w][n];
    }
}
