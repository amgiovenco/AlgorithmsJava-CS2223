package HW2;

public class LucasNumbersTiming {
    public static void main (String[] args) {
        for (int i = 0; i <= 40; i++) {
            long startTime = System.nanoTime();
            LucasNumbers.lucas(i);
            long endTime = System.nanoTime();
            System.out.println("Time taken to comput L(" + i + ") is: " + (endTime - startTime) + " nanoseconds.");
        }
    }
}
