import java.util.Arrays;

public class MatrixGenerator {

    public static boolean checkConvergence(double[][] matrix) {
        double[][] A = new double[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            double cur = matrix[i][i];
            for (int j = 0; j < matrix.length; j++) {
                if (i == j ) {
                    A[i][j] = 0;
                } else {
                    A[i][j] = matrix[i][j] / cur;
                }
            }
        }

//        MatrixGenerator.printMatrix(A);

       double[] sums = new  double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                sums[i] += Math.abs(A[i][j]);
            }
        }

        double max = -Double.MAX_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            if (max < sums[i] ) {
                max = sums[i];
            }
        }

        return max < 1;
    }


    public static double[][] createRandomMatrix(int n) throws Exception {
        if (n > 1000) throw new Exception("Max size of matrix exceeded");

        double[][] matrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Math.floor(Math.random() * 100) + 1;
            }
        }

        for (int i = 0; i < n; i++) {
            double sumOfNonDiagonal = 0;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    sumOfNonDiagonal += matrix[i][j];
                }
            }
            matrix[i][i] = sumOfNonDiagonal + Math.floor(Math.random() * 50);
        }

        return  matrix;
    }

    public static double[][] createHilbertMatrix(int n) throws Exception {
        if (n > 1000) throw new Exception("Max size of matrix exceeded");
        double[][] matrix = new double[n][n];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                matrix[i-1][j-1] = 1 / (double) (i + j - 1);
            }
        }
        return  matrix;
    }

    public static double[] createVector(double[][] matrix, int n) throws Exception {
        if (n > 1000) throw new Exception("Max size of vector exceeded");

        double[] vector = new double[n];

        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += matrix[i][j];
            }
            vector[i] = sum;
        }
//        for (int i = 0; i < n; i++) {
//            vector[i] = Math.floor(Math.random() * 100);
//        }
        System.out.println("b: " + Arrays.toString(vector));
        return  vector;
    }

    public static void printMatrix(double[][] matrix) {
        System.out.println("Matrix:");
        for (double[] doubles : matrix) {
            System.out.println(Arrays.toString(doubles));
        }
        System.out.println();
    }
}
