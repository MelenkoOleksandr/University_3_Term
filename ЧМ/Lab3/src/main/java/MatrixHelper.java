public class MatrixHelper {
    public static double[] multiplyMatrixByVector(double[][] markovMatrix, double[] x0) {
        double[] result = new double[markovMatrix.length];
        for (int i = 0; i < markovMatrix.length; i++) {
            for (int j = 0; j < markovMatrix.length; j++) {
                result[i] += markovMatrix[i][j] * x0[j];
            }
        }
        return result;
    }
    public static double[][] multiplyMatrixByMatrix(double[][] result, double[][] matrix) {
        double[][] temp = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                for (int k = 0; k < matrix.length; k++) {
                    temp[i][j] += result[i][k] * matrix[k][j];
                }
            }
        }
        return temp;
    }

    public static double[][] getMatrixDegree(double[][] matrix, int degree) {
        double[][] result = matrix;
        for (int i = 1; i < degree; i++) {
            result = multiplyMatrixByMatrix(result, matrix);
        }
        return result;
    }

    public static double[][] getSumOfMatrices(double[][] matrix1, double[][] matrix2) {
        double[][] sum = new double[matrix1.length][matrix1.length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1.length; j++) {
                sum[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return sum;
    }

    public static double[][] multiplyMatrixByNumber(double[][] matrix, double number) {
        double[][] result = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                result[i][j] = matrix[i][j] * number;
            }
        }
        return result;
    }

    public static void printMatrix(double[][] matrix, String title) {
        System.out.println(title);
        for (double[] row : matrix) {
            for (double column : row) {
                System.out.print(column + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static double getNorm(double[] vector) {
        double sum = 0;
        for (double v : vector) {
            sum += Math.abs(v);
        }
        return sum;
    }

    public static double[] subtractVectors(double[] vector1, double[] vector2) {
        double[] result = new double[vector1.length];
        for (int i = 0; i < vector1.length; i++) {
            result[i] = vector1[i] - vector2[i];
        }
        return result;
    }

    public static void printVector(double[] x, String title) {
        System.out.println(title);
        for (double v : x) {
            System.out.println(v);
        }
        System.out.println();
    }
}
