package zerooneknapsack;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Find a subset in array n[] with sum k
 * Input:
 * 5
 * 11
 * 2 2 7 8 10
 */
public class SubsetSum {

    static int[][] t;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        int k = scan.nextInt();
        int[] n = new int[size];
        for (int i = 0; i < size; i++)
            n[i] = scan.nextInt();
        t = new int[k + 1][size + 1];
        for (int[] x: t)
            Arrays.fill(x, -1);
        System.out.println(subsetSumRecursiveMemoized(n, k, size));
        System.out.println(subsetSumDynamic(n, k));
    }

    static boolean subsetSumRecursiveMemoized(int[] n, int k, int size) {
        if (t[k][size] != -1) return t[k][size] == 1;
        if (k == 0) {
            t[k][size] = 1;
            return true;
        }
        if (size == 0) {
            t[k][size] = 0;
            return false;
        }
        boolean flag;
        if (n[size - 1] <= k) {
            flag = subsetSumRecursiveMemoized(n, k - n[size - 1], size - 1) ||
                    subsetSumRecursiveMemoized(n, k, size - 1);
        } else {
            flag = subsetSumRecursiveMemoized(n, k, size - 1);
        }
        t[k][size] = flag ? 1 : 0;
        return flag;
    }

    static boolean subsetSumDynamic(int[] n, int k) {
        boolean[][] dp = new boolean[k + 1][n.length + 1];
        for (int i = 0; i <= k; i++) {
            dp[i][0] = false;
        }
        for (int i = 0; i <= n.length; i++) {
            dp[0][i] = true;
        }
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n.length; j++) {
                if (n[j - 1] <= i) {
                    dp[i][j] = dp[i - n[j - 1]][j - 1] || dp[i][j - 1];
                }
                else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[k][n.length];
    }
}
