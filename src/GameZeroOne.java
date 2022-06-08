import java.util.Arrays;
import java.util.Scanner;

public class GameZeroOne {

    static int[][] t;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 6;
        int start = 0;
        t = new int[n + 1][2];

        for (int[] x: t)
            Arrays.fill(x, -1);

        System.out.println(gameDP(n, start));
    }

    static int game(int n, int start) {
        if (t[n][start] != -1) return t[n][start];
        if (n == 0) {
            t[n][start] = start ^ 1;
            return start ^ 1;
        }
        int sqrt = (int)Math.floor(Math.sqrt(n));
        for (int i = 1; i <= sqrt; i++) {
            int res = game(n - i * i, start ^ 1);
            if (res == start)  {
                t[n][start] = start;
                return start;
            }
        }
        t[n][start] = start ^ 1;
        return start ^ 1;
    }

    static int gameDP(int n, int start) {
        int[][] dp = new int[n + 1][2];
        for (int[] x : dp)
            Arrays.fill(x, -1);


        for (int i = 0; i < 2; i++) {
            dp[0][i] = i ^ 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                int sqrt = (int)Math.sqrt(i);
                for (int k = 1; k <= sqrt; k++) {
                    int res = dp[i - k * k][j ^ 1];
                    if (res == j) {
                        dp[i][j] = j;
                        break;
                    }
                }
                if (dp[i][j] == -1)  {
                    dp[i][j] = j ^ 1;
                }
            }
        }
        return dp[n][start];
    }
}
