public class GaussLUDecompositionMethod {

    public static void main(String[] args) {
        // Coefficients of the linear equations
//        double[][] coefficients = {
//                {2, -1, 3, 9},
//                {1, 2, 1, 8},
//                {3, 0, -1, 3}
//        };

        double[][] coefficients = {
                {3, 2, -1, 10},
                {2, -1, 3, 5},
                {1, 3, 2, 15}
        };

        double[] solution = solveEquations(coefficients);

        // Displaying the solution
        if (solution == null) {
            System.out.println("The system of equations has no unique solution.");
        } else {
            System.out.println("Solution:");
            for (int i = 0; i < solution.length; i++) {
                System.out.println("x" + (i + 1) + " = " + solution[i]);
            }
        }
    }

    public static double[] solveEquations(double[][] coefficients) {
        int n = coefficients.length;
        double[] solution = new double[n];

        if (!isSquare(coefficients) || coefficients[0].length != n + 1) {
            System.out.println("Invalid coefficient matrix.");
            return null;
        }

        // LU decomposition
        double[][] L = new double[n][n];
        double[][] U = new double[n][n];
        for (int i = 0; i < n; i++) {
            // Upper triangular matrix
            for (int k = i; k < n; k++) {
                double sum = 0;
                for (int j = 0; j < i; j++) {
                    sum += L[i][j] * U[j][k];
                }
                U[i][k] = coefficients[i][k] - sum;
            }

            // Lower triangular matrix
            L[i][i] = 1;
            for (int k = i + 1; k < n; k++) {
                double sum = 0;
                for (int j = 0; j < i; j++) {
                    sum += L[k][j] * U[j][i];
                }
                L[k][i] = (coefficients[k][i] - sum) / U[i][i];
            }
        }

        // Solve LY = b using forward substitution
        double[] Y = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < i; j++) {
                sum += L[i][j] * Y[j];
            }
            Y[i] = (coefficients[i][n] - sum) / L[i][i];
        }

        // Solve UX = Y using backward substitution
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += U[i][j] * solution[j];
            }
            solution[i] = (Y[i] - sum) / U[i][i];
        }

        return solution;
    }

    public static boolean isSquare(double[][] matrix) {
        int rows = matrix.length;
        for (double[] row : matrix) {
            if (row.length != rows + 1) {
                return false;
            }
        }
        return true;
    }
}
