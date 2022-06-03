import java.util.Scanner;

/**
 * Get the column heading corresponding to the position provided for an MSExcel document
 */
public class ExcelColumns {

    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();
        if (n <= 0) throw new IllegalArgumentException("Please provide input > 0");
        System.out.println(output(n - 1));
    }

    static String output(int input) {
        if (input < 0) return "";
        char letter = (char)('a' + input % 26);
        return output(input/26 - 1) + letter;
    }
}
