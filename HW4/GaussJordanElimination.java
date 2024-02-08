package HW4;

public class GaussJordanElimination {

    public static double[] gaussJordanElimination(double[][] A, double[] b) {
        int n = A.length;
        double[][] Ab = new double[n][n+1];

        // Construct the augmented matrix Ab
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Ab[i][j] = A[i][j];
            }
            Ab[i][n] = b[i];
        }

        // Perform the Gauss-Jordan elimination
        for (int i = 0; i < n; i++) {
            // Partial pivoting
            int pivotRow = i;
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(Ab[j][i]) > Math.abs(Ab[pivotRow][i])) {
                    pivotRow = j;
                }
            }

            // Swap the rows
            double[] temp = Ab[i];
            Ab[i] = Ab[pivotRow];
            Ab[pivotRow] = temp;

            // Normalize the pivot row
            double pivot = Ab[i][i];
            for (int j = i; j <= n; j++) {
                Ab[i][j] /= pivot;
            }

            // Eliminate the other rows
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    double factor = Ab[j][i];
                    for (int k = i; k <= n; k++) {
                        Ab[j][k] -= factor * Ab[i][k];
                    }
                }
            }
        }

        // Extract the solution
        double[] x = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = Ab[i][n];
        }

        return x;
    }

    public static void main(String[] args) {
        // Example system of equations Ax = b
        double[][] A = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, -1, -1, -1, -1},
            {1, -1, 1, -1, 1, -1, 1, -1, 1},
            {1, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1},
            {1, 1, -1, 1, 1, -1, 1, 1, -1},
            {9, -8, 7, -6, 5, -4, 3, -2, 1}
        };

        double[] b = {122, -88, 32, 3, 7, 18, 76, 0, 41};

        double[] x = gaussJordanElimination(A, b);

        // Print the solution
        for (int i = 0; i < x.length; i++) {
            long rounded = Math.round(x[i]);
            if (i < x.length - 1) {
                System.out.print(rounded + ", ");
            }
            else {
                System.out.print(rounded);
            }
        }
    }
}
