package zerooneknapsack;

import java.util.Scanner;

/**
 * Input:
 * 5 1
 * 1 2 4 5 3
 *
 * Divide array into 2 subsets such that difference of their sum is k.
 */
public class SubsetsWithGivenDifference {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] n = new int[size];
        int k = scanner.nextInt();
        for (int i = 0; i < size; i++)
            n[i] = scanner.nextInt();

        /*
         * Problem Simplification:
         * k = S2 - S1
         * S2 = k + S1
         * S1 + S2 = S
         * 2*S1 + k = S
         * 2*S1 = S - k
         * so S - k should be even number
         *
         * S1 = S - k / 2
         * Find a subset with sum s1
         */

        int sum = getSum(n);
        if ((sum - k) % 2 != 0) System.out.println("0");
        else {
            System.out.println(CountSubsetsWithSumK.subsetCountDynamic(n, (sum - k)/2));
        }
    }

    private static int getSum(int[] n) {
        int sum = 0;
        for (int x : n)
            sum += x;
        return sum;
    }
}
