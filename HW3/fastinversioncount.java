package HW3;
import java.util.Scanner;

public class fastinversioncount {
    public static void main(String args[]) {
        try (Scanner Scanner = new Scanner(System.in)) {
            System.out.println("Enter the size of your array: ");
            int size = Scanner.nextInt();

            System.out.println("Enter your integers you want in your array (input a number then press enter after each number)");
            int[] array = new int[size];
            
            for (int i = 0; i < size; i++) {
                array[i] = Scanner.nextInt();
            }
            System.out.println("Number of inversions: " + mergeSort(array) + ".");
        }
    }

    public static int mergeSort(int[] array) {
        int[] temp = new int[array.length];
        return mergeSort(array, temp, 0, array.length - 1);
    }

    private static int mergeSort(int[] array, int[] temp, int left, int right) {
        int counter = 0;

        if (left < right) {
            int mid = (left + right) / 2;
            counter += mergeSort(array, temp, left, mid);
            counter += mergeSort(array, temp, mid + 1, right);
            counter += merge(array, temp, left, mid, right);
        }
        return counter;
    }
    private static int merge(int[] array, int[] temp, int left, int mid, int right) {
        int counter = 0;

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            }
            else {
                temp[k++] = array[j++];
                counter += (mid - i - 1);
            }
        }
        while (i <= mid) {
            temp[k++] = array[i++];
        }
        while (j <= right) {
            temp[k++] = array[j++];
        }
        for(i = left; i <= right; i++) {
            array[i] = temp[i];
        }
        return counter;
    }
}
