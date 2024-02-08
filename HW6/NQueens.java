import java.util.Arrays;

public class NQueens {
    private static int[] queens;

    public static void main(String[] args) {
        // Find and print the 1st legal position for N Queens for N ranging from 4 to 32
        for (int i = 4; i <= 14; i++) {
            System.out.printf("1st legal position for %d Queens is %s\n",
                    i, Arrays.toString(nextSolution(new int[i])));
        }

        // Calculate and print the number of legal positions for N Queens for N ranging from 4 to 17
        for (int i = 4; i <= 12; i++) {
            solutionrate(i);
        }
    }

    // Calculate and print the number of legal positions for N Queens
    private static void solutionrate(int n) {
        int[] queens = new int[n];
        int rate = 0;

        // Iterate until no more legal positions are found
        while (true) {
            queens = nextSolution(queens);
            if (queens[0] == 0)
                break;
            rate++;
        }
        System.out.printf("%d legal %d Queen positions found.\n", rate, n);
    }

    // Find the next legal solution for the N Queens problem
    private static int[] nextSolution(int[] queens) {
        while (true) {
            // Find the next legal position for the queens
            queens = PositionChecker.nxtLglPos(queens);

            // Check if the current arrangement is a solution
            if (PositionChecker.isSolution(queens, queens.length)) {
                return queens;
            }
        }
    }
}
