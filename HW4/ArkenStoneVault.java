package HW4;

public class ArkenStoneVault {

    public static void main(String[] args) {
        // Define a 2D array (matrix) representing the gem values in the vault
        int[][] gemMatrix = {
            {96, 33, 44, 98, 75, 68, 99, 84},  // Row 1
            {10, 41, 1, 86, 46, 24, 53, 93},   // Row 2
            {83, 97, 94, 27, 65, 51, 30, 7},   // Row 3
            {56, 70, 47, 64, 22, 88, 67, 12},  // Row 4
            {91, 11, 77, 48, 13, 71, 92, 15},  // Row 5
            {32, 59, 17, 25, 31, 4, 16, 63},   // Row 6
            {79, 5, 14, 23, 78, 37, 40, 74},   // Row 7
            {35, 89, 52, 66, 82, 20, 95, 21}   // Row 8
        };

        // Call the method to find the most precious path in the gemMatrix
        findMostPreciousPath(gemMatrix);
    }

    private static void findMostPreciousPath(int[][] matrix) {
        int numRows = matrix.length;            // Number of rows in the matrix
        int numCols = matrix[0].length;         // Number of columns in the matrix

        // Initialize a DP (Dynamic Programming) table to store intermediate results
        int[][] dpTable = new int[numRows][numCols];

        // Copy the first row of the matrix to the DP table as the base case
        System.arraycopy(matrix[0], 0, dpTable[0], 0, numCols);

        // Iterate through the matrix to fill the DP table
        for (int row = 1; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                // Calculate the maximum gems that can be collected from three possible directions
                int fromLeft = (col > 0) ? dpTable[row - 1][col - 1] : 0;
                int fromAbove = dpTable[row - 1][col];
                int fromRight = (col < numCols - 1) ? dpTable[row - 1][col + 1] : 0;
                dpTable[row][col] = matrix[row][col] + Math.max(fromLeft, Math.max(fromAbove, fromRight));
            }
        }

        // Backtrack from the last row to find the path that collected the maximum gems
        String path = "";
        int maxGems = 0;
        int col = -1;
        // Find the column in the last row with the maximum gems
        for (int i = 0; i < numCols; i++) {
            if (dpTable[numRows - 1][i] > maxGems) {
                maxGems = dpTable[numRows - 1][i];
                col = i;
            }
        }

        // Trace back the path from the bottom to the top of the matrix
        int row = numRows - 1;
        while (row >= 0) {
            path = "(" + (row + 1) + ", " + (col + 1) + ") " + path;
            int prevRow = row - 1;
            // Adjust the column index if the maximum gems were collected from left or right
            if (prevRow >= 0) {
                if (col > 0 && dpTable[prevRow][col - 1] + matrix[row][col] == dpTable[row][col]) {
                    col -= 1;
                } else if (col < numCols - 1 && dpTable[prevRow][col + 1] + matrix[row][col] == dpTable[row][col]) {
                    col += 1;
                }
            }
            row -= 1;
        }
       
        // Print the results
        System.out.println("Starting square: " + path.substring(0, 6));
        System.out.println("Most Precious Path (row, vault): " + path);
        System.out.println("Total Gems Collected: " + maxGems);
        System.out.println("Ending square: " + path.substring(path.length() - 7, path.length()));
    }
}
