public class CroutDecompositionMethod {

    public static void main(String[] args) {
        //1st problem
//        double[][] coefficients = {
//                {2, -1, 3, 9},
//                {1, 2, 1, 8},
//                {3, 0, -1, 3}
//        };

        //2nd problem
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

        // Crout decomposition
        double[][] L = new double[n][n];
        double[][] U = new double[n][n];

        for (int i = 0; i < n; i++) {
            U[i][i] = 1; // Diagonal of U is set to 1

            for (int j = i; j < n; j++) {
                double sum = 0;
                for (int k = 0; k < i; k++) {
                    sum += L[j][k] * U[k][i];
                }
                L[j][i] = coefficients[j][i] - sum;
            }

            for (int j = i + 1; j < n; j++) {
                double sum = 0;
                for (int k = 0; k < i; k++) {
                    sum += L[i][k] * U[k][j];
                }
                if (L[i][i] == 0) {
                    System.out.println("Divide by zero error.");
                    return null;
                }
                U[i][j] = (coefficients[i][j] - sum) / L[i][i];
            }
        }

        // Solve Ly = b using forward substitution
        double[] Y = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < i; j++) {
                sum += L[i][j] * Y[j];
            }
            if (L[i][i] == 0) {
                System.out.println("Divide by zero error.");
                return null;
            }
            Y[i] = (coefficients[i][n] - sum) / L[i][i];
        }

        // Solve Ux = y using backward substitution
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += U[i][j] * solution[j];
            }
            if (U[i][i] == 0) {
                System.out.println("Divide by zero error.");
                return null;
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
