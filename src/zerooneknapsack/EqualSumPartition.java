package zerooneknapsack;

import java.util.Scanner;

/**
 * Can array elements be partitioned in 2 halves such that both have equal sum?
 * Input:
 * 5
 * 0 2 3 4 5
 */
public class EqualSumPartition {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] n = new int[size];
        int sum = 0;
        for (int i = 0; i < size; i++) {
            n[i] = scanner.nextInt();
            sum += n[i];
        }
        if (sum % 2 != 0) {
            System.out.println(false);
        } else {
            System.out.println(SubsetSum.subsetSumDynamic(n, sum / 2));
        }
    }
}
