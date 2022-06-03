import java.util.Scanner;

/**
 * Input:
 * 6
 * 2
 * -7 10 -1 8 -3 5
 *
 * n = 6 (size of array)
 * k = 2 (sum required)
 */
public class SmallestSubArrayWithSumK {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = scanner.nextInt();

        int[][] sum = new int[n][n];
        for (int i = 0; i < n; i++) {
            sum[i][i] = a[i];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n && j - i + 1 < min; j++) {
                sum[i][j] = sum[i][j - 1] + a[j];
                if (sum[i][j] == k) {
                    if (j - i + 1 < min)
                        min = j - i + 1;
                    break;
                }
            }
            if (min == 1) break;
        }
        System.out.println(min);
    }
}
