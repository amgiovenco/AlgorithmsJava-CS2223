package HW3;
import java.util.Scanner;

public class easyinversioncount {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of your array: ");
        int n;
        String input = scanner.nextLine();
        n = Integer.parseInt(input);

        int[] array = new int[n];
        System.out.println("Enter " + n + " numbers:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        scanner.close();

        int inversions = countInversions(array);
        System.out.println("Number of inversions: " + inversions);
    }

    private static int countInversions(int[] array) {
        int inversions = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    inversions++;
                }
            }
        }
        return inversions;
    }
}

