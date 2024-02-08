import java.util.Arrays;
import java.util.Scanner;

public class PositionChecker {

    public static void main(String[] args) {
        // Example 1: Legal position
        int[] t1 = {1, 6, 8, 3, 7, 4, 2, 5};
        System.out.println(isLegalPosition(t1, 8));

        // Example 2: Illegal position with two queens in the same column
        int[] t2 = {1, 6, 8, 3, 7, 0, 0, 0};
        System.out.println(isLegalPosition(t2, 8));

        // Example 3: Illegal position with two queens in the same diagonal
        int[] t3 = {1, 6, 8, 3, 5, 0, 0, 0};
        System.out.println(isLegalPosition(t3, 8));

    }

    // Checks if the given arrangement of queens is legal
    public static boolean isLegalPosition(int[] queens, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (queens[i] == 0 || queens[j] == 0) { // Skip if queen is not placed
                    continue;
                }
                if (queens[i] == queens[j]) { // Check if queens are in the same column
                    return false;
                }
                if (Math.abs(queens[i] - queens[j]) == Math.abs(i - j)) { // Check if queens are in the same diagonal
                    return false;
                }
            }
        }
        return true;
    }

    // Checks if the given arrangement is a solution (all queens placed)
    public static boolean isSolution(int[] queens, int n) {
        int rate = 0;
        for (int i = 0; i < n; i++) {
            if (queens[i] == 0) rate++;
        }
        if (rate > 0 && rate != n) return false;
        return true;
    }

    // Finds the next legal position for queens based on the current arrangement
    public static int[] nxtLglPos(int[] queens) {
        int[] nextPos = queens.clone();
        for (int i = nextPos.length - 1; i >= 0; i--) {
            // If this entry AND the previous entry are 0, skip
            if (nextPos[i] == 0 && i != 0 && nextPos[i - 1] == 0) continue;

            while (nextPos[i] <= nextPos.length - 1) {
                nextPos[i] += 1;
                if (PositionChecker.isLegalPosition(nextPos, queens.length)) {
                    return nextPos;
                }
            }
            nextPos[i] = 0;
        }
        return nextPos;
    }
}
