package HW2;

public class LucasNumbers {
    public static void main(String[] args) { 
        int n = 40;
        System.out.println("Lucas number at position " + n + " is: " + lucas(n));
    }
    public static int lucas(int n) {
        if (n == 0) {
            return 2;
        }
        if (n == 1) {
            return 1;
        }
        return lucas(n - 1) + lucas(n - 2);
    }
}