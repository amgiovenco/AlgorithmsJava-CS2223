import java.util.Arrays;
import java.util.Scanner;

public class dijkstrasAlgorithm {

    private static final int NO_PARENT = -1;

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 54, 11, 13, 0, 0, 0, 0, 0, 0},
                {54, 0, 37, 0, 3, 0, 102, 0, 0, 0,},
                {11, 37, 0, 10, 36, 19, 0, 0, 0, 0},
                {13, 0, 10, 0, 0, 18, 0, 0, 7, 0},
                {0, 3, 36, 0, 0, 15, 124, 123, 0, 0},
                {0, 0, 19, 18, 15, 0, 0, 138, 8, 0},
                {0, 102, 0, 0, 124, 0, 0, 9, 0, 72},
                {0, 0, 0, 0, 123, 138, 9, 0, 146, 67},
                {0, 0, 0, 7, 0, 8, 0, 146, 0, 213},
                {0, 0, 0, 0, 0, 0, 72, 67, 213, 0}
        };

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Starting Vertex (0-9): ");
        int start = scanner.nextInt();
        System.out.print("Enter Ending Vertex (0-9): ");
        int end = scanner.nextInt();

        algo(matrix, start, end);
    }

    public static void algo(int[][] matrix, int start, int end) {

        int n = matrix[0].length;
        int[] shortest = new int[n];
        boolean[] added = new boolean[n];
        Arrays.fill(shortest, Integer.MAX_VALUE);
        Arrays.fill(added, false);
        shortest[start] = 0;
        int[] parents = new int[n];
        parents[start] = NO_PARENT;

        for (int i = 1; i < n; i++) {
            int closest = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (!added[j] && shortest[j] < shortestDistance) {
                    closest = j;
                    shortestDistance = shortest[j];
                }
            }

            added[closest] = true;

            for (int j = 0; j < n; j++) {
                int edgeDistance = matrix[closest][j];

                if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortest[j])) {
                    parents[j] = closest;
                    shortest[j] = shortestDistance + edgeDistance;
                }
            }
        }

        System.out.println("Start Vertex: " + start + " to End Vertex: " + end);
        System.out.println("Shortest Path Value: " + shortest[end]);
        System.out.print("Path: ");
        path(end, parents);
    }

    private static void path(int currentVertex, int[] parents) {
        if (currentVertex == NO_PARENT) {
            return;
        }
        path(parents[currentVertex], parents);
        System.out.print(currentVertex + " ");
    }
}
