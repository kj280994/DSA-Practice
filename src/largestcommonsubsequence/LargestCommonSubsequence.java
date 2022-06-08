package largestcommonsubsequence;

import java.util.Arrays;
import java.util.Scanner;

class LargestCommonSubsequence {

    static int[][] t;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s1 = scan.nextLine();
        String s2 = scan.nextLine();

        t = new int[s1.length() + 1][s2.length() + 1];

        for (int[] x : t)
            Arrays.fill(x, -1);

        System.out.println(getLargestCommonSubsequenceLength(s1, s2, s1.length(), s2.length()));
        System.out.println(getLargestCommonSubsequenceLengthDP(s1, s2));
    }

    private static int getLargestCommonSubsequenceLength(String s1, String s2, int s1Length, int s2Length) {
        if (t[s1Length][s2Length] != -1)
            return t[s1Length][s2Length];

        if (s1Length == 0 || s2Length == 0) {
            t[s1Length][s2Length] = 0;
            return 0;
        }

        if (s1.charAt(s1Length - 1) == s2.charAt(s2Length - 1)) {
            t[s1Length][s2Length] =
                    1 + getLargestCommonSubsequenceLength(s1, s2, s1Length - 1, s2Length - 1);
        } else {
            t[s1Length][s2Length] = Math.max(
                    getLargestCommonSubsequenceLength(s1, s2, s1Length, s2Length - 1),
                    getLargestCommonSubsequenceLength(s1, s2, s1Length - 1, s2Length)
            );
        }
        return t[s1Length][s2Length];
    }

    private static int getLargestCommonSubsequenceLengthDP(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++)
            dp[i][0] = 0;

        for (int i = 0; i <= s2.length(); i++)
            dp[0][i] = 0;

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }

        return dp[s1.length()][s2.length()];
    }
}
