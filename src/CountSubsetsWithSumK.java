import java.util.Arrays;
import java.util.Scanner;

/**
 * Input:
 * 5
 * 10
 * 2 4 6 8 10
 *
 * No of subset count: 3
 * 2, 8
 * 4, 6
 * 10
 */
public class CountSubsetsWithSumK {

    static int[][] t;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int k = scanner.nextInt();
        int[] n = new int[size];
        for (int i = 0; i < size; i++) {
            n[i] = scanner.nextInt();
        }

        t = new int[k + 1][size + 1];
        for (int[] x: t)
            Arrays.fill(x, -1);
        System.out.println(subsetCountRecursiveMemoized(n, k, size));
        System.out.println(subsetCountDynamic(n, k));
    }

    static int subsetCountRecursiveMemoized(int[] n, int k, int size) {
        if (t[k][size] != -1) return t[k][size];
        if (k == 0 && size == 0) {
            t[k][size] = 1;
            return 1;
        }
        if (size == 0) {
            t[k][size] = 0;
            return 0;
        }
        if (n[size - 1] <= k) {
            t[k][size] = subsetCountRecursiveMemoized(n, k - n[size - 1], size - 1) +
                    subsetCountRecursiveMemoized(n, k, size - 1);
        } else {
            t[k][size] = subsetCountRecursiveMemoized(n, k, size - 1);
        }
        return t[k][size];
    }

    static int subsetCountDynamic(int[] n, int k) {
        int[][] dp = new int[k + 1][n.length + 1];
        for (int i = 0; i <= k; i++) {
            dp[i][0] = 0;
        }
        dp[0][0] = 1;
        for (int i = 1; i <= n.length; i++) {
            for (int j = 0; j <= k; j++) {
                if (n[i - 1] <= j) {
                    dp[j][i] = dp[j - n[i - 1]][i - 1] + dp[j][i - 1];
                } else {
                    dp[j][i] = dp[j][i - 1];
                }
            }
        }
        return dp[k][n.length];
    }
}
