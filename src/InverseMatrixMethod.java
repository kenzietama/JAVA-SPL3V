public class InverseMatrixMethod {

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

        double[][] matrixA = new double[3][3];
        double[] vectorB = new double[3];

        // Extracting coefficients into matrixA and vectorB
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrixA[i][j] = coefficients[i][j];
            }
            vectorB[i] = coefficients[i][3];
        }

        // Calculating the determinant of matrixA
        double determinant = matrixA[0][0] * (matrixA[1][1] * matrixA[2][2] - matrixA[1][2] * matrixA[2][1])
                - matrixA[0][1] * (matrixA[1][0] * matrixA[2][2] - matrixA[1][2] * matrixA[2][0])
                + matrixA[0][2] * (matrixA[1][0] * matrixA[2][1] - matrixA[1][1] * matrixA[2][0]);

        if (determinant == 0) {
            System.out.println("The system of equations has no unique solution.");
        } else {
            // Calculating the inverse of matrixA
            double[][] inverseMatrixA = new double[3][3];
            inverseMatrixA[0][0] = (matrixA[1][1] * matrixA[2][2] - matrixA[1][2] * matrixA[2][1]) / determinant;
            inverseMatrixA[0][1] = (matrixA[0][2] * matrixA[2][1] - matrixA[0][1] * matrixA[2][2]) / determinant;
            inverseMatrixA[0][2] = (matrixA[0][1] * matrixA[1][2] - matrixA[0][2] * matrixA[1][1]) / determinant;
            inverseMatrixA[1][0] = (matrixA[1][2] * matrixA[2][0] - matrixA[1][0] * matrixA[2][2]) / determinant;
            inverseMatrixA[1][1] = (matrixA[0][0] * matrixA[2][2] - matrixA[0][2] * matrixA[2][0]) / determinant;
            inverseMatrixA[1][2] = (matrixA[0][2] * matrixA[1][0] - matrixA[0][0] * matrixA[1][2]) / determinant;
            inverseMatrixA[2][0] = (matrixA[1][0] * matrixA[2][1] - matrixA[1][1] * matrixA[2][0]) / determinant;
            inverseMatrixA[2][1] = (matrixA[0][1] * matrixA[2][0] - matrixA[0][0] * matrixA[2][1]) / determinant;
            inverseMatrixA[2][2] = (matrixA[0][0] * matrixA[1][1] - matrixA[0][1] * matrixA[1][0]) / determinant;

            // Multiplying inverseMatrixA and vectorB to get the solution
            double[] solution = new double[3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    solution[i] += inverseMatrixA[i][j] * vectorB[j];
                }
            }

            // Displaying the solution
            System.out.println("Solution:");
            for (int i = 0; i < 3; i++) {
                System.out.println("x" + (i + 1) + " = " + solution[i]);
            }
        }
    }
}
