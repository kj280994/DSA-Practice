import java.util.Scanner;

/**
 * Input:
 * 5
 * 1 9 4 7 2
 *
 * Two subsets possible s.t. minimum difference 1
 * {9, 2} = 11
 * {7, 4, 1} = 12
 */
public class MinimumSubsetSumDifference {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] n = new int[size];
        for (int i = 0; i < size; i++) {
            n[i] = scanner.nextInt();
        }

        System.out.println(minimumSubsetSumDifference(n));
    }

    static int minimumSubsetSumDifference(int[] n) {
        int sum = getSum(n);
        boolean[] possibleSubsetSumInRange = getPossibleSubsetSum(n, sum);
        int minimumDifference = Integer.MAX_VALUE;
        for (int i = 0; i <= sum / 2; i++) {
            if (!possibleSubsetSumInRange[i]) continue;
            minimumDifference = Math.min(minimumDifference, sum - 2 * i);
        }
        return minimumDifference;
    }

    static int getSum(int[] n) {
        int sum = 0;
        for (int x : n)
            sum += x;
        return sum;
    }

    static boolean[] getPossibleSubsetSum(int[] n, int sum) {
        boolean[][] subsetSum = new boolean[n.length + 1][sum + 1];
        for (int i = 0; i <= sum; i++) {
            subsetSum[0][i] = false;
        }
        for (int i = 0; i <= n.length; i++) {
            subsetSum[i][0] = true;
        }
        for (int i = 1; i <= n.length; i++) {
            for (int j = 1; j <= sum; j++) {
                if (n[i - 1] <= j) {
                    subsetSum[i][j] = subsetSum[i - 1][j - n[i - 1]] || subsetSum[i - 1][j];
                } else {
                    subsetSum[i][j] = subsetSum[i - 1][j];
                }
            }
        }
        return subsetSum[n.length];
    }
}
