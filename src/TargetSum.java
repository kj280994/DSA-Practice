import java.util.Scanner;

/**
 * No of combinations of +/- to array elements to get a target sum
 * 4 1
 * 1 1 2 3
 *
 * Output:
 * 3
 * {+1 +1 +2 -3}
 * {-1 +1 -2 +3}
 * {+1 -1 -2 +3}
 *
 * Simplification:
 * Array can be divided in two parts: One all +, one all -
 * so we have to add subtract them to get the target
 * this is the same problem as counting subsets with difference k.
 */
public class TargetSum {
    public static void main(String[] args) {
        SubsetsWithGivenDifference.main(args);
    }
}
